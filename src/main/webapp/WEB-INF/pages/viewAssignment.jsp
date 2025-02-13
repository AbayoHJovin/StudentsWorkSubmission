<%--    &lt;%&ndash;--%>
<%--      Created by IntelliJ IDEA.--%>
<%--      User: AYB--%>
<%--      Date: 12/02/2025--%>
<%--      Time: 20:11--%>
<%--      To change this template use File | Settings | File Templates.--%>
<%--    &ndash;%&gt;--%>
<%--    <%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>

<%--    <!DOCTYPE html>--%>
<%--    <html lang="en">--%>
<%--    <head>--%>
<%--        <meta charset="UTF-8">--%>
<%--        <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--        <title>View Assignment</title>--%>
<%--        <script src="https://cdn.tailwindcss.com"></script>--%>
<%--    </head>--%>
<%--    <body class="bg-gray-100 p-6">--%>
<%--    <div class="max-w-3xl mx-auto bg-white p-6 rounded-lg shadow-lg">--%>
<%--        <h1 class="text-2xl font-bold text-gray-800 mb-4">${assignment.title}</h1>--%>
<%--        <p class="text-gray-600 mb-2"><strong>Course:</strong> ${assignment.course.name}</p>--%>
<%--        <p class="text-gray-600 mb-2"><strong>Teacher:</strong> ${assignment.instructor.firstName} ${assignment.instructor.lastName}</p>--%>
<%--        <p class="text-gray-600 mb-2"><strong>Deadline:</strong> ${assignment.deadline}</p>--%>

<%--        <h2 class="text-lg font-semibold text-gray-700 mt-4">Description</h2>--%>
<%--        <p class="text-gray-700 leading-relaxed mt-2"> ${assignment.description}</p>--%>

<%--        <c:if test="${not empty assignment.assignmentLink}">--%>
<%--            <div class="mt-4 p-4 bg-gray-50 border-l-4 border-blue-500">--%>
<%--                <p class="text-blue-700 font-medium">There are additional files attached to this assignment.</p>--%>
<%--            </div>--%>
<%--        </c:if>--%>

<%--        <button class="mt-6 px-4 py-2 bg-blue-600 text-white font-semibold rounded-lg hover:bg-blue-700 transition">Back to Dashboard</button>--%>
<%--    </div>--%>
<%--    </body>--%>
<%--    </html>--%>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Assignment</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 p-6">
<div class="max-w-3xl mx-auto bg-white p-6 rounded-lg shadow-lg">
    <h1 class="text-2xl font-bold text-gray-800 mb-4">${assignment.title}</h1>
    <p class="text-gray-600 mb-2"><strong>Course:</strong> ${assignment.course.name}</p>
    <p class="text-gray-600 mb-2"><strong>Teacher:</strong> ${assignment.instructor.firstName} ${assignment.instructor.lastName}</p>
    <p class="text-gray-600 mb-2"><strong>Deadline:</strong> ${assignment.deadline}</p>

    <h2 class="text-lg font-semibold text-gray-700 mt-4">Description</h2>
    <p class="text-gray-700 leading-relaxed mt-2"> ${assignment.description}</p>

    <c:if test="${not empty assignment.assignmentLink}">
        <div class="mt-4 p-4 bg-gray-50 border-l-4 border-blue-500">
            <p class="text-blue-700 font-medium">There are additional files attached to this assignment.</p>
        </div>
    </c:if>

    <h2 class="text-lg font-semibold text-gray-700 mt-6">Submissions</h2>
    <!-- Above the table -->
    <div class="flex justify-between items-center mb-4">
        <h2 class="text-lg font-semibold text-gray-700">Submissions</h2>
        <a href="downloadAllSubmissions?assignmentId=${assignment.id}" class="px-4 py-2 bg-green-600 text-white font-semibold rounded-lg hover:bg-green-700 transition">
            Download All Submissions
        </a>
    </div>
    <table class="min-w-full bg-white mt-4">
        <thead>
        <tr>
            <th class="py-2 px-4 border-b border-gray-200 bg-gray-50 text-left text-sm font-semibold text-gray-600">Submission ID</th>
            <th class="py-2 px-4 border-b border-gray-200 bg-gray-50 text-left text-sm font-semibold text-gray-600">Student Name</th>
            <th class="py-2 px-4 border-b border-gray-200 bg-gray-50 text-left text-sm font-semibold text-gray-600">Student Email</th>
            <th class="py-2 px-4 border-b border-gray-200 bg-gray-50 text-left text-sm font-semibold text-gray-600">Submission Date</th>
            <th class="py-2 px-4 border-b border-gray-200 bg-gray-50 text-left text-sm font-semibold text-gray-600">Download</th>
        </tr>
        </thead>
        <tbody>
        <!-- Inside the table body -->
        <c:forEach items="${submissions}" var="submission" >
        <tr>
            <td class="py-2 px-4 border-b border-gray-200 text-sm text-gray-700">${submission.id}</td>
            <td class="py-2 px-4 border-b border-gray-200 text-sm text-gray-700">${submission.student.firstName} ${submission.student.lastName}</td>
            <td class="py-2 px-4 border-b border-gray-200 text-sm text-gray-700">${submission.student.email}</td>
            <td class="py-2 px-4 border-b border-gray-200 text-sm text-gray-700">
                <fmt:formatDate value="${submission.submittedAt}" pattern="yyyy-MM-dd HH:mm:ss" />
            </td>
            <td class="py-2 px-4 border-b border-gray-200 text-sm text-gray-700">
                <a href="downloadSubmission?submissionId=${submission.id}" class="text-blue-600 hover:text-blue-800">Download</a>
            </td>
        </tr>
        </c:forEach>
    </table>

    <button class="mt-6 px-4 py-2 bg-blue-600 text-white font-semibold rounded-lg hover:bg-blue-700 transition">Back to Dashboard</button>
</div>
</body>
</html>