package com.submission.mis.worksubmission.controllers;

import com.submission.mis.worksubmission.service.SubmissionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/downloadSubmission")
public class DownloadSubmissionServlet extends HttpServlet {
    private final SubmissionService submissionService = SubmissionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int submissionId = Integer.parseInt(request.getParameter("submissionId"));
        String filePath = submissionService.getSubmissionById(submissionId).getFilePath();

        File file = new File(getServletContext().getRealPath("") + File.separator + filePath);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        try (FileInputStream fileInputStream = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = fileInputStream.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }
}