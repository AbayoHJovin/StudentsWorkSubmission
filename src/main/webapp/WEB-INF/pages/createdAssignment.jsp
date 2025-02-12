<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Assignment Created</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 h-screen flex items-center justify-center">

<div class="bg-white shadow-lg rounded-lg w-full max-w-md p-6">
    <!-- Card Header -->
    <div class="bg-green-500 text-white text-2xl font-bold text-center py-4 rounded-t-lg">
        Assignment Created Successfully
    </div>

    <!-- Card Body -->
    <div class="text-center mt-6">
        <div class="text-5xl text-green-500 mb-4">
            <span>✔</span>
        </div>
        <div class="text-xl text-gray-700 mb-4">
            Your assignment has been successfully created!
        </div>
        <p class="text-gray-500 mb-6">
            You can now manage the assignment from the dashboard.
        </p>
        <!-- Button to Return to Dashboard -->
        <a href="${pageContext.request.contextPath}/instructor-dashboard" class="bg-green-500 text-white font-semibold py-2 px-6 rounded-md hover:bg-green-600 transition duration-300">
            Return to Dashboard
        </a>
    </div>
</div>

</body>
</html>
