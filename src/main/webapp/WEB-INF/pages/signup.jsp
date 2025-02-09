<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Include TailwindCSS CDN -->
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <title>Sign Up</title>
    <script>
        function toggleFields() {
            var role = document.getElementById("role").value;
            var courseField = document.getElementById("courseField");
            var phoneField = document.getElementById("phoneField");
            var birthdateField = document.getElementById("birthdateField");

            if (role === "INSTRUCTOR") {
                courseField.style.display = "block";  // Show the course input field
                phoneField.style.display = "block";   // Show the phone number field
                birthdateField.style.display = "none"; // Hide the birthdate field
            } else {
                courseField.style.display = "none";   // Hide the course input field
                phoneField.style.display = "none";    // Hide the phone number field
                birthdateField.style.display = "block"; // Show the birthdate field
            }
        }
    </script>
</head>
<body>
<!-- Signup Page (signup.jsp) -->
<div class="flex items-center justify-center min-h-screen bg-gray-100">
    <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
        <h2 class="text-2xl font-bold mb-6 text-center text-gray-800">Sign Up</h2>

        <!-- Display success message if available -->

        <%-- Scriptlet to display a success message --%>
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
        <!-- Form for user registration -->
            <form action="SignupServlet" method="POST">
            <!-- First Name -->
            <div class="mb-4">
                <label for="firstName" class="block text-sm font-medium text-gray-700">First Name</label>
                <input type="text" id="firstName" name="firstName" placeholder="First Name" class="w-full p-2 border rounded mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500" required>
            </div>

            <!-- Last Name -->
            <div class="mb-4">
                <label for="lastName" class="block text-sm font-medium text-gray-700">Last Name</label>
                <input type="text" id="lastName" name="lastName" placeholder="Last Name" class="w-full p-2 border rounded mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500" required>
            </div>

            <!-- Email -->
            <div class="mb-4">
                <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
                <input type="email" id="email" name="email" placeholder="Email" class="w-full p-2 border rounded mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500" required>
            </div>

            <!-- Password -->
            <div class="mb-4">
                <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
                <input type="password" id="password" name="password" placeholder="Password" class="w-full p-2 border rounded mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500" required>
            </div>

            <!-- Role Selection -->
            <div class="mb-4">
                <label for="role" class="block text-sm font-medium text-gray-700">Role</label>
                <select id="role" name="role" class="w-full p-2 border rounded mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500" onchange="toggleFields()" required>
                    <option value="STUDENT">Student</option>
                    <option value="INSTRUCTOR">Instructor</option>
                </select>
            </div>

            <!-- Courses Taught (Visible for Instructors) -->
            <div id="courseField" style="display: none;" class="mb-4">
    <div>
        <label>
            <input type="checkbox" name="courses" value="KINYARWANDA"> Kinyarwanda
        </label><br>
        <label>
            <input type="checkbox" name="courses" value="JAVA"> Java
        </label><br>
        <label>
            <input type="checkbox" name="courses" value="SE"> SE
        </label><br>
        <label>
            <input type="checkbox" name="courses" value="EMBEDDED_SYSTEMS"> Embedded Systems
        </label><br>
        <label>
            <input type="checkbox" name="courses" value="NETWORKING"> Networking
        </label><br>
        <label>
            <input type="checkbox" name="courses" value="WEB3"> Web3
        </label><br>
        <label>
            <input type="checkbox" name="courses" value="MATHS"> Maths
        </label><br>
        <label>
            <input type="checkbox" name="courses" value="PHYSICS"> Physics
        </label><br>
        <label>
            <input type="checkbox" name="courses" value="DATABASES"> Databases
        </label><br>
        <label>
            <input type="checkbox" name="courses" value="E/SHIP"> Entrepreneurship
        </label><br>
        <label>
            <input type="checkbox" name="courses" value="HISTORY"> History
        </label><br>
    </div>
            </div>

            <!-- Phone Number (Visible for Instructors) -->
            <div id="phoneField" style="display: none;" class="mb-4">
                <label for="tel" class="block text-sm font-medium text-gray-700">Phone Number</label>
                <input type="tel" id="tel" name="tel" placeholder="Enter your phone number" class="w-full p-2 border rounded mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>

            <!-- Birthdate (Visible for Students) -->
            <div id="birthdateField" style="display: block;" class="mb-4">
                <label for="birthDate" class="block text-sm font-medium text-gray-700">Birthdate</label>
                <input type="date" id="birthDate" name="birthDate" class="w-full p-2 border rounded mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>

            <!-- Submit Button -->
            <div class="mb-4">
                <button type="submit" class="w-full p-2 bg-blue-500 text-white rounded hover:bg-blue-700">Sign Up</button>
            </div>
        </form>

        <!-- Redirect to login page -->
        <p class="text-center">
            Already have an account? <a href="${pageContext.request.contextPath}/loginUser" class="text-blue-500">Login here</a>
        </p>
    </div>
</div>
</body>
</html>
