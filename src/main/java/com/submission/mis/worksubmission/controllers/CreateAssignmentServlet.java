package com.submission.mis.worksubmission.controllers;

import com.submission.mis.worksubmission.models.Assignment;
import com.submission.mis.worksubmission.models.Course;
import com.submission.mis.worksubmission.models.Instructor;
import com.submission.mis.worksubmission.service.AssignmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateAssignmentServlet extends HttpServlet {
    private final AssignmentService assignmentService = AssignmentService.getInstance();
    private static final String UPLOAD_DIRECTORY = "uploads";

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
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        Map<String, String> errors = new HashMap<>(); // Store validation errors
        String title = null, description = null, deadlineStr = null, courseIdStr = null;
        String filePath = null;

        try {
            List<FileItem> formItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest((javax.servlet.http.HttpServletRequest) request);

            // Parse form data
            for (FileItem item : formItems) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case "title":
                            title = item.getString();
                            if (title == null || title.trim().isEmpty()) {
                                errors.put("title", "Title is required.");
                            }
                            break;
                        case "description":
                            description = item.getString();
                            if (description == null || description.trim().isEmpty()) {
                                errors.put("description", "Description is required.");
                            }
                            break;
                        case "deadline":
                            deadlineStr = item.getString();
                            if (deadlineStr == null || deadlineStr.trim().isEmpty()) {
                                errors.put("deadline", "Deadline is required.");
                            }
                            break;
                        case "courseId":
                            courseIdStr = item.getString();
                            if (courseIdStr == null || courseIdStr.trim().isEmpty()) {
                                errors.put("courseId", "Course ID is required.");
                            }
                            break;
                    }
                } else {
                    if (!item.getName().isEmpty()) {
                        String fileName = new File(item.getName()).getName();
                        filePath = uploadPath + File.separator + fileName;
                        item.write(new File(filePath));
                    }
                }
            }

            // Validate deadline format and ensure it's in the future
            LocalDate deadline = null;
            if (deadlineStr != null && !deadlineStr.trim().isEmpty()) {
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
            if (courseIdStr != null && !courseIdStr.trim().isEmpty()) {
                try {
                    courseId = Integer.parseInt(courseIdStr);
                } catch (NumberFormatException e) {
                    errors.put("courseId", "Invalid course ID.");
                }
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
                    filePath != null ? UPLOAD_DIRECTORY + "/" + new File(filePath).getName() : null,
                    instructor,
                    course
            );
            assignmentService.addAssignment(assignment);

            // Redirect to the success page
            response.sendRedirect("success");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Failed to create assignment: " + e.getMessage());
            request.getRequestDispatcher("WEB-INF/pages/createAssignment.jsp").forward(request, response);
        }
    }
}