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
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    UsersDao usersDao;
    RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        usersDao = new UsersDao();
        String name = request.getParameter("email");
        String password = request.getParameter("password");
        User user = usersDao.login(name, password);
        if (user != null) {
            // session . add user()
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            dispatcher = request.getRequestDispatcher("/user/shop.jsp");
            dispatcher.forward(request, response);
            
            if (user.getRole().equals("admin")) {
                System.out.println("admin");
                response.sendRedirect("admin");

            } else {
                System.out.println("user");

                response.sendRedirect("UserHomeServlet");

            }

        } else {
            out.println("log in faild ");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
