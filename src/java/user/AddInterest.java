/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import beans.ProductCategory;
import beans.User;
import db.CategoryDao;
import db.InterestsDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "AddInterest", urlPatterns = {"/account/AddInterest"})
public class AddInterest extends HttpServlet {
    InterestsDao handlerInerests;
    String []ids=new String[]{};
    User user;
    boolean isAdded;
    RequestDispatcher dispatcher;
     ArrayList<ProductCategory> categories=new ArrayList<>();
     CategoryDao categoryHandler=new CategoryDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        handlerInerests=new InterestsDao();
        ids=request.getParameterValues("Categories");
        
         HttpSession session = request.getSession(false);
           user=(User) session.getAttribute("user");
           if(ids!=null){
        for (String id : ids) {
            System.out.println(id);
           isAdded = handlerInerests.InsertInerests(user.getId(), id);
            if(!isAdded){
                System.out.println("error in adding  ");
            }
                
                
            }
        }
           else
                System.out.println("no selection for interests ");
          response.sendRedirect("../UserHomeServlet");
           
           
           
    }
       
    
@Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

     dispatcher = request.getRequestDispatcher("/user/interests.jsp");
     
     categories=(ArrayList<ProductCategory>) categoryHandler.getProductCategories();
             request.setAttribute("categoryList", categories);
            dispatcher.forward(request, response);
    
}
}
    
 


