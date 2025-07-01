/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package StaffController;

import Models.DAO.MobileDAO;
import Models.DTO.Mobile;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mainh
 */
public class SearchServlet extends HttpServlet {

    private final String failPage = "LoginFailed.jsp";
    private final String searchByNamePage = "ViewMobileByName.jsp";
    private final String searchByIdPage = "ViewMobileById.jsp";    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = failPage;
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String action = request.getParameter("btnAction");

        try  {
            /* TODO output your page here. You may use following sample code. */
            MobileDAO mobileDAO = new MobileDAO();
            List<Mobile> mobileList = new ArrayList();
            Mobile mobileItem = null;
            if (action != null) {
                if (action.equals("SearchByName")) {
                    url = searchByNamePage;
                    String mobileName = request.getParameter("txtMobileName");
                    mobileList = mobileDAO.getMobileByName(mobileName);
                    session.setAttribute("MobileList", mobileList);
                } 
                else if (action.equals("SearchById")) {
                    url = searchByIdPage;
                    String mobileId = request.getParameter("txtMobileId");
                    mobileItem = mobileDAO.getMobileById(mobileId);
                    session.setAttribute("MobileItem", mobileItem);
                }
            }

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
