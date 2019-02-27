/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import beans.Order;
import beans.OrderItem;
import beans.User;
import beans.UserShoppingCart;
import db.OrderDao;
import db.OrderItemDao;
import db.ProductDao;
import db.UserCartDAO;
import db.UsersDao;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utility.Utils;

/**
 *
 * @author abdullah
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {

    UserCartDAO userCartDAO = new UserCartDAO();
    ProductDao productDao = new ProductDao();
    UsersDao usersDao = new UsersDao();
    List<UserShoppingCart> checkoutCartList = new ArrayList<>();
    OrderDao orderDao = new OrderDao();
    OrderItemDao orderItemDao = new OrderItemDao();
    OrderItem item;
    double newCreditLimit;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int totalPrices =-1;
        if(request.getParameter("totalsum")!=null){
                     totalPrices = getTotalAmount(request.getParameter("totalsum"));

        }
        else{
                    request.getRequestDispatcher("checkout.jsp").forward(request, response);

        }

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        checkoutCartList = userCartDAO.getUserCheckoutCart(user.getId());
        System.out.println("check out list " + checkoutCartList.size());
//        System.out.println("check out list quntitiyu " + checkoutCartList.get(0).getQuantity());
//        System.out.println("check out list name " + checkoutCartList.get(0).getProductName());
        System.out.println("check out list price " + checkoutCartList.get(0).getProductPrice());
        request.setAttribute("totalPrices", totalPrices);
        request.setAttribute("checkoutCartList", checkoutCartList);
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        int productColumnsEffected = -1, creditLimitColumnEffected = -1;
        boolean isCartCleard;
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String street = request.getParameter("street");

        int totalAmount = -1;
        if (getTotalAmount(request.getParameter("total_price")) < 0) {
            totalAmount = getTotalAmount(request.getParameter("total_price"));
        } else {
            request.getRequestDispatcher("checkout.jsp").forward(request, response);

        }
        System.out.println("total amount " + totalAmount);
        Order order = new Order();
        order.setStatus("completed");
        order.setUserId(userId);
        String ordeTime = Utils.GetDate();
        order.setOrderTime(ordeTime);
        order.setTotalAmount(totalAmount);
        double creditLimit = user.getCreditlimits();
        System.out.println("credit limit  = ");
        if (creditLimit >= totalAmount) {
            if (orderDao.addOrder(order)) {
                int orderNumber = orderDao.getOrderNumber(userId, ordeTime);
                if (orderNumber > 0) {
                    checkoutCartList = userCartDAO.getUserCheckoutCart(userId);
                    System.out.println("checkoutCartList size = " + checkoutCartList.size());
                    for (int i = 0; i < checkoutCartList.size(); i++) {
                        item = new OrderItem();
                        item.setOrderNumber(orderNumber);
                        item.setProductId(Integer.parseInt(checkoutCartList.get(i).getProductId()));
                        item.setQuantity(Integer.parseInt(checkoutCartList.get(i).getUserCartProductQuantity()));

                        orderItemDao.addOrder(item);

                        productColumnsEffected = productDao.updateProductQuantity(item.getQuantity(), item.getProductId());
                        out.println("item number " + i + " add ");
                    }
                    newCreditLimit = user.getCreditlimits() - totalAmount;
                    if (productColumnsEffected > 0) {
                        System.out.println("in productColumnsEffected if");
                        creditLimitColumnEffected = usersDao.updateUserCreditLimit(newCreditLimit, userId);
                        if (creditLimitColumnEffected > 0) {
                            System.out.println("in creditLimitColumnEffected if");

                            isCartCleard = userCartDAO.ClearUserCart(userId);
                            if (isCartCleard) {
                                System.out.println("in isCartCleard if");

                                response.sendRedirect("UserHomeServlet");
                            } else {

                            }
                        }
                    }

                }
                out.println("order add successfully");
            } else {
                out.println("order cannot add ");
            }

        } else {

            System.out.println("Your Credit is not Enughe");
        }
    }

    private int getTotalAmount(String totalAmount) {
        if(totalAmount!=null){
        totalAmount = totalAmount.replace("Total ", "");
        totalAmount = totalAmount.replace(" EGP", "");
        return Integer.parseInt(totalAmount);
        }
        else{
            return -1;
        }
       
    }
}
