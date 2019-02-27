/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import beans.Order;
import beans.User;
import db.OrderDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserOrdersServlet extends HttpServlet {

    OrderDao dao = new OrderDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        List<Order> list = new ArrayList<>();
        list = dao.getAllOrder(userId);
        System.out.println("list size = "+list.size());

        request.setAttribute("list", list);
        request.getRequestDispatcher("user/orders.jsp").forward(request, response);
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        List<Order> list = new ArrayList<>();
        list = dao.getAllOrder(userId);
        System.out.println("list size ="+list.size());

        request.setAttribute("userCartProducts", list);
        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
