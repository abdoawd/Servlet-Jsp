package login;

import beans.User;
import dao.DatabaseHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    DatabaseHandler handler;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        handler = new DatabaseHandler();
        String name = request.getParameter("email");
        String password = request.getParameter("password");
        User  user=handler.login(name, password);
        if(user !=null)
        {
            System.out.println("log in successfully ");
        }
        else
        {
                        System.out.println("log in faild ");

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
