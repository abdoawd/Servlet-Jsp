package login;

import beans.Address;
import beans.User;
import db.AddressDao;
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
    AddressDao addressHandler;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        usersDao = new UsersDao();
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

        if (userEmailCookie != null && userPasswordCookie != null) {
            User user = getUserFromDatabase(rememberMe, rememberMe);
            putUserIntoSession(user, request, response);
            request.getRequestDispatcher("user/shop.jsp").forward(request, response);

        } else {
            request.getRequestDispatcher("pages/login.jsp").forward(request, response);

        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String remeberMe = request.getParameter("remember");
        usersDao = new UsersDao();
        addressHandler = new AddressDao();

        String name = request.getParameter("email");
        Cookie[] cookie = request.getCookies();
        String password = request.getParameter("password");

        User user = getUserFromDatabase(name, password);

        if (user != null) {
            Address userAddress = addressHandler.getAddress(user.getId());
            if (userAddress != null) {
                user.setAddress(userAddress);
            }
            // session . add user()
            if (remeberMe != null && remeberMe.equalsIgnoreCase("on")) {
                {
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

            } else {
                Cookie emailCookie = new Cookie("userEmail", "");
                Cookie passwordCookie = new Cookie("userPassword", "");
                emailCookie.setMaxAge(0);
                passwordCookie.setMaxAge(0);
                response.addCookie(emailCookie);
                response.addCookie(passwordCookie);
            }
            putUserIntoSession(user, request, response);
            if (user.getRole().equals("admin")) {
                System.out.println("admin");
                response.sendRedirect("admin");
                out.print("role=" + user.getRole());
            } else {
                System.out.println("user");
                out.print("role=" + user.getRole());
                response.sendRedirect("shop");
            }

        } else {
            out.println("log in faild ");
        }
    }

    User getUserFromDatabase(String email, String password) {
        return usersDao.login(email, password);
    }

    void putUserIntoSession(User user, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
        session.setAttribute("loggedIn", Constants.LOGGED_IN);
        session.setMaxInactiveInterval(1000*60*60*24*24);

    }

}
