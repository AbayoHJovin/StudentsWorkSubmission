<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <title>View Assignments</title>
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <!-- Header -->
    <div class="flex justify-between items-center mb-6">
        <h1 class="text-2xl font-bold">Assignments for ${course.name}</h1>
        <a href="instructor-dashboard" class="bg-blue-500 text-white px-4 py-2 rounded">Back to Dashboard</a>
    </div>

    <!-- Assignments List -->
    <div class="bg-white p-6 rounded-lg shadow-md">
        <h2 class="text-xl font-semibold mb-4">Assignments</h2>
        <ul class="space-y-2">
            <c:forEach items="${assignments}" var="assignment">
                <li class="p-4 bg-gray-50 rounded-lg">
                    <h3 class="font-semibold">${assignment.title}</h3>
                    <p class="text-sm text-gray-600">${assignment.description}</p>
                    <p class="text-sm text-gray-600">Deadline: ${assignment.deadline}</p>
                    <div class="mt-2 space-x-2">
                        <a href="view-submissions?assignmentId=${assignment.id}" class="bg-blue-500 text-white px-3 py-1 rounded">View Submissions</a>
                        <a href="edit-assignment?id=${assignment.id}" class="bg-yellow-500 text-white px-3 py-1 rounded">Edit</a>
                        <a href="delete-assignment?id=${assignment.id}" class="bg-red-500 text-white px-3 py-1 rounded">Delete</a>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>