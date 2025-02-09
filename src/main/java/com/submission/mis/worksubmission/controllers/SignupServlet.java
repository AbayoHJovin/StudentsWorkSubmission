package com.submission.mis.worksubmission.controllers;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.*;

import com.submission.mis.worksubmission.models.Course;
import com.submission.mis.worksubmission.models.Instructor;
import com.submission.mis.worksubmission.models.Student;
import com.submission.mis.worksubmission.service.CourseService;
import com.submission.mis.worksubmission.service.InstructorService;
import com.submission.mis.worksubmission.service.StudentService;
import com.submission.mis.worksubmission.util.GetAge;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

public class SignupServlet extends HttpServlet {
    StudentService studentService = StudentService.getInstance();
    InstructorService instructorService = InstructorService.getInstructorService();
    CourseService courseService = CourseService.getCourseService();  // Add CourseService

    // Regular expression for email and phone number validation
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_REGEX = "^(\\+2507[238]\\d{7}|07[238]\\d{7})$";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("formName", "Student Registration");
        request.getRequestDispatcher("WEB-INF/pages/signup.jsp").forward(request, response);
    }
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    PrintWriter out = response.getWriter();
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String role = request.getParameter("role");

    try {
        // Validate email format
        if (email == null || email.isEmpty() || !email.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        // Validate password (you can add more specific validation if needed)
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        if (role.equals("STUDENT")) {
            if (studentService.isStudentEmailExists(email)) {
                throw new IllegalArgumentException("A student with this email already exists.");
            }
            String birthDateStr = request.getParameter("birthDate");
            if (birthDateStr == null || birthDateStr.isEmpty()) {
                throw new IllegalArgumentException("Birth date is required.");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
            int age = GetAge.calculateAge(birthDateStr);

            Student student = new Student(firstName, lastName, email, password, birthDate, age);
            studentService.addStudent(student);
            response.sendRedirect("success-servlet");

        } else if (role.equals("INSTRUCTOR")) {
            String phoneNumber = request.getParameter("tel");
            if (phoneNumber == null || phoneNumber.isEmpty()) {
                throw new IllegalArgumentException("Phone number is required.");
            }

            if (!phoneNumber.matches(PHONE_REGEX)) {
                throw new IllegalArgumentException("Invalid phone number format. Please enter a valid Rwanda phone number.");
            }

            if (instructorService.isInstructorEmailExists(email)) {
                throw new IllegalArgumentException("An instructor with this email already exists.");
            }
            if(instructorService.isInstructorPhoneExists(phoneNumber)){
                throw new IllegalArgumentException("An Instructor with this phone number already exists!");
            }

            String[] selectedCourses = request.getParameterValues("courses");
            if (selectedCourses == null || selectedCourses.length == 0) {
                throw new IllegalArgumentException("At least one course must be selected for the instructor.");
            }

            Instructor instructor = new Instructor(firstName, lastName, email, password, phoneNumber);

            List<Course> coursesList = new ArrayList<>();

            for (String courseName : selectedCourses) {
                Course course = courseService.getCourseByName(courseName);
                if (course == null) {
                    course = new Course();
                    course.setName(courseName);
                    courseService.addCourse(course);
                }
                coursesList.add(course);
            }
            instructor.setCourses(coursesList);  // Set the courses for the instructor
            instructorService.addInstructor(instructor);  // Add instructor first
//            instructorService.updateInstructor(instructor);  // Then update instructor with courses
            response.sendRedirect("success-servlet");

        } else {
            throw new IllegalArgumentException("Invalid role selected.");
        }

    } catch (IllegalArgumentException e) {
        out.println("Error: " + e.getMessage());
        request.setAttribute("errorMessage", e.getMessage());
        request.getRequestDispatcher("WEB-INF/pages/signup.jsp").forward(request, response);
    } catch (Exception e) {
        e.printStackTrace();
        out.println("An unexpected error occurred: " + e.getMessage());
        System.out.println("An unexpected error occurred" + e.getMessage());
        request.setAttribute("errorMessage", "An unexpected error occurred. Please try again.");
        request.getRequestDispatcher("WEB-INF/pages/signup.jsp").forward(request, response);
    }
}

    public void destroy() {
    }
}
