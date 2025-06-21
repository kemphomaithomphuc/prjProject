<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styleLogin.css">
        <title>Login.jsp</title>
    </head>
    <body>
        <h1 class="login-title">Login</h1>
        <form action="MainControllerServlet" method="post">
            Username <input type="text" name="txtUsername"><br>
            Password <input type="password" name="txtPassword"><br>
            
            <!-- Centered buttons inside this div -->
            <div class="button-group">
                <input class="content-box" type="submit" name="btnAction" value="Log In">
                <input class="content-box" type="submit" name="btnAction" value="Sign Up">
            </div>
        </form>
    </body>
</html>
