<%@page import="java.util.List"%>
<%@page import="Models.DTO.Mobile"%>
<%@page import="Models.DAO.MobileDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mobile Search Result</title>
    <link rel="stylesheet" type="text/css" href="styleSearchByPrice.css">
</head>
<body>
    <div class="container">
        <header class="page-header">
            <h1 class="title">📱 Mobiles in Price Range</h1>
        </header>

        <%
            int count = 0;
            int numberOfMobile = 0;
            float minValue = (float) session.getAttribute("MinValue");
            float maxValue = (float) session.getAttribute("MaxValue");
            List<Mobile> mobileList = (List<Mobile>) session.getAttribute("MobileList");
            int currentPage = request.getAttribute("currentPage") != null ? (Integer) request.getAttribute("currentPage") : 1;
            int totalPages = request.getAttribute("totalPages") != null ? (Integer) request.getAttribute("totalPages") : 1;
            String message = (String) request.getAttribute("Message");
        %>

        <% if (mobileList != null && !mobileList.isEmpty()) { %>
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
                    <th>Add</th>
                </tr>
            </thead>
            <tbody>
                <% count = (currentPage - 1) * 10;
                   for (Mobile mobile : mobileList) {
                   if (mobile.getPrice() >= minValue && mobile.getPrice() <= maxValue) {
                       numberOfMobile++;
                %>
                <tr>
                    <td><%= ++count %></td>
                    <td><%= mobile.getMobileID() %></td>
                    <td><%= mobile.getDescription() %></td>
                    <td>$<%= mobile.getPrice() %></td>
                    <td><%= mobile.getMobileName() %></td>
                    <td><%= mobile.getYearOfProduction() %></td>
                    <td><%= mobile.getQuantity() %></td>
                    <td><%= mobile.isNotSale() ? "Yes" : "No" %></td>
                    <td><a class="btn btn-add" href="ManageCartController?action=Add&MobileId=<%= mobile.getMobileID() %>">Add</a></td>
                </tr>
                <% } %>
                 <% } %>
            </tbody>
        </table>
        <% } else { %>
        <p class="no-result">🚫 No mobile data found in this price range.</p>
        <% } %>

        <!-- 🔢 Pagination -->
        <% if (totalPages > 1) { %>
        <div class="pagination">
            <% if (currentPage > 1) { %>
                <a href="SearchByPriceController?page=<%= currentPage - 1 %>">⬅ Prev</a>
            <% } %>
            <% for (int i = 1; i <= totalPages; i++) { %>
                <% if (i == currentPage) { %>
                    <span class="current-page"><%= i %></span>
                <% } else { %>
                    <a href="SearchByPriceController?page=<%= i %>"><%= i %></a>
                <% } %>
            <% } %>
            <% if (currentPage < totalPages) { %>
                <a href="SearchByPriceController?page=<%= currentPage + 1 %>">Next ➡</a>
            <% } %>
        </div>
        <% } %>

        <% if (message != null) { %>
        <h4 class="message"><%= message %></h4>
        <% } %>

        <section class="summary">
            <h3>🔍 Found: <%= numberOfMobile %> mobile(s) in range [$<%= minValue %> - $<%= maxValue %>]</h3>
        </section>

        <div class="back-link">
            <a class="btn btn-back" href="ManageCartController?action=ViewList">⬅ Back</a>
        </div>
    </div>
</body>
</html>
