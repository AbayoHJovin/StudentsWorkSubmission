package com.submission.mis.worksubmission.controllers;

import com.submission.mis.worksubmission.models.Assignment;
import com.submission.mis.worksubmission.models.Course;
import com.submission.mis.worksubmission.models.Instructor;
import com.submission.mis.worksubmission.service.AssignmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/create-assignment")
public class CreateAssignmentServlet extends HttpServlet {
    private final AssignmentService assignmentService = AssignmentService.getInstance();
    private static final String UPLOAD_DIRECTORY = "uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }

        Instructor instructor = (Instructor) session.getAttribute("user");
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            List<FileItem> formItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest((javax.servlet.http.HttpServletRequest) request);
            String title = null, description = null, deadlineStr = null, courseIdStr = null;
            String fileName = null;

            for (FileItem item : formItems) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case "title":
                            title = item.getString();
                            break;
                        case "description":
                            description = item.getString();
                            break;
                        case "deadline":
                            deadlineStr = item.getString();
                            break;
                        case "courseId":
                            courseIdStr = item.getString();
                            break;
                    }
                } else {
                    fileName = new File(item.getName()).getName();
                    String filePath = uploadPath + File.separator + fileName;
                    item.write(new File(filePath));
                }
            }

            LocalDate deadline = LocalDate.parse(deadlineStr);
            int courseId = Integer.parseInt(courseIdStr);
            Course course = new Course();
            course.setId(courseId);

            Assignment assignment = new Assignment(title, description, deadline, UPLOAD_DIRECTORY + "/" + fileName, instructor, course);
            assignmentService.addAssignment(assignment);

            response.sendRedirect("instructor-dashboard");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Failed to create assignment: " + e.getMessage());
            request.getRequestDispatcher("WEB-INF/pages/instructor-dashboard.jsp").forward(request, response);
        }
    }
}