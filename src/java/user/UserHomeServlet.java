package user;

import beans.Product;
import beans.ProductCategory;
import dao.DatabaseHandler;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserHomeServlet extends HttpServlet {

    DatabaseHandler handler = new DatabaseHandler();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // handker to get  categories 
        // handler  ti get intersts user product 
        List<ProductCategory> categories = handler.getProductCategories();

        // we have to get user ud from object that we will create it on the session
        // and this comment just to remember 
        int userId = 1;
        List<Product> products = handler.getInterstsProduct(userId);
        System.out.println("product size = " + products.size());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}