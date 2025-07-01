/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MainController;

import Models.DAO.UserDAO;
import Models.DTO.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Mainh
 */
public class MainControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String loginPage = "Login.jsp";
    private final String userController = "UserControllerServlet";
    private final String staffController = "StaffControllerServlet";
    private final String signUpPage = "SignUp.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = loginPage;
        HttpSession session = request.getSession();
        String action = request.getParameter("btnAction");
        String userName = request.getParameter("txtUsername");
        String message = null;
        String password = request.getParameter("txtPassword");
        if (userName.isEmpty() || password.isEmpty()) {
            url = loginPage;
            request.setAttribute("Message", "❗You must fill in both username and password.");
        }
        try {

            if (action.equals("Sign Up")) {
                url = signUpPage;
            }
            UserDAO userDAO = new UserDAO();
            User user = userDAO.login(userName, Integer.parseInt(password));
            if (action.equals("Log In")) {
                if (userName == null || password == null) {
                    url = loginPage;

                } else if (user != null) {
                    session.setAttribute("UserName", userName);
                    session.setAttribute("User", user);
                    if (user.getRole() == 2) {
                        url = staffController;
                    } else if (user.getRole() == 0) {
                        url = userController;
                    }
                }
            }

            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
