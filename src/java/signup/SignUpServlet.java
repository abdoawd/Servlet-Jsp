package signup;
import beans.User;
import db.UsersDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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
        String jop = request.getParameter("job");
          
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
