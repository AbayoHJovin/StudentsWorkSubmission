package com.submission.mis.worksubmission.controllers;

import com.submission.mis.worksubmission.models.Assignment;
import com.submission.mis.worksubmission.models.Student;
import com.submission.mis.worksubmission.models.Submission;
import com.submission.mis.worksubmission.service.AssignmentService;
import com.submission.mis.worksubmission.service.SubmissionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class SubmitAssignmentServlet extends HttpServlet {
    private final AssignmentService assignmentService = AssignmentService.getInstance();
    private final SubmissionService submissionService = SubmissionService.getInstance();
    private static final String UPLOAD_DIRECTORY = "submissions"; // Directory to save uploaded files

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("formName", "Student Registration");
        request.getRequestDispatcher("WEB-INF/pages/submitAssignment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }

        // Get the logged-in student from the session
        Student student = (Student) session.getAttribute("user");

        // Get the assignment ID from the request parameters
        String assignmentIdStr = request.getParameter("assignmentId");
        if (assignmentIdStr == null || assignmentIdStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Assignment ID is required.");
            return;
        }

        int assignmentId;
        try {
            assignmentId = Integer.parseInt(assignmentIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Assignment ID.");
            return;
        }

        // Fetch the assignment from the database
        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        if (assignment == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Assignment not found.");
            return;
        }

        // Handle file upload
        Part filePart = request.getPart("assignmentFile");
        if (filePart == null || filePart.getSize() == 0) {
            request.setAttribute("error", "No file uploaded.");
            request.getRequestDispatcher("submitAssignment.jsp?assignmentId=" + assignmentId).forward(request, response);
            return;
        }

        // Validate file size (max 10MB)
        if (filePart.getSize() > 10 * 1024 * 1024) {
            request.setAttribute("error", "File size exceeds the maximum limit of 10MB.");
            request.getRequestDispatcher("submitAssignment.jsp?assignmentId=" + assignmentId).forward(request, response);
            return;
        }

        // Save the file to the submissions directory
        String fileName = getFileName(filePart);
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        // Save the relative file path for the database
        String relativeFilePath = UPLOAD_DIRECTORY + "/" + fileName;

        // Create the submission
        Submission submission = new Submission(
                fileName,
                relativeFilePath,
                LocalDateTime.now(),
                student,
                assignment
        );
        submissionService.addSubmission(submission);

        // Redirect to a success page or back to the dashboard
        response.sendRedirect("student-dashboard");
    }

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