<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Work Submission</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50">
<!-- Navbar -->
<nav class="bg-indigo-600 p-4 flex justify-between items-center text-white">
    <div class="text-lg font-bold">Student Submission</div>
    <form action="logout" method="get">
        <button type="submit" class="bg-red-500 px-4 py-2 rounded hover:bg-red-600 transition">
            Logout
        </button>
    </form>
</nav>

<div class="container mx-auto p-4">
    <!-- Student Credentials -->
    <section class="bg-white p-6 rounded mb-6 transition">
        <h2 class="text-xl font-semibold mb-4">Student Information</h2>
        <p><strong>First Name:</strong> ${user.firstName}</p>
        <p><strong>Last Name:</strong> ${user.lastName}</p>
        <p><strong>Email:</strong> ${user.email}</p>
        <p><strong>Birthdate:</strong> ${user.birthDate}</p>
        <button class="bg-indigo-500 text-white px-4 py-2 rounded mt-4 hover:bg-indigo-600 transition">Update</button>
    </section>

    <!-- Recent Assignments -->
    <section class="bg-white p-6 rounded mb-6 transition">
        <h2 class="text-xl font-semibold mb-4">Recent Assignments</h2>
        <div class="flex space-x-4 mb-4">
            <button class="tab-button px-4 py-2 rounded transition bg-indigo-500 text-white" onclick="showTab('unsubmitted', this)">Unsubmitted</button>
            <button class="tab-button px-4 py-2 rounded transition bg-gray-300 text-gray-800" onclick="showTab('submitted', this)">Submitted</button>
        </div>
        <div id="unsubmitted" class="tab-content">
    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
        <c:choose>
            <c:when test="${not empty recentAssignments}">
                <c:forEach items="${recentAssignments}" var="assignment">
                    <div class="relative bg-white shadow-md p-6 rounded-lg group transition hover:shadow-lg hover:bg-gray-100">
                        <h3 class="font-semibold text-lg text-gray-800">${assignment.title}</h3>
                        <p class="text-sm text-gray-600 mt-1">${assignment.description}</p>
                        <p class="text-sm text-gray-600 mt-1">
                            <strong>Deadline:</strong> ${assignment.deadline}
                        </p>

                        <!-- Hidden buttons, shown on hover -->
                        <div class="absolute bottom-4 right-4 opacity-0 group-hover:opacity-100 transition flex space-x-2">
<%--                            <form action="download-assignment?assignmentId=${assignment.id}" method="get">--%>
                                <a href="download-assignment?assignmentId=${assignment.id}" class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 transition">
                                    Download
                                </a>
<%--                            </form>--%>

                            <a href="${pageContext.request.contextPath}/submitAssignment?assignmentId=${assignment.id}" class="bg-indigo-500 text-white px-4 py-2 rounded hover:bg-indigo-600 transition">
                                Submit
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p class="text-gray-500">No assignments available.</p>
            </c:otherwise>
        </c:choose>
    </div>

        </div>
        <div id="submitted" class="tab-content hidden">
            <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
                <div class="bg-gray-200 p-4 rounded relative group hover:bg-gray-300 transition">
                    <h3 class="font-semibold">Assignment 2</h3>
                    <p>Course: Physics 202</p>
                    <p>Instructor: Dr. Johnson</p>
                    <p><strong>Deadline:</strong> 2025-03-20</p>
                    <div class="absolute top-4 right-4 bg-green-500 text-white px-3 py-1 rounded">✔ Submitted</div>
                    <button class="absolute bottom-4 right-4 bg-green-500 text-white px-3 py-2 rounded opacity-0 group-hover:opacity-100 transition">Download</button>
                </div>
            </div>
        </div>
    </section>

    <!-- Courses Taken -->
    <section class="bg-white p-6 rounded  transition">
        <h2 class="text-xl font-semibold mb-4">Courses Taken</h2>
    <ul class="space-y-2">
    <c:forEach items="${courses}" var="course">
        <li class="mb-4 p-4 bg-gray-50 rounded hover:bg-gray-100 transition cursor-pointer">
        <h3 class="font-bold text-2xl">${course.name}</h3>
            <p class="text-sm text-gray-600 font-semibold">Instructors:</p>
        <ul class="ml-4">
        <c:forEach items="${course.instructors}" var="instructor">
            <li class="mb-4 p-4 rounded transition">
            <li class="mb-4 p-4 rounded transition">
                <p>${instructor.firstName} ${instructor.lastName}</p>
                <p>${instructor.email}</p>
            </li>
        </c:forEach>
        </li>
    </c:forEach>
        </ul>
    </section>
</div>

<script>
    function showTab(tabId, button) {
        document.querySelectorAll('.tab-content').forEach(tab => {
            tab.classList.add('hidden');
        });
        document.getElementById(tabId).classList.remove('hidden');
        document.querySelectorAll('.tab-button').forEach(btn => {
            btn.classList.remove('bg-indigo-500', 'text-white');
            btn.classList.add('bg-gray-300', 'text-gray-800');
        });
        button.classList.add('bg-indigo-500', 'text-white');
        button.classList.remove('bg-gray-300', 'text-gray-800');
    }
</script>
</body>
</html>
