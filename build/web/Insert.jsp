<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert Mobile</title>
    <link rel="stylesheet" type="text/css" href="styleInsert.css">
</head>
<body>
    <div class="container">
        <h1 class="title">📲 Insert New Mobile</h1>
        <form action="ManageStaffServlet" method="post" class="insert-form">
            <label>Mobile ID:</label>
            <input type="text" name="txtMobileId" required>

            <label>Product Year:</label>
            <input type="text" name="txtYear" required>

            <label>Mobile Name:</label>
            <input type="text" name="txtMobileName" required>

            <label>Price:</label>
            <input type="text" name="txtPrice" required>

            <label>Description:</label>
            <input type="text" name="txtDescription" required>

            <label>Quantity:</label>
            <input type="text" name="txtQuantity" required>

            <label>Is Sale (true/false):</label>
            <input type="text" name="txtIsSale" required>

            <input type="submit" name="btnAction" value="Insert" class="btn">
        </form>
    </div>
</body>
</html>
