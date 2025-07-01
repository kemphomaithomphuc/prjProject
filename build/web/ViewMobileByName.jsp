<%@page import="java.util.List"%>
<%@page import="Models.DTO.Mobile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mobile Search Result</title>
    <link rel="stylesheet" type="text/css" href="styleViewMobileByName.css">
</head>
<body>
    <div class="container">
        <h1 class="title">🔍 Search Result: Mobile Devices</h1>

        <%
            int count = 0;
            int numberOfMobile = 0;
            List<Mobile> mobileList = (List<Mobile>) session.getAttribute("MobileList");
            if (mobileList != null && !mobileList.isEmpty()) {
        %>
        <table class="mobile-table">
            <thead>
                <tr>
                    <th>No.</th>
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
                <%
                    for (Mobile mobile : mobileList) {
                        numberOfMobile++;
                %>
                <tr>
                    <td><%= ++count %></td>
                    <td><%= mobile.getMobileID() %></td>
                    <td><%= mobile.getDescription() %></td>
                    <td><%= mobile.getPrice() %></td>
                    <td><%= mobile.getMobileName() %></td>
                    <td><%= mobile.getYearOfProduction() %></td>
                    <td><%= mobile.getQuantity() %></td>
                    <td><%= mobile.isNotSale() ? "Yes" : "No" %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <h3 class="summary">📦 Total Devices Found: <%= numberOfMobile %></h3>
        <% } else { %>
            <p class="no-data">❌ No mobile devices found with the given name.</p>
        <% } %>

        <a href="StaffControllerServlet" class="back-link">⬅ Back to Staff Menu</a>
    </div>
</body>
</html>
