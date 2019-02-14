package signup;

import dao.DatabaseHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpServlet extends HttpServlet {

    DatabaseHandler handler = new DatabaseHandler();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String jop = request.getParameter("jop");
        
        
       boolean isSinUp=handler.addUser( firstName , lastName,  email ,  password, jop);
        System.out.println("is sin up = " + isSinUp);

//        writer.println("username = " + firstName);
//        writer.println("password = " + lastName);
//        writer.println("email = " + email);
//        writer.println("password = " + password);
//        writer.println("confirmPassword = " + confirmPassword);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
