<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">--%>
<%--    <title>Instructor Dashboard</title>--%>
<%--</head>--%>
<%--<body class="bg-gray-100">--%>
<%--<div class="container mx-auto p-4">--%>
<%--    <!-- Header -->--%>
<%--    <div class="flex justify-between items-center mb-6">--%>
<%--        <h1 class="text-2xl font-bold">Welcome, ${user.firstName} ${user.lastName}</h1>--%>
<%--        <a href="logout" class="bg-red-500 text-white px-4 py-2 rounded">Logout</a>--%>
<%--    </div>--%>

<%--    <!-- Courses Section -->--%>
<%--    <div class="bg-white p-6 rounded-lg shadow-md mb-6">--%>
<%--        <h2 class="text-xl font-semibold mb-4">Your Courses</h2>--%>
<%--        <ul class="space-y-2">--%>
<%--            <c:forEach items="${user.courses}" var="course">--%>
<%--                <li class="p-2 bg-gray-50 rounded">${course.name}</li>--%>
<%--            </c:forEach>--%>
<%--        </ul>--%>
<%--    </div>--%>

<%--    <!-- Assignments Section -->--%>
<%--    <div class="bg-white p-6 rounded-lg shadow-md">--%>
<%--        <h2 class="text-xl font-semibold mb-4">Your Assignments</h2>--%>
<%--        <c:forEach items="${assignments}" var="assignment">--%>
<%--            <div class="p-4 bg-gray-50 rounded-lg mb-4">--%>
<%--                <h3 class="font-semibold">${assignment.title}</h3>--%>
<%--                <p class="text-sm text-gray-600">${assignment.description}</p>--%>
<%--                <p class="text-sm text-gray-600">Deadline: ${assignment.deadline}</p>--%>
<%--                <div class="mt-2 space-x-2">--%>
<%--                    <a href="edit-assignment?id=${assignment.id}" class="bg-blue-500 text-white px-3 py-1 rounded">Edit</a>--%>
<%--                    <a href="delete-assignment?id=${assignment.id}" class="bg-red-500 text-white px-3 py-1 rounded">Delete</a>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </c:forEach>--%>
<%--    </div>--%>

<%--    <!-- Create Assignment Form -->--%>
<%--    <div class="mt-6 bg-white p-6 rounded-lg shadow-md">--%>
<%--        <h2 class="text-xl font-semibold mb-4">Create New Assignment</h2>--%>
<%--        <form action="create-assignment" method="POST">--%>
<%--            <div class="mb-4">--%>
<%--                <label for="title" class="block text-sm font-medium text-gray-700">Title</label>--%>
<%--                <input type="text" name="title" id="title" class="w-full p-2 border rounded" required>--%>
<%--            </div>--%>
<%--            <div class="mb-4">--%>
<%--                <label for="description" class="block text-sm font-medium text-gray-700">Description</label>--%>
<%--                <textarea name="description" id="description" class="w-full p-2 border rounded" required></textarea>--%>
<%--            </div>--%>
<%--            <div class="mb-4">--%>
<%--                <label for="deadline" class="block text-sm font-medium text-gray-700">Deadline</label>--%>
<%--                <input type="date" name="deadline" id="deadline" class="w-full p-2 border rounded" required>--%>
<%--            </div>--%>
<%--            <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded">Create Assignment</button>--%>
<%--        </form>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>




<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <title>Instructor Dashboard</title>
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <!-- Header -->
    <div class="flex justify-between items-center mb-6">
        <h1 class="text-2xl font-bold">Welcome, ${user.firstName} ${user.lastName}</h1>
        <a href="logout" class="bg-red-500 text-white px-4 py-2 rounded">Logout</a>
    </div>

    <!-- Courses Section -->
