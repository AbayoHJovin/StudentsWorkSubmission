//package com.submission.mis.worksubmission.controllers;
//
//import com.submission.mis.worksubmission.models.Instructor;
//import com.submission.mis.worksubmission.models.Student;
//import com.submission.mis.worksubmission.service.InstructorService;
//import com.submission.mis.worksubmission.service.StudentService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;
//
//import java.io.IOException;
//
//@WebServlet("/login")
//public class LoginServlet extends HttpServlet {
//    InstructorService instructorService = InstructorService.getInstructorService();
//    private final StudentService studentService = StudentService.getInstance();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String role = request.getParameter("role");
//
//        try {
//            if (role.equals("INSTRUCTOR")) {
//                Instructor instructor = instructorService.getInstructorByEmail(email);
//                if (instructor != null && instructor.getPassword().equals(password)) {
//                    HttpSession session = request.getSession();
//                    session.setAttribute("user", instructor);
//                    session.setAttribute("role", "INSTRUCTOR");
//                    response.sendRedirect("instructor-dashboard");
//                } else {
//                    throw new IllegalArgumentException("Invalid email or password for instructor.");
//                }
//            } else if (role.equals("STUDENT")) {
//                Student student = studentService.getStudentByEmail(email);
//                if (student != null && student.getPassword().equals(password)) {
//                    HttpSession session = request.getSession();
//                    session.setAttribute("user", student);
//                    session.setAttribute("role", "STUDENT");
//                    response.sendRedirect("student-dashboard");
//                } else {
//                    throw new IllegalArgumentException("Invalid email or password for student.");
//                }
//            } else {
//                throw new IllegalArgumentException("Invalid role selected.");
//            }
//        } catch (IllegalArgumentException e) {
//            request.setAttribute("errorMessage", e.getMessage());
//            request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
//        }
//    }
//}





package com.submission.mis.worksubmission.controllers;

import com.submission.mis.worksubmission.models.Instructor;
import com.submission.mis.worksubmission.models.Student;
import com.submission.mis.worksubmission.service.InstructorService;
import com.submission.mis.worksubmission.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final InstructorService instructorService = InstructorService.getInstructorService();
    private final StudentService studentService = StudentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try {
            if (role.equals("INSTRUCTOR")) {
                Instructor instructor = instructorService.getInstructorByEmail(email);
                if (instructor != null && BCrypt.checkpw(password, instructor.getPassword())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", instructor);
                    session.setAttribute("role", "INSTRUCTOR");
                    response.sendRedirect("instructor-dashboard");
                } else {
                    throw new IllegalArgumentException("Invalid email or password for instructor.");
                }
            } else if (role.equals("STUDENT")) {
                Student student = studentService.getStudentByEmail(email);
                if (student != null && BCrypt.checkpw(password, student.getPassword())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", student);
                    session.setAttribute("role", "STUDENT");
                    response.sendRedirect("student-dashboard");
                } else {
                    throw new IllegalArgumentException("Invalid email or password for student.");
                }
            } else {
                throw new IllegalArgumentException("Invalid role selected.");
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
        }
    }
}