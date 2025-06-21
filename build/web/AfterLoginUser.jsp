<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Choice Page</title>
    <link rel="stylesheet" type="text/css" href="styleAfterLogin.css">
</head>
<body>
    <div class="choice-container">
        <%
            String userName = (String) session.getAttribute("UserName");
        %>
        <div class="card glassmorphism">
            <h1 class="welcome-title">👋 Welcome, <%= userName %></h1>

            <div class="nav-links">
                <a class="nav-button view-mobile" href="ViewMobileController?action=ViewMobileList">📱 View Mobile</a>
                <a class="nav-button view-cart" href="ViewCartController?action=ViewCart">🛒 View Cart</a>
            </div>

            <div class="logout-container">
                <a href="Login.jsp" class="logout-button">🚪 Logout</a>
            </div>
        </div>
    </div>
</body>
</html>
