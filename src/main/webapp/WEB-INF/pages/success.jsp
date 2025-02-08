<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <title>Signup Successful</title>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
<div class="bg-white p-8 rounded-lg shadow-md text-center">
    <h2 class="text-2xl font-bold mb-4 text-green-600">Signup Successful!</h2>
    <p class="text-gray-700 mb-6">Your account has been created successfully.</p>
    <a href="${pageContext.request.contextPath}/loginUser" class="text-blue-500 hover:underline">Click here to login</a>
</div>
</body>
</html>