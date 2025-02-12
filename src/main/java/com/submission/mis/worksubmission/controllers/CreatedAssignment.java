package com.submission.mis.worksubmission.controllers;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

import com.submission.mis.worksubmission.models.Student;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "CreatedAssignment", value = "/createdAssignment")
public class CreatedAssignment extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/pages/createdAssignment.jsp").forward(request, response);
    }
}