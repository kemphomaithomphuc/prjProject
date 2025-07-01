<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>✨ Sign Up | Mobile Shop</title>
    <link rel="stylesheet" href="styleSignUp.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
</head>
<body>
    <div class="background-blur">
        <div class="signup-card">
            <h1 class="signup-title">🌟 Create Your Account</h1>
            <p class="subtitle">Join us and explore mobile world with style</p>

            <%
                String message = (String) session.getAttribute("Message");
                if (message != null) {
            %>
                <p class="message-box"><%= message %></p>
            <%
                    session.removeAttribute("Message");
                }
            %>

            <form class="signup-form" action="SignUpController" method="post">
                <div class="input-group">
                    <label for="txtUserId">Your ID</label>
                    <input type="text" name="txtUserId" id="txtUserId" required>
                </div>

                <div class="input-group">
                    <label for="txtUserName">Your Name</label>
                    <input type="text" name="txtUserName" id="txtUserName" required>
                </div>

                <div class="input-group">
                    <label for="txtPassword">Password</label>
                    <input type="password" name="txtPassword" id="txtPassword" required>
                </div>

                <div class="input-group">
                    <label for="txtSecondPassword">Confirm Password</label>
                    <input type="password" name="txtSecondPassword" id="txtSecondPassword" required>
                </div>

                <div class="checkbox-group">
                    <input type="checkbox" name="isAdmin" id="isAdmin">
                    <label for="isAdmin">Register as Staff</label>
                </div>

                <button type="submit" class="wow-button">🚀 Sign Up Now</button>
            </form>

            <div class="login-back">
                <a href="Login.jsp">⬅ Back to Login</a>
            </div>
        </div>
    </div>
</body>
</html>
