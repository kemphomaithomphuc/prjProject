<%@page import="java.util.List"%>
<%@page import="Models.DTO.CartItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>🛒 View Cart</title>
    <link rel="stylesheet" type="text/css" href="styleCart.css">
</head>
<body>
    <div class="container">
        <h1 class="title">🛒 Your Cart</h1>

        <%
            int count = 0;
            int numberOfMobile = 0;
            List<CartItem> itemList = (List<CartItem>) request.getAttribute("Cart");

            if (itemList != null && !itemList.isEmpty()) {
        %>
        <table class="cart-table">
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
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (CartItem item : itemList) {
                        numberOfMobile++;
                %>
                <tr>
                    <td><%= ++count %></td>
                    <td><%= item.getMobileID() %></td>
                    <td><%= item.getDescription() %></td>
                    <td>$<%= item.getPrice() %></td>
                    <td><%= item.getMobileName() %></td>
                    <td><%= item.getYearOfProduction() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td><%= item.isNotSale() %></td>
                    <td>
                        <form method="post">
                            <input type="hidden" name="ItemId" value="<%= item.getMobileID() %>">
                            <input class="btn remove" type="submit" formaction="ManageCartController?action=Remove" value="Remove">
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

        <h3 class="summary">🧾 Total Items: <%= numberOfMobile %></h3>

        <form action="ManageCartController" method="post">
            <input class="btn save" type="submit" name="action" value="Save">
        </form>

        <a class="btn back" href="ManageCartController?action=ViewList">← Back</a>

        <%
            if (request.getAttribute("Message") != null) {
        %>
            <p class="message"><%= request.getAttribute("Message") %></p>
        <%
            }
        %>
    </div>
</body>
</html>
