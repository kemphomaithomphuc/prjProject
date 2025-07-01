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
    <div class="container" id="cart-wrapper">
        <header class="cart-header">
            <h1 class="title">🛒 Your Shopping Cart</h1>
        </header>

        <main class="cart-content">
            <%
                int count = 0;
                int numberOfMobile = 0;
                List<CartItem> itemList = (List<CartItem>) request.getAttribute("Cart");
                session.setAttribute("ListInCart", itemList);

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
                    <tr class="cart-item-row">
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
                                <input class="btn btn-remove" type="submit" formaction="ManageCartController?action=Remove" value="Remove">
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <%
                } else {
            %>
            <div class="empty-message">
                <p>Your cart is currently empty. 🛍️</p>
            </div>
            <%
                }
            %>
        </main>

        <section class="cart-summary">
            <h3>🧾 Total Items: <span class="total-count"><%= numberOfMobile %></span></h3>
        </section>

        <footer class="cart-actions">
            <form action="ManageCartController" method="post">
                <input class="btn btn-save" type="submit" name="action" value="Save">
            </form>

            <form action="ManageCartController" method="post">
                <input class="btn btn-buy" type="submit" name="action" value="Buy">
            </form>

            <form action="ViewMobileController" method="post">
                <input class="btn btn-back" type="submit" name = "action" value="← Back">
            </form>
        </footer>

        <%
            if (request.getAttribute("Message") != null) {
        %>
        <div class="feedback-message">
            <p><%= request.getAttribute("Message") %></p>
        </div>
        <%
            }
        %>
    </div>
</body>
</html>
