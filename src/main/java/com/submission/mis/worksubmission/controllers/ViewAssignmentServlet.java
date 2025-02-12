package com.submission.mis.worksubmission.controllers;

import java.io.*;

import com.submission.mis.worksubmission.models.Assignment;
import com.submission.mis.worksubmission.service.AssignmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

public class ViewAssignmentServlet extends HttpServlet {
    private final AssignmentService assignmentService = AssignmentService.getInstance();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
      request.setAttribute("assignment", assignment);
      request.getRequestDispatcher("WEB-INF/pages/viewAssignment.jsp").forward(request, response);
    }
}