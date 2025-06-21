/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package UserController;

import Models.DAO.MobileDAO;
import Models.DTO.Mobile;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Mainh
 */
public class ManageCartController extends HttpServlet {

    private final String addToCartPage = "AddToCartController";
    private final String viewRangePricePage = "ViewBookByPrice.jsp";
    private final String viewPage = "ViewMobileList.jsp";
    private final String viewCartPage = "ViewCartController";
    private final String removeProcess = "RemoveFromCartController";
    private final String saveProcess = "SaveToCartController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String url = null;
        try {
            /* TODO output your page here. You may use following sample code. */
            switch (action) {
                case "add":
                    String mobileID = request.getParameter("MobileId");
                    if (mobileID != null && !mobileID.trim().isEmpty()) {
                        session.setAttribute("MobileId", mobileID); 
                        url = addToCartPage;
                    } else {
                        session.setAttribute("Message", " Lsck of detail");
                        url = viewPage;
                    }
                    break;
                case "Find Price Range":
                    String minPrm = request.getParameter("txtMinValue");
                    String maxPrm = request.getParameter("txtMaxValue");
                    float minValue,
                     maxValue;
                    if (minPrm == null || minPrm.isEmpty()) {
                        minValue = Float.NEGATIVE_INFINITY;
                    } else {
                        minValue = Float.parseFloat(minPrm);
                    }
                    if (maxPrm == null || maxPrm.isEmpty()) {
                        maxValue = Float.POSITIVE_INFINITY;
                    } else {
                        maxValue = Float.parseFloat(maxPrm);
                    }
                    session.setAttribute("MinValue", minValue);
                    session.setAttribute("MaxValue", maxValue);
                    url = viewRangePricePage;
                    break;
                case "ViewList":
                    MobileDAO mobileDAO = new MobileDAO();
                    List<Mobile> mobileList = mobileDAO.getMobileList();
                    session.setAttribute("MobileList", mobileList);
                    String message = (String) request.getAttribute("Message");
                    session.setAttribute("Message", message);
                    url = viewPage;
                    break;
                case "View Cart":
                    url = viewCartPage;
                    break;
                case "Remove":
                    url = removeProcess;
                    break;
                case "Save":
                    url = saveProcess;
                    break;
                default:
                    break;
            }

        } catch (Exception ex) {
            System.out.println("MewMew");
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
