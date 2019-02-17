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

/**
 *
 * @author abdullah
 */
@WebServlet(name = "ProductDetails", urlPatterns = {"/ProductDetails"})
public class ProductDetails extends HttpServlet {

    Product product = new Product();
    ProductDao productDao = new ProductDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("paramter " + req.getParameter("product_id"));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("product_id"));
        System.out.println("product id = " + id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("pages/product.jsp");

        product = productDao.getProductById(id);
                System.out.println("product name = " + product.getName());

        req.setAttribute("product", product);
        dispatcher.include(req, resp);

    }

}
