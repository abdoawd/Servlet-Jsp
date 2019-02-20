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

public class UserHomeServlet extends HttpServlet {

    ProductDao handler = new ProductDao();
    RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String searchWord = request.getParameter("search");
        String category = request.getParameter("category_id");
        String startPrice = request.getParameter("start_salary");
        String endPrice = request.getParameter("end_salry");

        int category_id;

        List<Product> products = null;
        List<ProductCategory> categories = null;
        if (searchWord != null) {
//            products = handler.getProductByName(searchWord);
            products = handler.getProductByName(searchWord);
            System.out.println("products size =" + products.size());
            System.out.println("products size = ");
        } else if (category != null) {
            category_id = Integer.parseInt(category);

            products = handler.getProductsByCategoryId(category_id);

        } else if (endPrice != null && startPrice != null) {
//             category_id = Integer.parseInt(category);

//            int startPriceInt = Integer.parseInt(startPrice);
//            int endPriceInt = Integer.parseInt(endPrice);
            products = handler.getProductsByNmaeAndPrice(1, "as", 10, 100);
            System.out.println("products size = " + products.size());

        } else {
            products = handler.getAllProducts(-1);

        }

        categories = handler.getProductCategories();

        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        dispatcher = request.getRequestDispatcher("/user/shop.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<ProductCategory> categories = handler.getProductCategories();
        int userId = 1;
        List<Product> products = handler.getAllProducts(0);
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
