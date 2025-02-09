<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">--%>
<%--    <title>Student Dashboard</title>--%>
<%--</head>--%>
<%--<body class="bg-gray-100">--%>
<%--<div class="container mx-auto p-4">--%>
<%--    <h1 class="text-2xl font-bold mb-4">Welcome, ${user.firstName} ${user.lastName}</h1>--%>
<%--    <div class="grid grid-cols-1 md:grid-cols-3 gap-4">--%>
<%--        <c:forEach items="${user.courses}" var="course">--%>
<%--            <div class="bg-white p-4 rounded shadow">--%>
<%--                <h2 class="text-xl font-semibold">${course.name}</h2>--%>
<%--                <p>Instructor: ${course.instructor.firstName} ${course.instructor.lastName}</p>--%>
<%--                <c:forEach items="${course.assignments}" var="assignment">--%>
<%--                    <div class="mt-2">--%>
<%--                        <h3 class="font-semibold">${assignment.title}</h3>--%>
<%--                        <p>${assignment.description}</p>--%>
<%--                        <p>Deadline: ${assignment.deadline}</p>--%>
<%--                        <a href="${assignment.assignmentLink}" class="text-blue-500">Download</a>--%>
<%--                        <form action="submit-assignment" method="POST" enctype="multipart/form-data">--%>
<%--                            <input type="hidden" name="assignmentId" value="${assignment.id}">--%>
<%--                            <input type="file" name="file" class="w-full p-2 border rounded mb-2">--%>
<%--                            <button type="submit" class="bg-blue-500 text-white p-2 rounded">Submit</button>--%>
<%--                        </form>--%>
<%--                    </div>--%>
<%--                </c:forEach>--%>
<%--            </div>--%>
<%--        </c:forEach>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <title>Student Dashboard</title>
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <!-- Header -->
    <div class="flex justify-between items-center mb-6">
        <h1 class="text-2xl font-bold">Welcome, ${user.firstName} ${user.lastName}</h1>
        <a href="logout" class="bg-red-500 text-white px-4 py-2 rounded">Logout</a>
    </div>

    <!-- Courses Section -->
    <div class="bg-white p-6 rounded-lg shadow-md mb-6">
        <h2 class="text-xl font-semibold mb-4">Your Courses</h2>
        <ul class="space-y-2">
            <c:forEach items="${user.courses}" var="course">
                <li class="p-4 bg-gray-50 rounded-lg">
                    <h3 class="font-semibold">${course.name}</h3>
                    <p class="text-sm text-gray-600">Instructor: ${course.instructor.firstName} ${course.instructor.lastName}</p>
                    <p class="text-sm text-gray-600">Email: ${course.instructor.email}</p>

                    <!-- Assignments for the Course -->
                    <div class="mt-2">
                        <h4 class="font-semibold">Assignments</h4>
                        <c:forEach items="${course.assignments}" var="assignment">
                            <div class="p-3 bg-gray-100 rounded-lg mt-2">
                                <h5 class="font-medium">${assignment.title}</h5>
                                <p class="text-sm text-gray-600">${assignment.description}</p>
                                <p class="text-sm text-gray-600">Deadline: ${assignment.deadline}</p>
                                <a href="${assignment.assignmentLink}" class="text-blue-500 text-sm">Download Assignment</a>

                                <form action="submit-assignment" method="POST" enctype="multipart/form-data" class="mt-2">
                                    <input type="hidden" name="assignmentId" value="${assignment.id}">
                                    <input type="file" name="file" class="w-full p-2 border rounded mb-2" required>
                                    <button type="submit" class="bg-green-500 text-white px-3 py-1 rounded">Submit Work</button>
                                </form>
                            </div>
                        </c:forEach>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>