/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import db.UsersDao;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author abdullah
 */
@WebFilter(filterName = "ValidationFilter", servletNames = {"SinuUpServlett"})
public class ValidationFilter implements Filter {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private FilterConfig filterConfig;
    private String userEmail;
    UsersDao usersDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        usersDao = new UsersDao();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        userEmail = request.getParameter("email");
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(userEmail);
        if (!usersDao.isEmailExist(userEmail)) {
            System.out.println("email in if" + userEmail);

            if (matcher.find()) {
                System.out.println("email valid" + userEmail);

                chain.doFilter(request, response);
            } else {
                filterConfig.getServletContext().setAttribute("emailValid", "Email Not Valid");
                request.getRequestDispatcher("/pages/signup.jsp").include(request, response);
            }
        } else {
            filterConfig.getServletContext().setAttribute("emailExist", "Email Exist");
            request.getRequestDispatcher("/pages/signup.jsp").include(request, response);
        }

    }

    @Override
    public void destroy() {
    }

}
