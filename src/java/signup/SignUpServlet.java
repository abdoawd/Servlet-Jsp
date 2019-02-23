package signup;

import beans.Address;
import beans.User;
import db.AddressDao;
import db.ProductDao;
import db.UsersDao;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

public class SignUpServlet extends HttpServlet {

    UsersDao handler = new UsersDao();
    RequestDispatcher dispatcher;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String jop = request.getParameter("jop");
        User user = new User();    
        user = handler.addUser(firstName, lastName, email, password, jop);
        if(user!=null ){
         HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            dispatcher = request.getRequestDispatcher("/user/shop.jsp");
            dispatcher.forward(request, response);
            
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
