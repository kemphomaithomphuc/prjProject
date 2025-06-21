package StaffController;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class StaffControllerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        try {
            String userName = (String) session.getAttribute("UserName");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Staff Dashboard</title>");
            out.println("<link rel='stylesheet' type='text/css' href='styleStaff.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h1 class='welcome'>👋 Your Choice</h1>");

            out.println("<div class='action-links'>");
            out.println("<a class='btn' href='DataViewServlet?action=add'>➕ Add Mobile</a>");
            out.println("<a class='btn' href='DataViewServlet?action=delete'>❌ Delete Mobile</a>");
            out.println("<a class='btn' href='DataViewServlet?action=update'>✏️ Update Mobile</a>");
            out.println("<a class='btn' href='DataViewServlet?action=search'>🔍 Search Mobile</a>");
            out.println("</div>");

            // Back to Login button
            out.println("<div class='back-container'>");
            out.println("<a class='btn back-btn' href='Login.jsp'>⬅ Log Out</a>");
            out.println("</div>");

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception ex) {
            out.println("<p class='error'>" + ex.getMessage() + "</p>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Staff Dashboard Controller";
    }
}
