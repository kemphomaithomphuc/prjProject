<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Mobile</title>
    <link rel="stylesheet" type="text/css" href="styleSearch.css">
</head>
<body>
    <div class="container">
        <h1 class="title">🔍 Search Mobile</h1>

        <form action="SearchServlet" method="post" class="search-form">
            <div class="form-group">
                <label for="txtMobileName">Search by Name:</label>
                <input type="text" name="txtMobileName" id="txtMobileName">
                <input type="submit" name="btnAction" value="SearchByName" class="btn">
            </div>

            <div class="form-group">
                <label for="txtMobileId">Search by ID:</label>
                <input type="text" name="txtMobileId" id="txtMobileId">
                <input type="submit" name="btnAction" value="SearchById" class="btn">
            </div>
        </form>

        <a href="StaffControllerServlet" class="back-link">⬅ Back to Staff Menu</a>
    </div>
</body>
</html>
