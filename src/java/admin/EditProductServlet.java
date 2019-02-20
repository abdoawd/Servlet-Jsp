/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import beans.Product;
import beans.ProductCategory;
import db.ProductDao;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author A7med
 */
@WebServlet(name = "editProduct", urlPatterns = {"/admin/editProduct"})
public class EditProductServlet extends HttpServlet {

    ProductDao handler = new ProductDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productId = request.getParameter("productIdInput");
        if (productId == null) {
            List<Product> productsList = handler.getAllProducts(0);
            request.setAttribute("productsList", productsList);
        } else {
            List<Product> productsList = handler.getAllProducts(Integer.parseInt(productId));
            Product selectedProduct = productsList.get(0);

            List<Product> productsListAll = handler.getAllProducts(0);
            request.setAttribute("productsList", productsListAll);

            List<ProductCategory> productCategotyList = handler.getProductCategories();
            request.setAttribute("productCategotyList", productCategotyList);
            request.setAttribute("selectedProduct", selectedProduct);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("editProduct.jsp");
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
//        processRequest(request, response);
//        if (request.getParameter("productName") != null) {
        
        String id = request.getParameter("toEditProductId");
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        String productPrice = request.getParameter("productPrice");
        String productQuantity = request.getParameter("productQuantity");
        String productCategory = request.getParameter("productCategory");
        String productDiscount = request.getParameter("productDiscount");
        
        System.out.println(id);
            /*Part filePart = request.getPart("productImage");*/
            InputStream picInputStream = null/*filePart.getInputStream()*/;
        
        boolean isSucceed = handler.updateProduct(id, productName, productDescription, productPrice, productQuantity, productCategory, productDiscount, picInputStream);
        request.setAttribute("isSucceed", isSucceed);
//        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("editProduct.jsp");
        dispatcher.forward(request, response);
    }
}
