<%--
  Created by IntelliJ IDEA.
  User: AYB
  Date: 08/02/2025
  Time: 08:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <!-- Include TailwindCSS CDN -->
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <title>Title</title>
</head>
<body>
<!-- Login Page (login.jsp) -->
<div class="flex items-center justify-center min-h-screen bg-gray-100">
    <div class="bg-white p-6 rounded-lg shadow-md w-96">
        <h2 class="text-2xl font-bold mb-4 text-center">Login</h2>
        <%
            String successMessage = (String) request.getAttribute("successMessage");
            String errorMessage = (String) request.getAttribute("errorMessage");

            if (successMessage != null) {
        %>
        <div style="background-color: green; color: white; padding: 10px;">
            <%= successMessage %>
        </div>
        <%
            }
            if (errorMessage != null) {
        %>
        <div style="background-color: red; color: white; padding: 10px;">
            <%= errorMessage %>
        </div>
        <%
            }
        %>
        <form action="loginUser" method="POST">
            <input type="email" name="email" placeholder="Email" class="w-full p-2 border rounded mb-3">
            <input type="password" name="password" placeholder="Password" class="w-full p-2 border rounded mb-3">
            <select name="role" class="w-full p-2 border rounded mb-3">
                <option value="STUDENT">Student</option>
                <option value="INSTRUCTOR">Instructor</option>
            </select>
            <button type="submit" class="w-full bg-blue-500 text-white p-2 rounded">Login</button>
            <h1>Don't have an account?
                <span><a href="${pageContext.request.contextPath}/signup">Signup</a></span>
            </h1>
        </form>
    </div>
</div>

</body>
</html>



