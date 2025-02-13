package com.submission.mis.worksubmission.controllers;

import com.submission.mis.worksubmission.models.Submission;
import com.submission.mis.worksubmission.service.SubmissionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownloadSingleSubmission extends HttpServlet {
    private final SubmissionService submissionService = SubmissionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submissionIdStr = request.getParameter("submissionId");
        if (submissionIdStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid submission ID");
            return;
        }
        int submissionId = Integer.parseInt(submissionIdStr);

        // Fetch the submission
        Submission submission = submissionService.getSubmissionById(submissionId);
        if (submission == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Submission not found");
            return;
        }

        // Set response headers
        File file = new File(submission.getFilePath());
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            return;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());

        // Stream the file to the response
        try (FileInputStream fis = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }
}