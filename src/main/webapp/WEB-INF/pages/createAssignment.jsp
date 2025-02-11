<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <title>Create Assignment</title>
</head>
<body class="bg-gray-100">

<!-- Create Assignment Form -->
<div class="flex items-center justify-center min-h-screen px-4">
    <div class="bg-white p-6 rounded-lg shadow-md w-full max-w-2xl">
        <h2 class="text-2xl font-bold mb-6 text-center">Create Assignment</h2>

        <!-- Error Messages -->
        <c:if test="${not empty errors}">
            <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
                <h2 class="font-bold">Validation Errors:</h2>
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error.key}: ${error.value}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <!-- Success Message -->
        <c:if test="${not empty successMessage}">
            <div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-4">
                    ${successMessage}
            </div>
        </c:if>

        <form action="create-assignment" method="POST" enctype="multipart/form-data">
            <!-- Title Input -->
            <div class="mb-4">
                <label for="title" class="block text-gray-700 font-semibold mb-1">Title</label>
                <input type="text" id="title" name="title" placeholder="Enter assignment title"
                       class="w-full p-2 border rounded focus:ring focus:ring-green-300">
                <c:if test="${not empty errors.title}">
                    <p class="text-red-500 text-sm">${errors.title}</p>
                </c:if>
            </div>

            <!-- Description Input -->
            <div class="mb-4">
                <label for="description" class="block text-gray-700 font-semibold mb-1">Description</label>
                <textarea id="description" name="description" placeholder="Enter assignment description"
                          class="w-full p-2 border rounded focus:ring focus:ring-green-300"></textarea>
                <c:if test="${not empty errors.description}">
                    <p class="text-red-500 text-sm">${errors.description}</p>
                </c:if>
            </div>

            <!-- Deadline Input -->
            <div class="mb-4">
                <label for="deadline" class="block text-gray-700 font-semibold mb-1">Deadline</label>
                <input type="date" id="deadline" name="deadline"
                       class="w-full p-2 border rounded focus:ring focus:ring-green-300">
                <c:if test="${not empty errors.deadline}">
                    <p class="text-red-500 text-sm">${errors.deadline}</p>
                </c:if>
            </div>

            <!-- Attach File Input -->
            <div class="mb-4">
                <label for="file" class="block text-gray-700 font-semibold mb-1">Attach File (Optional)</label>
                <input type="file" id="file" name="file" class="w-full p-2 border rounded cursor-pointer bg-gray-50">
                <c:if test="${not empty errors.file}">
                    <p class="text-red-500 text-sm">${errors.file}</p>
                </c:if>
            </div>

            <!-- Hidden Course ID Input -->
            <input type="hidden" id="courseId" name="courseId" value="${param.courseId}">

            <!-- Submit Button -->
            <button type="submit"
                    class="w-full bg-green-500 text-white p-2 rounded hover:bg-green-600 transition">
                Create Assignment
            </button>
        </form>
    </div>
</div>

</body>
</html>