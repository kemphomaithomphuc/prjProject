<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.List"%>
<%@page import="Models.DTO.CartItem"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <!-- External CSS -->
    <link rel="stylesheet" href="styleViewBuy.css">
</head>
<body>
    <h1>Your Items</h1>
    <%
        float totalPrice = 0;
        int totalQuantity = 0;
        int count = 0;
        List<CartItem> itemList = (List<CartItem>) session.getAttribute("Cart");
        NumberFormat vndFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
    %>
    <table class="cart-table">
        <thead>
            <tr>
                <th>No.</th>
                <th>ID</th>
                <th>Name</th>
                <th>Price (VND)</th>
                <th>Quantity</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (itemList != null && !itemList.isEmpty()) {
                    for (CartItem item : itemList) {
                        float itemTotalVND = (float) item.getPrice() * item.getQuantity() * 25000;
                        totalPrice += itemTotalVND;
                        totalQuantity += item.getQuantity();
            %>
            <tr>
                <td><%= ++count %></td>
                <td><%= item.getMobileID() %></td>
                <td><%= item.getMobileName() %></td>
                <td><%= vndFormat.format(item.getPrice() * 25000) %>₫</td>
                <td><%= item.getQuantity() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5" style="text-align:center; color:gray;">No items in cart.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <div class="totals">
        <h2>Total Quantity: <%= totalQuantity %></h2>
        <h2>Total Price: <%= vndFormat.format(totalPrice) %>₫</h2>
    </div>

    <div style="margin-top: 30px;">
        <form action="ViewCart.jsp" method="get">
            <button type="submit" class="back-btn">← Back to Shop</button>
        </form>
    </div>
</body>
</html>
