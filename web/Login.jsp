<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" href="styleLogin.css">
</head>
<body class="page-body">
    <div class="login-wrapper page-center">
        <%
            String message = (String) request.getAttribute("Message");
            boolean hasMessage = (message != null);
            String cardClass = hasMessage ? "login-card has-message animated-card drop-shadow" : "login-card animated-card drop-shadow";
        %>
        <div class="<%= cardClass %>">
            <h2 class="login-title heading-text icon-title">🔐 Login</h2>

            <form action="MainControllerServlet" method="post" class="login-form form-block form-animated">

                <div class="input-group group-username">
                    <label for="username" class="input-label">👤 Username</label>
                    <input type="text" name="txtUsername" id="username" class="input-field username-input">
                </div>

                <div class="input-group group-password">
                    <label for="password" class="input-label">🔒 Password</label>
                    <input type="password" name="txtPassword" id="password" class="input-field password-input">
                </div>

                <div class="button-group action-buttons">
                    <input class="login-button primary-button btn-login" type="submit" name="btnAction" value="Log In">
                    <input class="login-button secondary-button btn-signup" type="submit" name="btnAction" value="Sign Up">
                </div>

                <% if (hasMessage) { %>
                    <div class="message-container">
                        <p class="message-box error-message"><%= message %></p>
                    </div>
                <% } %>
            </form>
        </div>
    </div>
</body>
</html>
