//package com.submission.mis.worksubmission.controllers;
//
//import com.submission.mis.worksubmission.models.Assignment;
//import com.submission.mis.worksubmission.service.SubmissionService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebServlet("/view-submissions")
//public class ViewSubmissionsServlet extends HttpServlet {
//    private final SubmissionService submissionService = SubmissionService.getInstance();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int assignmentId = Integer.parseInt(request.getParameter("assignmentId"));
//        request.setAttribute("submissions", submissionService.getSubmissionsByAssignment(assignmentId));
//        request.getRequestDispatcher("WEB-INF/pages/view-submissions.jsp").forward(request, response);
//    }
//}