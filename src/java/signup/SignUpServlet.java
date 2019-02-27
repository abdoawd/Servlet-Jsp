package signup;

import beans.User;
import db.UsersDao;
import static filter.ValidationFilter.VALID_EMAIL_ADDRESS_REGEX;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignUpServlet extends HttpServlet {

    UsersDao handler = new UsersDao();
    RequestDispatcher dispatcher, dispatcherSignUp;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String jop = request.getParameter("job");
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (matcher.find()) {
            if (password.equals(confirmPassword)) {

                if (!handler.isEmailExist(email)) {
                    boolean isSignUp = handler.addUser(firstName, lastName, email, password, jop);
                    if (isSignUp) {
                        user = handler.login(email, password);
                        HttpSession session = request.getSession(true);
                        session.setAttribute("user", user);

//                    request.getRequestDispatcher("account/AddInterest").include(request, response);
                        response.sendRedirect("account/AddInterest");

                    } else {
                        request.setAttribute("emailNotValid", "Email Not Valid");
//                response.sendRedirect("pages/signup.jsp");
                        request.getRequestDispatcher("pages/signup.jsp").include(request, response);

                    }
                } else {
                    request.setAttribute("emailNotValid", "This Email Is Already Exist");
                    request.getRequestDispatcher("pages/signup.jsp").include(request, response);
                }
            } else {
                request.setAttribute("emailNotValid", "Passwords are not Identical");

                request.getRequestDispatcher("pages/signup.jsp").include(request, response);
            }
        } else {
            request.setAttribute("emailNotValid", "Email Not Valid");

            request.getRequestDispatcher("pages/signup.jsp").include(request, response);

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatcherSignUp = request.getRequestDispatcher("/pages/signup.jsp");
        dispatcherSignUp.forward(request, response);
    }

}
