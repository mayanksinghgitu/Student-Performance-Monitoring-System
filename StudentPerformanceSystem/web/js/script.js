document.getElementById('loginForm').addEventListener('submit', function (e) {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    if (!email || !password) {
        e.preventDefault();
        alert("All fields are required!");
    }
});

document.getElementById('registerForm').addEventListener('submit', function (e) {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    if (!email.includes('@')) {
        e.preventDefault();
        alert("Enter a valid email!");
    } else if (password.length < 6) {
        e.preventDefault();
        alert("Password must be at least 6 characters!");
    }
});
