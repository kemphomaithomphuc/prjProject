<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Remove Mobile</title>
    <link rel="stylesheet" type="text/css" href="styleRemove.css">
</head>
<body>
    <div class="container">
        <h1 class="title">🗑️ Remove Mobile</h1>
        <form action="ManageStaffServlet" method="post" class="remove-form">
            <label for="txtMobileId">Mobile ID:</label>
            <input type="text" name="txtMobileId" id="txtMobileId" required>
            
            <input type="submit" name="btnAction" value="delete" class="btn">
        </form>
        <a href="StaffControllerServlet" class="back-link">⬅ Back to Staff Menu</a>
    </div>
</body>
</html>
