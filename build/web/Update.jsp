<%@page import="Models.DTO.Mobile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Mobile</title>
    <link rel="stylesheet" type="text/css" href="styleUpdate.css">
</head>
<body>
    <%
        Mobile mobile = (Mobile) session.getAttribute("MobileItem");
    %>
    <div class="container">
        <h1 class="title">✏️ Update Mobile Info</h1>
        <form action="ManageStaffServlet" method="post" class="update-form">
            <input type="hidden" name="txtMobileId" value="<%= mobile.getMobileID() %>">

            <label for="txtPrice">Price:</label>
            <input type="text" name="txtPrice" id="txtPrice" value="<%= mobile.getPrice() %>" required>

            <label for="txtDescription">Description:</label>
            <input type="text" name="txtDescription" id="txtDescription" value="<%= mobile.getDescription() %>" required>

            <label for="txtQuantity">Quantity:</label>
            <input type="text" name="txtQuantity" id="txtQuantity" value="<%= mobile.getQuantity() %>" required>

            <label for="txtIsSale">Is Sale? (true/false):</label>
            <input type="text" name="txtIsSale" id="txtIsSale" value="<%= mobile.isNotSale() %>" required>

            <input type="submit" name="btnAction" value="update" class="btn">
        </form>
        <a href="StaffControllerServlet" class="back-link">⬅ Back to Staff Menu</a>
    </div>
</body>
</html>
