package signup;

import beans.Address;
import beans.ProductCategory;
import beans.User;
import db.AddressDao;
import db.CategoryDao;
import db.ProductDao;
import db.UsersDao;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

public class SignUpServlet extends HttpServlet {

    UsersDao handler = new UsersDao();
    RequestDispatcher dispatcher,dispatcherSignUp;
    
   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       User user=new User();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String jop = request.getParameter("jop");
          
        boolean isSignUp = handler.addUser(firstName, lastName, email, password, jop);
        if(isSignUp ){
            
            
            
               
           user=handler.login(email, password);
             HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            response.sendRedirect("account/AddInterest");
           
            
        }
        
    }

 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatcherSignUp=request.getRequestDispatcher("/pages/signup.jsp");
        dispatcherSignUp.forward(request, response);
    }

}
