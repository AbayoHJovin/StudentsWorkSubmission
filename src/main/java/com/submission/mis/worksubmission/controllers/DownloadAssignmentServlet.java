package com.submission.mis.worksubmission.controllers;

import com.submission.mis.worksubmission.models.Assignment;
import com.submission.mis.worksubmission.service.AssignmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class DownloadAssignmentServlet extends HttpServlet {
    private final AssignmentService assignmentService = AssignmentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String assignmentIdStr = request.getParameter("assignmentId");
        if (assignmentIdStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid assignment ID");
            return;
        }

        int assignmentId;
        try {
            assignmentId = Integer.parseInt(assignmentIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid assignment ID format");
            return;
        }

        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        if (assignment == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Assignment not found");
            return;
        }

        // Set response headers for ZIP file download
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=assignment_" + assignmentId + ".zip");

        try (ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream())) {
            // Step 1: Add a PDF file with assignment details
            File pdfFile = generateAssignmentDetailsPDF(assignment);
            addFileToZip(pdfFile, "Assignment_Details.pdf", zipOut);
            pdfFile.delete(); // Clean up temporary file

            // Step 2: Add the attached file (if it exists)
            if (assignment.getAssignmentLink() != null && !assignment.getAssignmentLink().isEmpty()) {
                File attachedFile = new File(getServletContext().getRealPath("/") + assignment.getAssignmentLink());
                if (attachedFile.exists()) {
                    addFileToZip(attachedFile, attachedFile.getName(), zipOut);
                } else {
                    // Log a warning if the attached file is missing
                    System.err.println("Attached file not found: " + attachedFile.getAbsolutePath());
                }
            }
        } catch (IOException e) {
            // Log the error and send a 500 response
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generating ZIP file");
        }
    }

    /**
     * Generates a temporary PDF file with assignment details.
     */
    private File generateAssignmentDetailsPDF(Assignment assignment) throws IOException {
        File pdfFile = File.createTempFile("assignment_details", ".pdf");

        try (PdfWriter writer = new PdfWriter(pdfFile);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {
            document.add(new Paragraph("Course: " + assignment.getCourse().getName()));
            document.add(new Paragraph("Instructor: " + assignment.getInstructor().getFirstName() + " " + assignment.getInstructor().getLastName()));
            document.add(new Paragraph("Deadline: " + assignment.getDeadline()));
            document.add(new Paragraph("Assignment Title: " + assignment.getTitle()));
            document.add(new Paragraph("Description: " + assignment.getDescription()));

        }

        return pdfFile;
    }

    /**
     * Adds a file to the ZIP output stream.
     */
    private void addFileToZip(File file, String fileName, ZipOutputStream zipOut) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zipOut.write(buffer, 0, len);
            }
            zipOut.closeEntry();
        }
    }
}