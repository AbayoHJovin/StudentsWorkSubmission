//package com.submission.mis.worksubmission.controllers;
//
//import com.submission.mis.worksubmission.models.Instructor;
//import com.submission.mis.worksubmission.service.AssignmentService;
//import com.submission.mis.worksubmission.service.InstructorService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//
//import java.io.IOException;
//
//import static com.submission.mis.worksubmission.util.HibernateUtil.getSessionFactory;
//public class InstructorDashboardServlet extends HttpServlet {
//    private final InstructorService instructorService = InstructorService.getInstructorService();
//    private final AssignmentService assignmentService = AssignmentService.getInstance();
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("user") == null) {
//            response.sendRedirect("login");
//            return;
//        }
//
//        Instructor instructor = (Instructor) session.getAttribute("user");
//         SessionFactory sess=getSessionFactory();
//        try (Session hibernateSession = sess.openSession()) {
//            instructor = hibernateSession.createQuery(
//                            "SELECT i FROM Instructor i " +
//                                    "LEFT JOIN FETCH i.courses " + // Eagerly fetch the courses
//                                    "WHERE i.id = :id", Instructor.class)
//                    .setParameter("id", instructor.getId())
//                    .uniqueResult();
//        }
//
//        request.setAttribute("user", instructor); // Pass the instructor to the JSP
//        request.getRequestDispatcher("WEB-INF/pages/trDashboard.jsp").forward(request, response);
//    }
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response); // Handle POST requests the same way as GET requests
//    }
//}





package com.submission.mis.worksubmission.controllers;

import com.submission.mis.worksubmission.models.Course;
import com.submission.mis.worksubmission.models.Instructor;
import com.submission.mis.worksubmission.service.AssignmentService;
import com.submission.mis.worksubmission.service.CourseService;
import com.submission.mis.worksubmission.service.InstructorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.submission.mis.worksubmission.util.HibernateUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/instructor-dashboard")
public class InstructorDashboardServlet extends HttpServlet {
    private final InstructorService instructorService = InstructorService.getInstructorService();
    private final AssignmentService assignmentService = AssignmentService.getInstance();
    private final CourseService courseService = CourseService.getCourseService();
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }

        // Get the logged-in instructor from the session
        Instructor instructor = (Instructor) session.getAttribute("user");

        // Fetch the instructor's details, courses, and assignments
        try (Session hibernateSession = sessionFactory.openSession()) {
            instructor = hibernateSession.createQuery(
                            "SELECT i FROM Instructor i " +
                                    "LEFT JOIN FETCH i.courses " + // Eagerly fetch the courses
                                    "WHERE i.id = :id", Instructor.class)
                    .setParameter("id", instructor.getId())
                    .uniqueResult();

            // Fetch the number of students enrolled in each course
            Map<Course, Integer> studentCounts = new HashMap<>();
            for (Course course : instructor.getCourses()) {
                int count = hibernateSession.createQuery(
                                "SELECT COUNT(s) FROM Student s " +
                                        "JOIN s.courses c " +
                                        "WHERE c.id = :courseId", Long.class)
                        .setParameter("courseId", course.getId())
                        .uniqueResult()
                        .intValue();
                studentCounts.put(course, count);
            }

            request.setAttribute("user", instructor); // Pass the instructor to the JSP
            request.setAttribute("studentCounts", studentCounts); // Pass the student counts to the JSP
            request.setAttribute("assignments", assignmentService.getAssignmentsByInstructor(instructor.getId()));
        }

        request.getRequestDispatcher("WEB-INF/pages/instructor-dashboard.jsp").forward(request, response);
    }
}