<%--    <div class="bg-white p-6 rounded-lg shadow-md mb-6">--%>
<%--        <h2 class="text-xl font-semibold mb-4">Your Courses</h2>--%>
<%--        <ul class="space-y-2">--%>
<%--            <c:forEach items="${user.courses}" var="course">--%>
<%--                <li class="p-4 bg-gray-50 rounded-lg">--%>
<%--                    <h3 class="font-semibold">${course.name}</h3>--%>
<%--                    <p class="text-sm text-gray-600">Students Enrolled: ${studentCounts.get(course)}</p>--%>
<%--                    <a href="view-assignments?courseId=${course.id}" class="text-blue-500 text-sm">View Assignments</a>--%>
<%--                </li>--%>
<%--            </c:forEach>--%>
<%--        </ul>--%>
<%--    </div>--%>
    <!-- Courses Section -->
    <div class="bg-white p-6 rounded-lg shadow-md mb-6">
        <h2 class="text-xl font-semibold mb-4">Your Courses</h2>
        <ul class="space-y-2">
            <c:forEach items="${user.courses}" var="course">
                <li class="p-4 bg-gray-50 rounded-lg">
                    <h3 class="font-semibold">${course.name}</h3>
                    <p class="text-sm text-gray-600">Students Enrolled: ${studentCounts.get(course)}</p>
                    <a href="view-assignments?courseId=${course.id}" class="text-blue-500 text-sm">View Assignments</a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <!-- Assignments Section -->
    <div class="bg-white p-6 rounded-lg shadow-md">
        <h2 class="text-xl font-semibold mb-4">Your Assignments</h2>
        <c:forEach items="${assignments}" var="assignment">
            <div class="p-4 bg-gray-50 rounded-lg mb-4">
                <h3 class="font-semibold">${assignment.title}</h3>
                <p class="text-sm text-gray-600">${assignment.description}</p>
                <p class="text-sm text-gray-600">Deadline: ${assignment.deadline}</p>
                <div class="mt-2 space-x-2">
                    <a href="view-submissions?assignmentId=${assignment.id}" class="bg-blue-500 text-white px-3 py-1 rounded">View Submissions</a>
                    <a href="edit-assignment?id=${assignment.id}" class="bg-yellow-500 text-white px-3 py-1 rounded">Edit</a>
                    <a href="delete-assignment?id=${assignment.id}" class="bg-red-500 text-white px-3 py-1 rounded">Delete</a>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- Assignments Section -->
    <div class="bg-white p-6 rounded-lg shadow-md">
        <h2 class="text-xl font-semibold mb-4">Your Assignments</h2>
        <c:forEach items="${assignments}" var="assignment">
            <div class="p-4 bg-gray-50 rounded-lg mb-4">
                <h3 class="font-semibold">${assignment.title}</h3>
                <p class="text-sm text-gray-600">${assignment.description}</p>
                <p class="text-sm text-gray-600">Deadline: ${assignment.deadline}</p>
                <div class="mt-2 space-x-2">
                    <a href="view-submissions?assignmentId=${assignment.id}" class="bg-blue-500 text-white px-3 py-1 rounded">View Submissions</a>
                    <a href="edit-assignment?id=${assignment.id}" class="bg-blue-500 text-white px-3 py-1 rounded">Edit</a>
                    <a href="delete-assignment?id=${assignment.id}" class="bg-red-500 text-white px-3 py-1 rounded">Delete</a>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- Create Assignment Form -->
    <div class="mt-6 bg-white p-6 rounded-lg shadow-md">
        <h2 class="text-xl font-semibold mb-4">Create New Assignment</h2>
        <form action="create-assignment" method="POST" enctype="multipart/form-data">
            <div class="mb-4">
                <label for="title" class="block text-sm font-medium text-gray-700">Title</label>
                <input type="text" name="title" id="title" class="w-full p-2 border rounded" required>
            </div>
            <div class="mb-4">
                <label for="description" class="block text-sm font-medium text-gray-700">Description</label>
                <textarea name="description" id="description" class="w-full p-2 border rounded" required></textarea>
            </div>
            <div class="mb-4">
                <label for="deadline" class="block text-sm font-medium text-gray-700">Deadline</label>
                <input type="date" name="deadline" id="deadline" class="w-full p-2 border rounded" required>
            </div>
            <div class="mb-4">
                <label for="assignmentFile" class="block text-sm font-medium text-gray-700">Assignment File</label>
                <input type="file" name="assignmentFile" id="assignmentFile" class="w-full p-2 border rounded" required>
            </div>
            <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded">Create Assignment</button>
        </form>
    </div>
</div>
</body>
</html>