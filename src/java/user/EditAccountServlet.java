/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import beans.Address;
import beans.User;
import db.AddressDao;
import db.UsersDao;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Nesma
 */
@WebServlet(name = "EditAccountServlet", urlPatterns = {"/account/EditAccount"})
@MultipartConfig
public class EditAccountServlet extends HttpServlet {

    AddressDao handlerAddress = new AddressDao();
    UsersDao myDb = new UsersDao();
    AddressDao userAddressHandler=new AddressDao();
    Address userAddress;
    boolean isChanged;
    User updatedUser;
    User userMe;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = new User();
        HttpSession session = request.getSession(false);
       

        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setJob(request.getParameter("job"));
        user.setCreditlimits(Double.parseDouble(request.getParameter("creditlimits")));
        String dateBirthday = request.getParameter("birthday");

        user.setBirthday(dateBirthday);

        Part filePart = request.getPart("profileImage");
        InputStream picInputStream = filePart.getInputStream();
         userMe = (User) session.getAttribute("user");
        user.setId(userMe.getId());
        System.out.println("user id in session " + userMe.getId());
        user = myDb.updateUserData(user, picInputStream);
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String country = request.getParameter("country");

        Address userAddress = new Address();

        userAddress = handlerAddress.addAdress(userMe.getId(), street, city, country);

        if (userAddress != null && user != null) {
            user.setAddress(userAddress);

            session.setAttribute("user", user);
            isChanged = true;

        } else {
            isChanged = false;
        }

        request.setAttribute("isChanged", isChanged);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/EditAccount.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/EditAccount.jsp");
      
        dispatcher.forward(request, response);

    }

}
