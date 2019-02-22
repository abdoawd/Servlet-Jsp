/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import beans.Product;
import beans.User;
import beans.UserShoppingCart;
import db.ProductDao;
import db.UserCartDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {

    UserCartDAO userCartDAO = new UserCartDAO();
    List<UserShoppingCart> checkoutCartList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int totalPrices = Integer.parseInt(request.getParameter("totalsum"));
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        checkoutCartList = userCartDAO.getUserCheckoutCart(Integer.parseInt(user.getId()));
        System.out.println("check out list " + checkoutCartList.size());
        System.out.println("check out list quntitiyu " + checkoutCartList.get(0).getQuantity());
        System.out.println("check out list name " + checkoutCartList.get(0).getProductName());
        System.out.println("check out list price " + checkoutCartList.get(0).getProductPrice());

        request.setAttribute("totalPrices", totalPrices);
        request.setAttribute("checkoutCartList", checkoutCartList);
        request.getRequestDispatcher("checkout.jsp").include(request, response);
    }

}
