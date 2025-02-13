package com.submission.mis.worksubmission.controllers;

import com.submission.mis.worksubmission.models.Submission;
import com.submission.mis.worksubmission.service.SubmissionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DownloadAllSubmissionsServlet extends HttpServlet {
    private final SubmissionService submissionService = SubmissionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String assignmentIdStr = request.getParameter("assignmentId");
        if (assignmentIdStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid assignment ID");
            return;
        }
        int assignmentId = Integer.parseInt(assignmentIdStr);

        // Fetch all submissions for the assignment
        List<Submission> submissions = submissionService.getSubmissionsByAssignment(assignmentId);

        // Set response headers for a zip file
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=submissions.zip");

        try (ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream())) {
            for (Submission submission : submissions) {
                File file = new File(submission.getFilePath());
                if (file.exists()) {
                    // Rename the file to the student's name
                    String studentName = submission.getStudent().getFirstName() + "_" + submission.getStudent().getLastName();
                    String fileName = studentName + "_" + file.getName();

                    // Add the file to the zip
                    zipOut.putNextEntry(new ZipEntry(fileName));
                    try (FileInputStream fis = new FileInputStream(file)) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = fis.read(buffer)) > 0) {
                            zipOut.write(buffer, 0, length);
                        }
                    }
                    zipOut.closeEntry();
                }
            }
        }
    }
}