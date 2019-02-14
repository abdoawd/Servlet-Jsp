/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import dao.DatabaseHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
        long productPrice = Long.parseLong(request.getParameter("productPrice"));
        int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
        Part filePart = request.getPart("productImage");
        String productCategory = request.getParameter("productCategory");
        long productDiscount = Long.parseLong(request.getParameter("productDiscount"));

        InputStream  picInputStream = filePart.getInputStream();

        System.out.println(productName);
        System.out.println(productDescription);
        System.out.println(productPrice);
        System.out.println(productQuantity);
        System.out.println(filePart.toString());
        System.out.println(productCategory);
        System.out.println(productDiscount);

        boolean isProductAdded = handler.addProduct(productName, productQuantity, productPrice, productDiscount,
                productCategory, picInputStream, productDescription);
        System.out.println(isProductAdded);
        
        request.setAttribute("isProductAdded", isProductAdded);
        RequestDispatcher dispatcher = request.getRequestDispatcher("addProduct.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
