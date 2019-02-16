package login;

import beans.User;
import db.UsersDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    UsersDao usersDao;
    RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        usersDao = new UsersDao();
        String name = request.getParameter("email");
        String password = request.getParameter("password");

        User user = usersDao.login(name, password);
        if (user != null) {
            System.out.println("log in successfully ");
            dispatcher = request.getRequestDispatcher("UserHomeServlet");
            dispatcher.forward(request, response);
        } else {
            System.out.println("log in faild ");

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
