
package admin;

import beans.ProductCategory;
import db.CategoryDao;
import db.ProductDao;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "AddProductServlet", urlPatterns = {"/admin/addProduct"})
@MultipartConfig
public class AddProductServlet extends HttpServlet {

    ProductDao handler = new ProductDao();
    CategoryDao handlerCategory = new CategoryDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        double productPrice = Double.parseDouble(request.getParameter("productPrice"));
        int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
        String productCategory = request.getParameter("productCategory");
        double productDiscount = Double.parseDouble(request.getParameter("productDiscount"));

        Part filePart = request.getPart("productImage");
        InputStream picInputStream = filePart.getInputStream();

        boolean isProductAdded = handler.addProduct(productName, productQuantity, productQuantity, productQuantity, productCategory, picInputStream, productDescription);
        List<ProductCategory> productCategotyList = handlerCategory.getProductCategories();
        request.setAttribute("productCategotyList", productCategotyList);
        request.setAttribute("isProductAdded", isProductAdded);
        RequestDispatcher dispatcher = request.getRequestDispatcher("addProduct.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<ProductCategory> productCategotyList=null;
        productCategotyList = handlerCategory.getProductCategories();
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
