package user;

import beans.Product;
import beans.ProductCategory;
import db.ProductDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.Constants;

public class UserHomeServlet extends HttpServlet {

    ProductDao handler = new ProductDao();
    RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // handker to get  categories 
        // handler  ti get intersts user product 

        String searchWord = request.getParameter("search");
        System.out.println("search word = " + searchWord);
        List<Product> products = handler.getProductByName(searchWord);
        List<ProductCategory> categories = handler.getProductCategories();

        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        System.out.println("size of searched ptoducts = " + products.size());
        dispatcher = request.getRequestDispatcher("/user/shop.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<ProductCategory> categories = handler.getProductCategories();
        int userId = 1;
        List<Product> products = handler.getAllProducts(Constants.SELECT_ACTIVE);
        List<Product> interestsProducts = handler.getInterstsProduct(userId);

        System.out.println("product size = " + products.size());
        System.out.println("ProductCategory size = " + categories.size());
        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        dispatcher = request.getRequestDispatcher("/user/shop.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
