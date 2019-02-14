package login;

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
        int i = handler.testConnection();
        String name = request.getParameter("email");
        String password = request.getParameter("password");
        writer.println("username = " + name +" " + i);
        writer.println("password = " + password +" " + i);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
