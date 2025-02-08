package com.submission.mis.worksubmission.controllers;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

import com.submission.mis.worksubmission.models.Student;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("formName", "Student Registration");
        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
    }
}