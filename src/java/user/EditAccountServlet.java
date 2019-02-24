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
@WebServlet(name = "EditAccountServlet", urlPatterns = {"/EditAccount"})
@MultipartConfig 
public class EditAccountServlet extends HttpServlet {
    AddressDao handlerAddress = new AddressDao();
     UsersDao myDb=new UsersDao();

 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      User user =new User();
      HttpSession session = request.getSession(false);
      PrintWriter out=response.getWriter();
      
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setJob(request.getParameter("job"));
        user.setCreditlimits(Double.parseDouble(request.getParameter("creditlimits")));
        String dateBirthday=request.getParameter("birthday");
       
       
            user.setBirthday(dateBirthday);
       
        Part filePart = request.getPart("profileImage");
        InputStream picInputStream = filePart.getInputStream();
        User userMe=(User)session.getAttribute("user");
        user.setId(userMe.getId());
                System.out.println("user id in session "+userMe.getId());
            userMe= myDb.updateUserData(user,picInputStream);
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
          Address userAddress=new Address();
       userAddress = handlerAddress.addAdress(userMe.getId(), street, city, country);
       user.setAddress(userAddress);
       
	session.setAttribute("user",userMe);
        out.println(userMe.getFirstName());
        
        
    }

  


}
