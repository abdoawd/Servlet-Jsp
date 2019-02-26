/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import beans.ProductCategory;
import beans.User;
import db.InterestsDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author FAMA
 */
@WebServlet(urlPatterns = {"/account/RemoveInterest"})
public class RemoveInterest extends HttpServlet {

    InterestsDao myHandler=new InterestsDao();
    List<ProductCategory> categories;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        categories=myHandler.getAllSelectedCategory(getUser(request));
        RequestDispatcher dispatcher=request.getRequestDispatcher("/user/removeInterest.jsp");
          request.setAttribute("categories",categories );
          dispatcher.include(request, response);
       
        

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         
          String categoryId=request.getParameter("productCategory");
         int userId=getUser(request);
         boolean isRemoved=myHandler.removeInterest(userId, categoryId);
         categories=myHandler.getAllSelectedCategory(userId);
          request.setAttribute("isRemoved", isRemoved);
          request.setAttribute("categories",categories );
            RequestDispatcher dispatcher=request.getRequestDispatcher("/user/removeInterest.jsp");
             
          dispatcher.forward(request, response);
         
         
        
       
    }
    public int getUser(HttpServletRequest request){
      HttpSession session=request.getSession(false);
      if(session!=null){
        User user=(User) session.getAttribute("user");
        return user.getId();
      }
      else 
          return -1;
    }

   
}
