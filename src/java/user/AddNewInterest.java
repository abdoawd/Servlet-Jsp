/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import beans.ProductCategory;
import beans.User;
import db.InterestsDao;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author FAMA
 */

@WebServlet(name = "AddNewInterest", urlPatterns = {"/account/AddNewInterest"})
public class AddNewInterest extends HttpServlet {

    User user;
    InterestsDao handler=new InterestsDao();
    InterestsDao handlerInerests=new InterestsDao();
   boolean isAdded;
    List<ProductCategory>categories=new ArrayList<>();
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        HttpSession session = request.getSession(false);
          user=(User)session.getAttribute("user");
         
        categories=handler.getAllUnSelectedCategory(user.getId());
        System.out.println(categories.get(0));
        request.setAttribute("categoryList", categories);
        RequestDispatcher myDispatcher=request.getRequestDispatcher("/user/addNewInterest.jsp");
        myDispatcher.include(request, response);
        
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryId=request.getParameter("productCategory");
        System.out.println(categoryId);
         HttpSession session = request.getSession(false);
           user=(User) session.getAttribute("user");
           if(categoryId!=null){
     
        
           isAdded = handlerInerests.InsertInerests(user.getId(), categoryId);
            if(!isAdded){
                System.out.println("error in adding  ");
            }
           else
                System.out.println("added ");
           }
            
        categories=handler.getAllUnSelectedCategory(user.getId());
        System.out.println(categories.get(0));
        request.setAttribute("categoryList", categories);
        
          RequestDispatcher dispatcher=request.getRequestDispatcher("/user/addNewInterest.jsp");
          request.setAttribute("isAdded", isAdded);
          dispatcher.forward(request, response);
       
    


}
}
