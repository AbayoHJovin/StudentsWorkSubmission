package com.submission.mis.worksubmission.controllers;

import com.submission.mis.worksubmission.models.Assignment;
import com.submission.mis.worksubmission.models.Course;
import com.submission.mis.worksubmission.models.Instructor;
import com.submission.mis.worksubmission.service.AssignmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class CreateAssignmentServlet extends HttpServlet {
    private final AssignmentService assignmentService = AssignmentService.getInstance();
    private static final String UPLOAD_DIRECTORY = "assignments"; // Directory to save uploaded files

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/createAssignment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }

        Instructor instructor = (Instructor) session.getAttribute("user");
        Map<String, String> errors = new HashMap<>(); // Store validation errors

        // Get form data
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String deadlineStr = request.getParameter("deadline");
        String courseIdStr = request.getParameter("courseId");

        // Validate title
        if (title == null || title.trim().isEmpty()) {
            errors.put("title", "Title is required.");
        }

        // Validate description
        if (description == null || description.trim().isEmpty()) {
            errors.put("description", "Description is required.");
        }

        // Validate deadline
        LocalDate deadline = null;
        if (deadlineStr == null || deadlineStr.trim().isEmpty()) {
            errors.put("deadline", "Deadline is required.");
        } else {
            try {
                deadline = LocalDate.parse(deadlineStr);
                if (deadline.isBefore(LocalDate.now())) {
                    errors.put("deadline", "Deadline must be a future or current date.");
                }
            } catch (DateTimeParseException e) {
                errors.put("deadline", "Invalid date format. Use YYYY-MM-DD.");
            }
        }

        // Validate course ID
        int courseId = 0;
        if (courseIdStr == null || courseIdStr.trim().isEmpty()) {
            errors.put("courseId", "Course ID is required.");
        } else {
            try {
                courseId = Integer.parseInt(courseIdStr);
            } catch (NumberFormatException e) {
                errors.put("courseId", "Invalid course ID.");
            }
        }

        // Handle file upload
        String filePath = null;
        Part filePart = request.getPart("file");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = getFileName(filePart);
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);
            filePath = UPLOAD_DIRECTORY + "/" + fileName; // Relative path for database
        }

        // If there are validation errors, return to the form with error messages
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("WEB-INF/pages/createAssignment.jsp").forward(request, response);
            return;
        }

        // Create the assignment
        Course course = new Course();
        course.setId(courseId);

        Assignment assignment = new Assignment(
                title,
                description,
                deadline,
                filePath, // Save the relative file path in the database
                instructor,
                course
        );
        assignmentService.addAssignment(assignment);

        // Redirect to the success page
        response.sendRedirect("createdAssignment");
    }

    // Helper method to get the file name from the Part
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}