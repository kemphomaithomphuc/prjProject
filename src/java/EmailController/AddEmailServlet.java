/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package EmailController;

import Models.DAO.UserDAO;
import Models.DTO.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import jakarta.servlet.http.HttpSession;
import java.util.Collections;

public class AddEmailServlet extends HttpServlet {

    private static final String CLIENT_ID = "221205097604-8sp3fja86njtsila6d2p9tlcjgv5if28.apps.googleusercontent.com";
    private final String viewProfilePage = "ViewProfile.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idTokenString = request.getParameter("credential");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

        String url = viewProfilePage;
        String message;

        try {
            if (user == null) {
                request.setAttribute("Message", "Session expired. Please log in again.");
            } else {
                NetHttpTransport transport = new NetHttpTransport(); // safer fallback
                JacksonFactory jacksonFactory = new JacksonFactory(); // explicit
                GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                        transport,
                        jacksonFactory)
                        .setAudience(Collections.singletonList(CLIENT_ID))
                        .build();

                GoogleIdToken idToken = verifier.verify(idTokenString);
                if (idToken != null) {
                    String email = idToken.getPayload().getEmail();
                    UserDAO userDAO = new UserDAO();
                    if (userDAO.addEmail(email, user)) {
                        session.setAttribute("Email", email);  // Store for display
                        message = "✅ Email linked successfully: " + email;
                    } else {
                        message = "⚠️ Failed to link email. It might already be linked.";
                    }
                } else {
                    message = "❌ Invalid Google ID token.";
                }

                request.setAttribute("Message", message);
            }

        } catch (Exception e) {
             e.printStackTrace();
            request.setAttribute("Message", "❌ Server error while verifying token.");
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
