package login;

import beans.User;
import db.UsersDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utility.Constants;

public class LoginServlet extends HttpServlet {

    UsersDao usersDao;
    RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();     // request is an instance of type 
        String userEmail = null;
        String userPassword = null;
        String remember = null;
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals("userEmail")) {
                userEmail = cookie.getValue();
            }
            if (cookie.getName().equals("userPassword")) {
                userPassword = cookie.getValue();

            }
            if (cookie.getName().equals("remember")) {
                remember = cookie.getValue();

            }

        }
        if (userEmail != null && userPassword != null) {
            request.setAttribute("userEmail", userEmail);
            request.setAttribute("userPassword", userPassword);
            request.setAttribute("remember", remember);
        }
        request.getRequestDispatcher("pages/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String remeberMe = request.getParameter("remember");

        usersDao = new UsersDao();

        String name = request.getParameter("email");

        String password = request.getParameter("password");
        User user = usersDao.login(name, password);
        System.out.println("remer me   " + remeberMe);
        if (user != null) {

            out.println("log in successfully ");
            out.print("role=" + user.getRole());
            out.print("role=" + user.getFirstName());

            // session . add user()
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);

            session.setAttribute("loggedIn", Constants.LOGGED_IN);
            if (remeberMe.equalsIgnoreCase("checked")) {
                Cookie emailCookie = new Cookie("userEmail", user.getEmail());
                Cookie passwordCookie = new Cookie("userPassword", user.getPassword());
                Cookie rememberCookie = new Cookie("remember", user.getEmail());

                emailCookie.setMaxAge(24 * 60 * 60);
                passwordCookie.setMaxAge(24 * 60 * 60);
                rememberCookie.setMaxAge(24 * 60 * 60);
                response.addCookie(emailCookie);
                response.addCookie(passwordCookie);
                response.addCookie(rememberCookie);

            }

            if (user.getRole().equals("admin")) {
                System.out.println("admin");
                response.sendRedirect("admin");
                out.print("role=" + user.getRole());
            } else {
                System.out.println("user");
                out.print("role=" + user.getRole());

                response.sendRedirect("UserHomeServlet");
            }

        } else {
            out.println("log in faild ");
        }
    }
}
