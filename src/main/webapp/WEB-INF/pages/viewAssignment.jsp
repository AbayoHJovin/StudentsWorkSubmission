<%--
  Created by IntelliJ IDEA.
  User: AYB
  Date: 12/02/2025
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

    <button class="mt-6 px-4 py-2 bg-blue-600 text-white font-semibold rounded-lg hover:bg-blue-700 transition">Back to Dashboard</button>
</div>
</body>
</html>
