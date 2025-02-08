<%--
  Created by IntelliJ IDEA.
  User: AYB
  Date: 08/02/2025
  Time: 08:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">

    <title>Title</title>
</head>
<body>
<!-- Submit Assignment (submit-assignment.jsp) -->
<div class="flex items-center justify-center min-h-screen bg-gray-100">
    <div class="bg-white p-6 rounded-lg shadow-md w-96">
        <h2 class="text-2xl font-bold mb-4 text-center">Submit Assignment</h2>
        <form action="SubmissionServlet" method="POST" enctype="multipart/form-data">
            <select name="assignmentId" class="w-full p-2 border rounded mb-3">
                <!-- Dynamic assignment options from the database will be inserted here -->
            </select>
            <input type="file" name="file" class="w-full p-2 border rounded mb-3">
            <button type="submit" class="w-full bg-blue-500 text-white p-2 rounded">Submit</button>
        </form>
    </div>
</div>
</body>
</html>
