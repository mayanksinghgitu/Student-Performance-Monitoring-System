document.getElementById('loginForm').addEventListener('submit', function (e) {
    e.preventDefault();


    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();
    const errorMsg = document.getElementById('errorMsg');

    // Clear previous error message
    errorMsg.textContent = '';

    // Simple validation
    if (!username || !password) {
        errorMsg.textContent = 'Both fields are required!';
        return;
    }

    // Example authentication
    if (username === 'admin' && password === 'password') {
        alert('Login successful!');
        alert('Welcome to STUDENT PERFORMANCE MONITORING SYSTEM');
        // Redirect or further processing here
        window.location.href = "https://openai.com/"; // Replace with your actual redirect page
    } else {
        errorMsg.textContent = 'Invalid username or password!';
    }
});
