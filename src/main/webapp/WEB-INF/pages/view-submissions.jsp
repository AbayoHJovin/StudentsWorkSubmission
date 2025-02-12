<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <title>View Submissions</title>
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <!-- Header -->
    <div class="flex justify-between items-center mb-6">
        <h1 class="text-2xl font-bold">Submissions for ${assignment.title}</h1>
        <a href="${pageContext.request.contextPath}/instructor-dashboard" class="bg-blue-500 text-white px-4 py-2 rounded">Back to dashboard</a>
    </div>

    <!-- Submissions Table -->
    <div class="bg-white p-6 rounded-lg shadow-md">
        <h2 class="text-xl font-semibold mb-4">Submissions</h2>
        <table class="min-w-full bg-white">
            <thead>
            <tr>
                <th class="py-2 px-4 border-b">Student Name</th>
                <th class="py-2 px-4 border-b">Student Email</th>
                <th class="py-2 px-4 border-b">Submitted At</th>
                <th class="py-2 px-4 border-b">Download</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${submissions}" var="submission">
                <tr>
                    <td class="py-2 px-4 border-b">${submission.student.firstName} ${submission.student.lastName}</td>
                    <td class="py-2 px-4 border-b">${submission.student.email}</td>
                    <td class="py-2 px-4 border-b">${submission.submittedAt}</td>
                    <td class="py-2 px-4 border-b">
                        <a href="${submission.filePath}" class="text-blue-500">Download</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>