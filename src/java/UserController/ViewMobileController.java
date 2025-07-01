package UserController;

import Models.DAO.MobileDAO;
import Models.DTO.Mobile;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewMobileController extends HttpServlet {

    private static final int ITEMS_PER_PAGE = 10;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String url = "ViewMobileList.jsp";
        String actionChoice = request.getParameter("actionChoice");

        try {
            MobileDAO mobileDAO = new MobileDAO();
            List<Mobile> fullList = mobileDAO.getMobileList();

            // ✅ Get action type (optional)
            String action = request.getParameter("cartOption");

            // ✅ Determine price filtering
            boolean isPriceSearch = "Price".equals(action);
            float minValue = Float.NEGATIVE_INFINITY;
            float maxValue = Float.POSITIVE_INFINITY;

            List<Mobile> filteredList = new ArrayList<>();

            if (isPriceSearch) {
                String minParam = (String) session.getAttribute("MinValue");
                String maxParam = (String) session.getAttribute("MaxValue");

                if (minParam != null && !minParam.isEmpty()) {
                    minValue = Float.parseFloat(minParam);
                }
                if (maxParam != null && !maxParam.isEmpty()) {
                    maxValue = Float.parseFloat(maxParam);
                }

                // 🔎 Filter by price
                for (Mobile mobile : fullList) {
                    if (mobile.getPrice() >= minValue && mobile.getPrice() <= maxValue) {
                        filteredList.add(mobile);
                    }
                }

                // Update the list for pagination
                fullList = filteredList;
            }

            // 🔢 Pagination
            int totalItems = fullList.size();
            int totalPages = (int) Math.ceil((double) totalItems / ITEMS_PER_PAGE);

            int currentPage = 1;
            String pageParam = request.getParameter("page");
            if (pageParam != null && pageParam.matches("\\d+")) {
                currentPage = Integer.parseInt(pageParam);
                if (currentPage < 1) currentPage = 1;
                if (currentPage > totalPages) currentPage = totalPages;
            }

            int startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
            int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, totalItems);
            List<Mobile> pageList = fullList.subList(startIndex, endIndex);

            // 🧾 Set pagination attributes
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", totalPages);

            // ✅ Set result list to session
            session.setAttribute("MobileList", pageList);

            // 🌐 Decide view
            if ("Price".equals(action) || actionChoice.equals("ViewMobileByPrice")) {
                url = "ViewBookByPrice.jsp";
                session.setAttribute("MinValue", minValue);
                session.setAttribute("MaxValue", maxValue);
                request.setAttribute("Message", "Showing mobiles in price range $" + minValue + " - $" + maxValue);
            }

        } catch (Exception ex) {
            request.setAttribute("Message", "⚠ Error: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
        return "Controller to view mobiles with price filtering and pagination (10 items/page)";
    }
}
