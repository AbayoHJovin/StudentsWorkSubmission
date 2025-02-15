//package com.submission.mis.worksubmission.controllers;
//
//import com.submission.mis.worksubmission.models.Student;
//import com.submission.mis.worksubmission.service.AssignmentService;
//import com.submission.mis.worksubmission.service.StudentService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//
//public class StudentDashboardServlet extends HttpServlet {
//    private final StudentService studentService = StudentService.getInstance();
//    private final AssignmentService assignmentService = AssignmentService.getInstance();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("user") == null) {
//            // Redirect to login if the user is not logged in
//            response.sendRedirect("login");
//            return;
//        }
//
//        // Get the logged-in student from the session
//        Student student = (Student) session.getAttribute("user");
//
//        // Fetch the student's details and courses
//        student = studentService.getStudentByEmail(student.getEmail()); // Refresh the student object
//        request.setAttribute("user", student); // Pass the student to the JSP
//
//        // Forward to the student dashboard JSP
//        request.getRequestDispatcher("WEB-INF/pages/studentDash.jsp").forward(request, response);
//    }
//
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response); // Handle POST requests the same way as GET requests
//    }
//}




package com.submission.mis.worksubmission.controllers;

import com.submission.mis.worksubmission.models.Assignment;
import com.submission.mis.worksubmission.models.Course;
import com.submission.mis.worksubmission.models.Instructor;
import com.submission.mis.worksubmission.models.Student;
import com.submission.mis.worksubmission.service.AssignmentService;
import com.submission.mis.worksubmission.service.CourseService;
import com.submission.mis.worksubmission.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class StudentDashboardServlet extends HttpServlet {
    private final StudentService studentService = StudentService.getInstance();
    private final AssignmentService assignmentService = AssignmentService.getInstance();
    private final CourseService courseService = CourseService.getCourseService();
    private static final Logger logger = LoggerFactory.getLogger(StudentDashboardServlet.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }

        // Get the logged-in student from the session
        Student student = (Student) session.getAttribute("user");
        List<Assignment> assignments = assignmentService.getRecentAssignments();

        // Fetch all courses with their instructors
        List<Course> courses = courseService.getAllCoursesWithInstructors();
//        for (Assignment assignment : assignments) {
//            // Find the course that matches the assignment's courseId
//            Course course = courses.stream()
//                    .filter(c -> c.getId() == assignment.getCourse()) // Assuming Assignment has getCourseId()
//                    .findFirst()
//                    .orElse(null);
//
//            String courseName = (course != null) ? course.getName() : "Unknown Course";
//
//            logger.info("Assignment Title: {}, Deadline: {}, Course: {}",
//                    assignment.getTitle(), assignment.getDeadline(), courseName);
//        }


        // Pass the student and courses to the JSP
        request.setAttribute("user", student);
        request.setAttribute("courses", courses);
        request.setAttribute("recentAssignments", assignments);

        // Forward to the student dashboard JSP
        request.getRequestDispatcher("WEB-INF/pages/studentDash.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}