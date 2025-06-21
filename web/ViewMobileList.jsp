<%@page import="java.util.List"%>
<%@page import="Models.DTO.Mobile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP View Mobile</title>
    <link rel="stylesheet" type="text/css" href="styleViewMobile.css">
</head>
<body>
    <div class="container">
        <h1 class="title">📱 View Mobile For Customer</h1>

        <form class="filter-form" action="ManageCartController" method="post">
            <fieldset class="price-range">
                <legend>🔎 Find by Price Range</legend>
                <label for="minValue">Min value:</label>
                <input type="number" id="minValue" name="txtMinValue" class="input-box"><br>

                <label for="maxValue">Max value:</label>
                <input type="number" id="maxValue" name="txtMaxValue" class="input-box"><br>

                <input class="btn primary" type="submit" name="action" value="Find Price Range">
            </fieldset>
        </form>

        <%
            int count = 0;
            int numberOfMobile = 0;
            List<Mobile> mobileList = (List<Mobile>) session.getAttribute("MobileList");
            String message = (String) request.getAttribute("Message");

            if (message == null) {
                message = (String) session.getAttribute("Message");
                session.removeAttribute("Message");
            }

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
                    <th>Add</th>
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
                    <td>$<%= mobile.getPrice() %></td>
                    <td><%= mobile.getMobileName() %></td>
                    <td><%= mobile.getYearOfProduction() %></td>
                    <td><%= mobile.getQuantity() %></td>
                    <td><%= mobile.isNotSale() %></td>
                    <td>
                        <form action="AddToCartController" method="post">
                            <input type="hidden" name="MobileId" value="<%= mobile.getMobileID() %>">
                            <input class="btn add" type="submit" value="Add">
                        </form>
                    </td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <%
            }
        %>

        <h3 class="summary">📦 Mobiles found: <%= numberOfMobile %></h3>

        <% if (message != null) { %>
            <h4 class="message"><%= message %></h4>
        <% } %>

        <form action="ViewCartController" method="post">
            <input class="btn cart" type="submit" name="action" value="🛒 View Cart">
        </form>
    </div>
</body>
</html>
