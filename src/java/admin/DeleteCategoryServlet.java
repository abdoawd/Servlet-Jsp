/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import beans.ProductCategory;
import db.CategoryDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author A7med
 */
@WebServlet(name = "DeleteCategoryServlet", urlPatterns = {"/admin/deleteCategory"})
public class DeleteCategoryServlet extends HttpServlet {
    
    CategoryDao handlerCategory = new CategoryDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<ProductCategory> categoryList = handlerCategory.getProductCategories();
        request.setAttribute("categoryList", categoryList);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("deleteCategory.jsp");
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
        
        int categoryIdInput = Integer.parseInt(request.getParameter("categoryIdInput"));
        int isCategoryDeleted = handlerCategory.deleteCategory(categoryIdInput);
        
        request.setAttribute("isCategoryDeleted", isCategoryDeleted);
        processRequest(request, response);
    }
}