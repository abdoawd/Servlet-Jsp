/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import beans.Product;
import beans.User;
import db.UserCartDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author abdullah
 */
@WebServlet(name = "UserCart", urlPatterns = {"/UserCart"})
public class UserCart extends HttpServlet {

    List<Product> products = new ArrayList<>();
    UserCartDAO userCartDAO = new UserCartDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        int userId = Integer.valueOf(user.getId());

        if (Boolean.valueOf(req.getParameter("addToCart")) == false) {
            int updateProductId = Integer.valueOf(req.getParameter("productId"));
            int itemNumber = Integer.valueOf(req.getParameter("itemNumber"));
            System.out.println("item number " + itemNumber);
            userCartDAO.updateToCartQuery(userId, updateProductId, itemNumber);
        } else {
            int productId = Integer.valueOf(req.getParameter("product_id"));
            System.out.println("product id " + productId);
            if (!userCartDAO.isProductInCart(userId, productId)) {
                System.out.println("in if ");
                userCartDAO.addToCart(userId, productId, 1);
            }
            products = userCartDAO.getUserCart(userId);

        }
        System.out.println("user id  in cart" + userId);
        System.out.println("size " + products.size());
        System.out.println("ounitity " + products.get(0).getQuantity());
        req.setAttribute("userCartProducts", products);
        req.getRequestDispatcher("cart.jsp").include(req, resp);
    }

}
