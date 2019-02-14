/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import beans.ProductCategory;
import dao.DatabaseHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author A7med
 */
@WebServlet(name = "AddProductServlet", urlPatterns = {"/admin/addProduct"})
@MultipartConfig
public class AddProductServlet extends HttpServlet {

    DatabaseHandler handler = new DatabaseHandler();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        double productPrice = Double.parseDouble(request.getParameter("productPrice"));
        int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
        String productCategory = request.getParameter("productCategory");
        double productDiscount = Double.parseDouble(request.getParameter("productDiscount"));

        Part filePart = request.getPart("productImage");
        InputStream  picInputStream = filePart.getInputStream();

        boolean isProductAdded = handler.addProduct(productName, productQuantity, productPrice, productDiscount,
                productCategory, picInputStream, productDescription);
        
        List<ProductCategory> productCategotyList = handler.getProductCategories();
        request.setAttribute("productCategotyList", productCategotyList);
        request.setAttribute("isProductAdded", isProductAdded);
        RequestDispatcher dispatcher = request.getRequestDispatcher("addProduct.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<ProductCategory> productCategotyList = handler.getProductCategories();
        request.setAttribute("productCategotyList", productCategotyList);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("addProduct.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    
}
