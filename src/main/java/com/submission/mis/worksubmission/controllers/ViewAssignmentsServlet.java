package com.submission.mis.worksubmission.controllers;

import com.submission.mis.worksubmission.models.Course;
import com.submission.mis.worksubmission.service.AssignmentService;
import com.submission.mis.worksubmission.service.CourseService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/view-assignments")
public class ViewAssignmentsServlet extends HttpServlet {
    private final AssignmentService assignmentService = AssignmentService.getInstance();
    private final CourseService courseService = CourseService.getCourseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        // Fetch the course details
        Course course = courseService.getCourseById(courseId);
        request.setAttribute("course", course);

        // Fetch assignments for the course
        request.setAttribute("assignments", assignmentService.getAssignmentsByCourse(courseId));

        // Forward to the view-assignments.jsp
        request.getRequestDispatcher("WEB-INF/pages/view-assignments.jsp").forward(request, response);
    }
}