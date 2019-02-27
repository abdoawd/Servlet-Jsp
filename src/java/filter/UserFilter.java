/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import beans.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.Constants;

/**
 *
 * @author Abdelrahman
 */
public class UserFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        PrintWriter out = new PrintWriter(System.out);
        // Check if the user is loggedIn
        if (req.getSession().getAttribute("loggedIn") != null) { // If any user is logged in
            int loggedIn = (int) req.getSession().getAttribute("loggedIn");
            User user = (User) req.getSession().getAttribute("user");
            if (loggedIn == Constants.LOGGED_IN & user != null) {
                if (user.getRole().equals("user")||user.getRole().equals("admin")) {
                    System.out.println("user");
//                    response.sendRedirect("admin");
                    // pass the request along the filter chain 
                    chain.doFilter(request, response);
                } else {
                    System.out.println("Not admin");
                    res.sendRedirect(req.getContextPath() + "/error/errorAdminFilter.jsp");
                }
            } else {
                System.out.println("Not logged in");
                res.sendRedirect(req.getContextPath() + "/Login");
            }
        } else {
            System.out.println("Session problem - Cannot get loggedIn attribue");
            res.sendRedirect(req.getContextPath() + "/Login");
        }

        System.out.println(
                "Filter called 2 ~~~~~~~~~~~~\t\t 2");    }

    @Override
    public void destroy() {
    }
    
}
