package user;

import beans.Product;
import beans.ProductCategory;
import beans.User;
import db.CategoryDao;
import db.ProductDao;
import db.UserCartDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utility.Constants;

public class UserHomeServlet extends HttpServlet {

    ProductDao handler = new ProductDao();
    CategoryDao handlerCategory = new CategoryDao();
    RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String searchWord = request.getParameter("search");
        String category = request.getParameter("category_id");
        String categoryName = request.getParameter("category");

        String startPrice = request.getParameter("start_salary");
        String endPrice = request.getParameter("end_salary");
        String addProductId = request.getParameter("addProductId");

        int category_id;

        List<Product> products = null;
        List<ProductCategory> categories = null;
        if (searchWord != null) {
//            products = handler.getProductByName(searchWord);
            products = handler.getProductByName(searchWord);
        } else if (category != null) {
            category_id = Integer.parseInt(category);

            products = handler.getProductsByCategoryId(category_id);
        } else if (startPrice != null) {

            category_id = Integer.parseInt(categoryName);
            int startPriceInt = Integer.parseInt(startPrice);
            int endPriceInt = Integer.parseInt(endPrice);
            System.out.println("category_id=" + category_id);
            System.out.println("startPriceInt=" + startPriceInt);
            System.out.println("endPriceInt=" + endPriceInt);

            products = handler.getProductsByNmaeAndPrice(category_id, startPriceInt, endPriceInt);
            System.out.println("products size =" + products.size());

        } else if (addProductId != null) {
            UserCartDAO cartDAO = new UserCartDAO();
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            int userId = user.getId();
            cartDAO.addToCart(userId, Integer.parseInt(addProductId), 1);
            products = handler.getAllProducts(Constants.SELECT_ACTIVE);

        } else {
            products = handler.getAllProducts(Constants.SELECT_ACTIVE);

        }

        categories = handlerCategory.getProductCategories();

        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        dispatcher = request.getRequestDispatcher("/user/shop.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<ProductCategory> categories = handlerCategory.getProductCategories();
        int userId = 1;
        List<Product> products = handler.getAllProducts(Constants.SELECT_ACTIVE);
        List<Product> interestsProducts = handler.getInterstsProduct(userId);

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
