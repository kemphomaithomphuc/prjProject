<%@page import="Models.DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Profile</title>
    <link rel="stylesheet" href="viewProfile.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
</head>
<body>

<div class="profile-wrapper">
    <div class="profile-card">
        <h1 class="page-title">👤 Your Profile</h1>

        <%
            User user = (User) session.getAttribute("User");
            String message = (String) session.getAttribute("Message");
            String email = (String) session.getAttribute("Email");
        %>

        <img src="user.jpg" alt="User Profile" class="profile-pic">

        <table class="profile-table">
            <tr><td>User ID</td><td><%= user.getUserID() %></td></tr>
            <tr><td>Password</td><td><%= user.getPassword() %></td></tr>
            <tr><td>Name</td><td><%= user.getFullName() %></td></tr>
            <tr><td>Role</td>
                <td>
                    <%
                        if (user.getRole() == 0) { out.print("Customer"); }
                        else if (user.getRole() == 2) { out.print("Staff"); }
                        else { out.print("Unknown"); }
                    %>
                </td>
            </tr>
            <% if (email != null) { %>
                <tr><td>Email</td><td><%= email %></td></tr>
            <% } %>
        </table>

        <div class="button-group">
            <a href="addEmail.jsp" class="btn add-email-btn">📧 Add Email</a>
            <% if (user.getRole() == 0) { %>
                <a href="AfterLoginUser.jsp" class="btn back-btn">⬅️ Back</a>
            <% } else if (user.getRole() == 2) { %>
                <a href="StaffControllerServlet" class="btn back-btn">⬅️ Back</a>
            <% } %>
        </div>

        <% if (message != null) { %>
            <div class="message"><%= message %></div>
        <% } %>
    </div>
</div>

</body>
</html>
