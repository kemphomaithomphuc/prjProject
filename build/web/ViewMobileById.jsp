<%@page import="Models.DTO.Mobile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Mobile by ID</title>
    <link rel="stylesheet" type="text/css" href="styleViewMobileById.css">
</head>
<body>
    <div class="container">
        <h1 class="title">📱 Mobile Details</h1>
        <%
            Mobile mobile = (Mobile) session.getAttribute("MobileItem");
            if (mobile != null) {
        %>
        <table class="mobile-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Name</th>
                    <th>Year</th>
                    <th>Quantity</th>
                    <th>Not Sale</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%=mobile.getMobileID()%></td>
                    <td><%=mobile.getDescription()%></td>
                    <td><%=mobile.getPrice()%></td>
                    <td><%=mobile.getMobileName()%></td>
                    <td><%=mobile.getYearOfProduction()%></td>
                    <td><%=mobile.getQuantity()%></td>
                    <td><%=mobile.isNotSale()%></td>
                </tr>
            </tbody>
        </table>
        <%
            } else {
        %>
            <p class="no-result">❌ No mobile found with the given ID.</p>
        <%
            }
        %>
        <a href="StaffControllerServlet" class="back-link">⬅ Back to Staff Menu</a>
    </div>
</body>
</html>
