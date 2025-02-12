<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Instructor Dashboard</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-white">
<!-- Navbar -->
<nav class="bg-indigo-600 p-4 flex justify-between items-center text-white">
    <div class="text-lg font-bold">Instructor Dashboard</div>
    <form action="logout" method="get">
        <button type="submit" class="bg-red-500 px-4 py-2 rounded hover:bg-red-600 transition">
            Logout
        </button>
    </form>
</nav>

<div class="container mx-auto p-4">
    <!-- Instructor Information -->
    <section class="bg-white p-6 rounded mb-6">
        <h2 class="text-xl font-semibold mb-4">Instructor Information</h2>
        <p><strong>First Name:</strong> ${user.firstName}</p>
        <p><strong>Last Name:</strong> ${user.lastName}</p>
        <p><strong>Email:</strong> ${user.email}</p>
        <p><strong>Phone Number:</strong> ${user.phoneNumber}</p>
    </section>
    <div class="bg-white p-4 rounded-lg mb-6">
        <h2 class="text-lg font-semibold">Total Students: <span class="text-blue-600">${studentCounts}</span></h2>
    </div>
    <div class="bg-white p-6 rounded-lg">
        <h2 class="text-xl font-semibold mb-4">Your Assignments</h2>
        <c:choose>
            <c:when test="${not empty assignments}">
                <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
                    <c:forEach items="${assignments}" var="assignment">
                        <div class="p-4 bg-gray-50 rounded-lg hover:bg-gray-100 transition">
                            <h3 class="font-semibold">${assignment.title}</h3>
                            <p class="text-sm text-gray-600">${assignment.description}</p>
                            <p class="text-sm text-gray-600"><strong>Deadline:</strong> ${assignment.deadline}</p>
                            <div class="mt-2 space-x-2">
                                <a href="view-submissions?assignmentId=${assignment.id}" class="bg-blue-500 text-white px-3 py-1 rounded">View Submissions</a>
                                <a href="edit-assignment?id=${assignment.id}" class="bg-yellow-500 text-white px-3 py-1 rounded">Edit</a>
                                <a href="delete-assignment?id=${assignment.id}" class="bg-red-500 text-white px-3 py-1 rounded">Delete</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <p class="text-gray-500">No assignments available.</p>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Your Courses Section -->
    <div class="bg-white p-6 my-10 rounded-lg mb-6">
        <div class="flex justify-start items-center mb-4">
            <h2 class="text-xl font-semibold">Your Courses</h2>
            <%--            <a href="add-course" class="bg-indigo-500 text-white px-4 py-2 rounded hover:bg-indigo-600 transition">Add Course</a>--%>
        </div>
        <c:choose>
            <c:when test="${not empty user.courses}">
                <ul class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
                    <c:forEach items="${user.courses}" var="course">
                        <li class="p-4 bg-gray-50 rounded-lg hover:bg-gray-100 transition flex flex-col md:flex-row md:justify-between items-start md:items-center">
                            <div>
                                <h3 class="font-semibold text-2xl">${course.name}</h3>
                                <p>
                                    <a href="view-assignments?courseId=${course.id}" class="text-blue-500 hover:underline">View Assignments</a>
                                </p>
                            </div>
                            <div class="mt-3 md:mt-0">
                                <a href="create-assignment?courseId=${course.id}" class="bg-indigo-500 text-white px-4 py-2 rounded hover:bg-indigo-600 transition text-sm md:text-base">
                                    Add Assignment
                                </a>
                            </div>
                        </li>

                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <p class="text-gray-500">No courses available.</p>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
