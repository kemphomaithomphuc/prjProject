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
            int count;
            int numberOfMobile = 0;
            MobileDAO mobileDAO = new MobileDAO();
            List<Mobile> mobileList = mobileDAO.getMobileList();
            float minValue = (float) session.getAttribute("MinValue");
            float maxValue = (float) session.getAttribute("MaxValue");

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
                    count = 0;
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
                    <td><%= mobile.isNotSale() %></td>
                    <td><a class="btn btn-add" href="ManageCartController?action=add&mobileId=<%= mobile.getMobileID() %>">Add</a></td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
        <%
            }
        %>

        <section class="summary">
            <h3>🔍 Found: <%= numberOfMobile %> mobile(s) in range [$<%= minValue %> - $<%= maxValue %>]</h3>
        </section>

        <div class="back-link">
            <a class="btn btn-back" href="ViewMobileController">⬅ Back</a>
        </div>
    </div>
</body>
</html>
