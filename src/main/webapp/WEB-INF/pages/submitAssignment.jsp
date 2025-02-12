<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Submit Assignment</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 p-6 flex items-center justify-center min-h-screen">
<div class="max-w-lg w-full bg-white p-6 rounded-lg shadow-lg">
    <h1 class="text-2xl font-bold text-gray-800 mb-4 text-center">Submit Assignment</h1>

    <form action="submitAssignment?assignmentId=${param.assignmentId}" method="post" enctype="multipart/form-data" class="space-y-4">
        <div>
            <label for="assignmentFile" class="block text-gray-700 font-medium mb-2">Upload your assignment:</label>
            <div class="relative w-full">
                <input type="file" id="assignmentFile" name="assignmentFile" class="hidden" onchange="displayFileName(this)" required>
                <label for="assignmentFile" class="w-full p-2 border border-gray-300 rounded-lg bg-gray-100 text-gray-600 text-center cursor-pointer hover:bg-gray-200 block">
                    <span id="fileLabel">Choose File</span>
                </label>
            </div>
            <p id="fileNameDisplay" class="text-sm text-gray-500 mt-2"></p>
        </div>

        <button type="submit" class="w-full px-4 py-2 bg-blue-600 text-white font-semibold rounded-lg hover:bg-blue-700 transition">
            Submit Assignment
        </button>
    </form>
</div>

<script>
    function displayFileName(input) {
        const fileNameDisplay = document.getElementById('fileNameDisplay');
        const fileLabel = document.getElementById('fileLabel');

        if (input.files && input.files.length > 0) {
            fileNameDisplay.innerHTML = `File Selected ${input.files[0].name}`;
            fileLabel.textContent = "Change File";
        } else {
            fileNameDisplay.textContent = '';
            fileLabel.textContent = "Choose File";
        }
    }
</script>
</body>
</html>
