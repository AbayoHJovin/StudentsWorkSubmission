<%--
  Created by IntelliJ IDEA.
  User: AYB
  Date: 08/02/2025
  Time: 08:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <title>Title</title>
</head>
<body>
<!-- Dashboard (dashboard.jsp) -->
<div class="min-h-screen flex flex-col">
    <nav class="bg-blue-600 text-white p-4 flex justify-between">
        <h1 class="text-xl">Dashboard</h1>
        <a href="LogoutServlet" class="bg-red-500 px-3 py-1 rounded">Logout</a>
    </nav>
    <div class="p-6">
        <h2 class="text-2xl font-bold">Welcome, <%= session.getAttribute("userName") %></h2>
        <% if (session.getAttribute("role").equals("INSTRUCTOR")) { %>
        <a href="createAssignment.jsp" class="bg-green-500 text-white p-2 rounded inline-block mt-4">Create Assignment</a>
        <% } else { %>
        <a href="submit-assignment.jsp" class="bg-blue-500 text-white p-2 rounded inline-block mt-4">Submit Assignment</a>
        <% } %>
    </div>
</div>
</body>
</html>
