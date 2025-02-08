<%--
  Created by IntelliJ IDEA.
  User: AYB
  Date: 08/02/2025
  Time: 08:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
  <title>Title</title>
</head>
<body>
<!-- Create Assignment (create-assignment.jsp) -->
<div class="flex items-center justify-center min-h-screen bg-gray-100">
  <div class="bg-white p-6 rounded-lg shadow-md w-96">
    <h2 class="text-2xl font-bold mb-4 text-center">Create Assignment</h2>
    <form action="AssignmentServlet" method="POST">
      <input type="text" name="title" placeholder="Title" class="w-full p-2 border rounded mb-3">
      <textarea name="description" placeholder="Description" class="w-full p-2 border rounded mb-3"></textarea>
      <input type="datetime-local" name="deadline" class="w-full p-2 border rounded mb-3">
      <button type="submit" class="w-full bg-green-500 text-white p-2 rounded">Create</button>
    </form>
  </div>
</div>
</body>
</html>
