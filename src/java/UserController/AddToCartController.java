/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package UserController;

import Models.DAO.MobileDAO;
import Models.DTO.CartItem;
import Models.DTO.Mobile;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;

/**
 *
 * @author Mainh
 */
public class AddToCartController extends HttpServlet {

    private final String managePage = "ManageCartController";
    private final String showPage = "ViewMobileList.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = null;
        Mobile mobile = null;
        CartItem cartItem = null;
        HashMap<String, CartItem> itemsInCart = null;
        HttpSession shoppingCart = request.getSession();
        String message = "";
        String mobileId = (String) request.getParameter("MobileId");     

        try {
            MobileDAO mobileDAO = new MobileDAO();
            itemsInCart = (HashMap<String, CartItem>) shoppingCart.getAttribute("Cart");

            if (itemsInCart == null) {
                itemsInCart = new HashMap<>();
                shoppingCart.setAttribute("Cart", itemsInCart);
            }

            mobile = mobileDAO.getMobileById(mobileId);

            if (mobile == null) {
                message = "Mobile not found.";
            } else {
                cartItem = itemsInCart.get(mobile.getMobileID());

                if (cartItem == null) {
                    cartItem = new CartItem(
                            mobile.getMobileID(),
                            mobile.getDescription(),
                            mobile.getPrice(),
                            mobile.getMobileName(),
                            mobile.getYearOfProduction(),
                            1,
                            mobile.isNotSale()
                    );
                    itemsInCart.put(cartItem.getMobileID(), cartItem);
                    message = "Mobile " + mobile.getMobileName() + " has been added to the cart.";
                } else {
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    message = "Increased quantity of mobile " + mobile.getMobileName() + " in cart.";
                }
            }

            request.setAttribute("Message", message);
            url = managePage + "?action=ViewList"; 
        } catch (Exception ex) {
            ex.printStackTrace();  
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
