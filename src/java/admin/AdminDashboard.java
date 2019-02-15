
package admin;

import db.ProductDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.Constants;


@WebServlet(name = "AdminDashboard", urlPatterns = {"/admin"})
public class AdminDashboard extends HttpServlet {

    ProductDao handler = new ProductDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productsNumber = handler.getTablesCounter(Constants.PRODUCT_TABLE_NAME);
        int categoriesNumber = handler.getTablesCounter(Constants.CATEGORY_TABLE_NAME);
        int ordersNumber = handler.getTablesCounter(Constants.ORDER_TABLE_NAME);
        int usersNumber = handler.getTablesCounter(Constants.USER_TABLE_NAME);

        request.setAttribute("productsNumber", productsNumber);
        request.setAttribute("categoriesNumber", categoriesNumber);
        request.setAttribute("ordersNumber", ordersNumber);
        request.setAttribute("usersNumber", usersNumber);

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/admin.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
