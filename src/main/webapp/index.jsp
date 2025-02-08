<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Submission Portal</title>
    <!-- Include TailwindCSS CDN -->
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-50">

<!-- Hero Section -->
<section class="bg-blue-600 text-white py-20 text-center">
    <h1 class="text-4xl font-extrabold mb-4">Welcome to the Online Submission Portal</h1>
    <p class="text-lg mb-8">Submit your work, assignments, and projects easily before the deadline!</p>
    <a href="${pageContext.request.contextPath}/signup" class="bg-white text-blue-600 px-6 py-3 rounded-full text-xl font-semibold">Get Started</a>
</section>

<!-- Features Section -->
<section class="py-16 px-6 bg-white">
    <div class="max-w-7xl mx-auto text-center">
        <h2 class="text-3xl font-bold mb-8">Features</h2>
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
            <div class="bg-blue-100 p-6 rounded-lg shadow-lg">
                <h3 class="text-xl font-semibold mb-4">Student Submissions</h3>
                <p>Submit your assignments, projects, and practice work easily before the deadline. Secure and organized!</p>
            </div>
            <div class="bg-blue-100 p-6 rounded-lg shadow-lg">
                <h3 class="text-xl font-semibold mb-4">Instructor Dashboard</h3>
                <p>Instructors can create assignments, set deadlines, and track student submissions effortlessly.</p>
            </div>
            <div class="bg-blue-100 p-6 rounded-lg shadow-lg">
                <h3 class="text-xl font-semibold mb-4">Multiple File Formats</h3>
                <p>Supports PDF, PPTX, Excel, and ZIP files. Upload your work in your preferred format!</p>
            </div>
        </div>
    </div>
</section>

<!-- Call to Action Section -->
<section class="bg-blue-600 text-white py-16 text-center">
    <h2 class="text-3xl font-bold mb-4">Ready to get started?</h2>
    <p class="text-lg mb-8">Log in now to begin submitting or managing assignments!</p>
    <a href="${pageContext.request.contextPath}/loginUser" class="bg-white text-blue-600 px-6 py-3 rounded-full text-xl font-semibold">Login</a>
</section>

<!-- Footer Section -->
<footer class="bg-gray-800 text-white py-8">
    <div class="text-center">
        <p>&copy; 2025 Online Submission Portal. All rights reserved.</p>
        <p>Designed by Jovin</p>
    </div>
</footer>

</body>
</html>
