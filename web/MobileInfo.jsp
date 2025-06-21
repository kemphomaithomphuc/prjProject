<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Find Mobile to Update</title>
    <link rel="stylesheet" type="text/css" href="styleMobileInfo.css">
</head>
<body>
    <div class="container">
        <h1 class="title">🔍 Find Mobile To Update</h1>
        <form action="FindMobileToUpdateServlet" method="post" class="info-form">
            <label for="txtMobileId">Mobile ID:</label>
            <input type="text" name="txtMobileId" id="txtMobileId" required>

            <input type="submit" name="btnAction" value="Show" class="btn">
        </form>
        <a href="StaffControllerServlet" class="back-link">⬅ Back to Staff Menu</a>
    </div>
</body>
</html>
