package login;

import beans.User;
import db.UsersDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Cookie[] cookies = request.getCookies();
        String remeberMeParam = request.getParameter("remember");

        String userEmailCookie = null;
        String userPasswordCookie = null;
        String rememberMe = null;
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    if (cookie.getName().equals("userEmail")) {
                        userEmailCookie = cookie.getValue();
                    }
                    if (cookie.getName().equals("userPassword")) {
                        userPasswordCookie = cookie.getValue();
                    }
                    if (cookie.getName().equals("remember")) {
                        rememberMe = cookie.getValue();
                    }

                }
            }
        
        if (userEmailCookie != null && userPasswordCookie != null && rememberMe != null) {
            System.out.println("insid if cookie");
            request.setAttribute("userEmail", userEmailCookie);
            request.setAttribute("userPassword", userPasswordCookie);
            request.setAttribute("remember", rememberMe);
//<c:if test="${remember}">checked</c:if>
        }
        request.getRequestDispatcher("pages/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
         String remeberMe = request.getParameter("remember");
        usersDao = new UsersDao();
        String name = request.getParameter("email");
        Cookie[] cookie = request.getCookies();
        String password = request.getParameter("password");
        User user = usersDao.login(name, password);
        if (user != null) {

            out.println("log in successfully ");
            out.print("role=" + user.getRole());
            out.print("role=" + user.getFirstName());

            // session . add user()
            if (remeberMe != null && remeberMe.equalsIgnoreCase("on")) {
                if (cookie == null) {
                    System.out.println("inside remmber me if ");
                    Cookie emailCookie = new Cookie("userEmail", user.getEmail());
                    Cookie passwordCookie = new Cookie("userPassword", user.getPassword());
                    Cookie rememberCookie = new Cookie("remember", "checked");
                    emailCookie.setMaxAge(24 * 60 * 60);
                    passwordCookie.setMaxAge(24 * 60 * 60);
                    rememberCookie.setMaxAge(24 * 60 * 60);
                    response.addCookie(emailCookie);
                    response.addCookie(passwordCookie);
                    response.addCookie(rememberCookie);
                }
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setAttribute("loggedIn", Constants.LOGGED_IN);

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
