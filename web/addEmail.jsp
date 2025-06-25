<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Email</title>

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">

    <!-- External CSS -->
    <link rel="stylesheet" type="text/css" href="addEmail.css">

    <!-- Google Sign-In API -->
    <script src="https://accounts.google.com/gsi/client" async defer></script>

    <script>
        function handleCredentialResponse(response) {
            document.getElementById("credential").value = response.credential;
            document.getElementById("emailForm").submit();
        }

        window.onload = function () {
            google.accounts.id.initialize({
                client_id: "221205097604-8sp3fja86njtsila6d2p9tlcjgv5if28.apps.googleusercontent.com",
                callback: handleCredentialResponse
            });
            google.accounts.id.renderButton(
                document.getElementById("googleSignInDiv"),
                { theme: "outline", size: "large", width: "300" }
            );
        };
    </script>
</head>
<body>

    <div class="card">
        <h1>Add Your Google Email</h1>

        <div id="googleSignInDiv"></div>

        <form id="emailForm" action="AddEmailServlet" method="post">
            <input type="hidden" name="credential" id="credential" />
        </form>

        <div class="footer">
            We only use your Google email to link your account securely.
        </div>
    </div>

</body>
</html>
