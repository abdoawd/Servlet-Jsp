/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import dao.DatabaseHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jdt.internal.compiler.impl.Constant;
import utility.Constants;

/**
 *
 * @author A7med
 */
@WebServlet(name = "AdminDashboard", urlPatterns = {"/admin"})
public class AdminDashboard extends HttpServlet {

    DatabaseHandler handler = new DatabaseHandler();
    
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
