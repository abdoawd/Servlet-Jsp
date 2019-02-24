/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import beans.Product;
import beans.ProductCategory;
import db.CategoryDao;
import db.ProductDao;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import utility.Constants;

/**
 *
 * @author A7med
 */
@WebServlet(name = "editProduct", urlPatterns = {"/admin/editProduct"})
@MultipartConfig
public class EditProductServlet extends HttpServlet {

    ProductDao handler = new ProductDao();
    CategoryDao handlerCategory = new CategoryDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productId = request.getParameter("productIdInput");

        List<Product> productsListAll = handler.getAllProducts(Constants.SELECT_ACTIVE);
        request.setAttribute("productsList", productsListAll);

        if (productId != null) {
            List<Product> productsList = handler.getAllProducts(Integer.parseInt(productId));
            Product selectedProduct = productsList.get(0);

            List<ProductCategory> productCategotyList = handlerCategory.getProductCategories();
            request.setAttribute("productCategotyList", productCategotyList);
            request.setAttribute("selectedProduct", selectedProduct);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("editProduct.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Product product = new Product();
        String productId = request.getParameter("productId");
        System.out.println(productId);

        product.setId(Integer.parseInt(productId));
        product.setName(request.getParameter("productName"));
        product.setDescription(request.getParameter("productDescription"));
        product.setPrice(Double.parseDouble(request.getParameter("productPrice")));
        product.setQuantity(Integer.parseInt(request.getParameter("productQuantity")));
        product.setCategoryId(Integer.parseInt(request.getParameter("productCategory")));
        product.setDiscount(Double.parseDouble(request.getParameter("productDiscount")));

        product.setPart(request.getPart("productImage"));
//        Part filePart = request.getPart("productImage");
//        InputStream picInputStream = filePart.getInputStream();

        boolean isSucceed = handler.updateProduct(product);
        request.setAttribute("isSucceed", isSucceed);

        RequestDispatcher dispatcher = request.getRequestDispatcher("editProduct.jsp");
        dispatcher.forward(request, response);
    }
}
