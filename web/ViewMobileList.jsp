<%@page import="java.util.List"%>
<%@page import="Models.DTO.Mobile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>📱 View Mobile For Customer</title>
        <link rel="stylesheet" type="text/css" href="styleViewMobile.css">
    </head>
    <body>
        <div class="container">
            <h1 class="title">📱 View Mobile For Customer</h1>

            <!-- 🔍 Price Filter Form -->
            <form class="filter-form" action="ManageCartController" method="post">
                <fieldset class="price-range">
                    <legend>🔎 Find by Price Range</legend>
                    <div class="form-group">
                        <label for="minValue">Min value:</label>
                        <input type="number" id="minValue" name="txtMinValue" class="input-box">
                    </div>

                    <div class="form-group">
                        <label for="maxValue">Max value:</label>
                        <input type="number" id="maxValue" name="txtMaxValue" class="input-box">
                    </div>

                    <input class="btn primary" type="submit" name="action" value="Find Price Range">
                </fieldset>
            </form>

            <%
                int count = 0;
                int numberOfMobile = 0;
                List<Mobile> mobileList = (List<Mobile>) session.getAttribute("MobileList");
                String message = (String) request.getAttribute("Message");
                int currentPage = request.getAttribute("currentPage") != null ? (Integer) request.getAttribute("currentPage") : 1;
                int totalPages = request.getAttribute("totalPages") != null ? (Integer) request.getAttribute("totalPages") : 1;

                if (message == null) {
                    message = (String) session.getAttribute("Message");
                    session.removeAttribute("Message");
                }

                if (mobileList != null && !mobileList.isEmpty()) {
            %>

            <!-- 📦 Mobile List Table -->
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
                    <% count = (currentPage - 1) * 10; %>
                    <% for (Mobile mobile : mobileList) { numberOfMobile++; %>
                    <tr>
                        <td><%= ++count %></td>
                        <td><%= mobile.getMobileID() %></td>
                        <td><%= mobile.getDescription() %></td>
                        <td>$<%= mobile.getPrice() %></td>
                        <td><%= mobile.getMobileName() %></td>
                        <td><%= mobile.getYearOfProduction() %></td>
                        <td><%= mobile.getQuantity() %></td>
                        <td><%= mobile.isNotSale() ? "Yes" : "No" %></td>
                        <td>
                            <form action="AddToCartController" method="post">
                                <input type="hidden" name="MobileId" value="<%= mobile.getMobileID() %>">
                                <input class="btn add" type="submit" value="Add">
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>

            <div class="pagination">
                <% if (totalPages > 1) { %>
                    <% if (currentPage > 1) { %>
                        <a href="ViewMobileController?page=<%= currentPage - 1 %>">⬅ Prev</a>
                    <% } %>
                    <% for (int i = 1; i <= totalPages; i++) { %>
                        <% if (i == currentPage) { %>
                            <span class="current-page"><%= i %></span>
                        <% } else { %>
                            <a href="ViewMobileController?page=<%= i %>"><%= i %></a>
                        <% } %>
                    <% } %>
                    <% if (currentPage < totalPages) { %>
                        <a href="ViewMobileController?page=<%= currentPage + 1 %>">Next ➡</a>
                    <% } %>
                <% } %>
            </div>
            <% } %>

            <!-- ℹ️ Summary & Message -->
            <h3 class="summary">📦 Mobiles found: <%= numberOfMobile %></h3>

            <% if (message != null) { %>
                <h4 class="message"><%= message %></h4>
            <% } %>

            <!-- 🔗 Action Buttons -->
            <div class="action-row">
                <form action="ViewCartController" method="post">
                    <input class="btn cart" type="submit" name="action" value="🛒 View Cart">
                </form>
                <form action="AfterLoginUser.jsp" method="post">
                    <input type="submit" class="btn back" value="⬅ Back to Menu">
                </form>
            </div>
        </div>
    </body>
</html>
