/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import beans.Product;
import db.ProductDao;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.Constants;

/**
 *
 * @author A7med
 */
@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/admin/deleteProduct"})
public class DeleteProductServlet extends HttpServlet {

    ProductDao handler = new ProductDao();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Product> productsList = handler.getAllProducts(0);
        request.setAttribute("productsList", productsList);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("deleteProduct.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String productId = request.getParameter("productId");
            boolean isProductDeleted = handler.deleteMethod(productId, Constants.PRODUCT_TABLE_NAME);
            request.setAttribute("isProductDeleted", isProductDeleted);
            
            List<Product> productsList = handler.getAllProducts(0);
            request.setAttribute("productsList", productsList);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("deleteProduct.jsp");
            dispatcher.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(DeleteProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
