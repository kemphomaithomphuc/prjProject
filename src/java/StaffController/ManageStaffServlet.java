package StaffController;

import Models.DAO.MobileDAO;
import Models.DTO.Mobile;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ManageStaffServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("btnAction");
        MobileDAO mobileDAO = new MobileDAO();
        String mobileId, description, mobileName;
        int yearOfProduction, quantity;
        float price;
        boolean notSale;

        try {
            String message = "";
            String backURL = "StaffControllerServlet"; // default back page

            if (action.equalsIgnoreCase("delete")) {
                mobileId = request.getParameter("txtMobileId");
                message = mobileDAO.deleteMobile(mobileId) ? "✅ Deleted successfully" : "❌ Failed to delete";
                backURL = "StaffControllerServlet";
            } else if (action.equalsIgnoreCase("update")) {
                mobileId = request.getParameter("txtMobileId");
                price = Float.parseFloat(request.getParameter("txtPrice"));
                description = request.getParameter("txtDescription");
                quantity = Integer.parseInt(request.getParameter("txtQuantity"));
                notSale = Boolean.parseBoolean(request.getParameter("txtIsSale"));

                Mobile existingMobile = mobileDAO.getMobileById(mobileId);
                Mobile updatedMobile = new Mobile(mobileId, description, price, existingMobile.getMobileName(), existingMobile.getYearOfProduction(), quantity, notSale);
                message = mobileDAO.updateMobile(updatedMobile) ? "✅ Updated successfully" : "❌ Failed to update";
                backURL = "StaffControllerServlet";
            } else if (action.equalsIgnoreCase("Insert")) {
                mobileId = request.getParameter("txtMobileId");
                description = request.getParameter("txtDescription");
                mobileName = request.getParameter("txtMobileName");
                yearOfProduction = Integer.parseInt(request.getParameter("txtYear"));
                quantity = Integer.parseInt(request.getParameter("txtQuantity"));
                price = Float.parseFloat(request.getParameter("txtPrice"));
                notSale = Boolean.parseBoolean(request.getParameter("txtIsSale"));

                Mobile mobile = new Mobile(mobileId, description, price, mobileName, yearOfProduction, quantity, notSale);
                message = mobileDAO.addMobile(mobile) ? "✅ Added successfully" : "❌ Failed to add";
                backURL = "StaffControllerServlet";
            }

            // Output styled HTML
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Staff Operation Result</title>");
            out.println("<link rel='stylesheet' type='text/css' href='styleResult.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='result-box'>");
            out.println("<h1>📋 Operation Result</h1>");
            out.println("<p class='message'>" + message + "</p>");
            out.println("<a href='" + backURL + "' class='btn back-btn'>⬅ Back</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            out.println("<p class='error'>❌ Error: " + ex.getMessage() + "</p>");
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
        return "Manages Insert/Update/Delete for Mobile";
    }
}
