package com.submission.mis.worksubmission.controllers;

import com.submission.mis.worksubmission.models.Course;
import com.submission.mis.worksubmission.service.AssignmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/view-assignments")
public class ViewAssignmentsServlet extends HttpServlet {
    private final AssignmentService assignmentService = AssignmentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        request.setAttribute("assignments", assignmentService.getAssignmentsByCourse(courseId));
        request.getRequestDispatcher("WEB-INF/pages/view-assignments.jsp").forward(request, response);
    }
}