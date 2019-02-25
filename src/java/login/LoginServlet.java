package login;

import beans.Address;
import beans.User;
import db.AddressDao;
import db.UsersDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        usersDao = new UsersDao();
        addressHandler=new AddressDao();
        String name = request.getParameter("email");
        String password = request.getParameter("password");
        User user = usersDao.login(name, password);
        if (user != null) {
           Address userAddress=   addressHandler.getAddress(user.getId());
           if(userAddress!=null)
          user.setAddress(userAddress);
            // session . add user()
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);

            session.setAttribute("loggedIn", Constants.LOGGED_IN);

            if (user.getRole().equals("admin")) {
                System.out.println("admin");
                response.sendRedirect("admin");
                out.print("role=" + user.getRole());
            } else {
                System.out.println("user");
                response.sendRedirect("UserHomeServlet");
            }

        } else {
            out.println("log in faild ");
        }
    }
}
