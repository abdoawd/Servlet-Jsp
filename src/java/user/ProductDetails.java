/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import beans.Product;
import db.ProductDao;
import db.UsersDao;
import java.awt.Image;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.Constants;

/**
 *
 * @author abdullah
 */
@WebServlet(name = "ProductDetails", urlPatterns = {"/ProductDetails"})
public class ProductDetails extends HttpServlet {

    Product product = new Product();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("pages/product.jsp");
        product.setCategoryId("1");
        product.setDescription("anas mohamed");
        product.setDiscount(12);
        product.setId("1");
        product.setName("labtop");
        product.setPrice(12);
        product.setQuantity("65432");
        req.setAttribute("product", product);
        dispatcher.include(req, resp);

    }

}
