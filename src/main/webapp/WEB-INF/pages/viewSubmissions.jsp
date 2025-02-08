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
<!-- View Submissions (view-submissions.jsp) -->
<div class="p-6">
  <h2 class="text-2xl font-bold mb-4">Student Submissions</h2>
  <table class="min-w-full bg-white shadow-md rounded-lg">
    <thead>
    <tr class="bg-gray-200">
      <th class="py-2 px-4">Student</th>
      <th class="py-2 px-4">Assignment</th>
      <th class="py-2 px-4">Submission</th>
    </tr>
    </thead>
    <tbody>
    <!-- Dynamic rows from database will be inserted here -->
    </tbody>
  </table>
</div>
</body>
</html>
