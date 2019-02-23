package db;

import beans.Product;
import beans.ProductCategory;
import beans.User;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.Constants;

public class UsersDao implements DbInterface {

    Connection connection;
    HandlerConnection handlerConnection;

    public UsersDao() {
        handlerConnection = new HandlerConnection();
        connection = handlerConnection.establishConnection();
    }

    public User login(String email, String password) {
        User user = null;
        try {
            ResultSet rs = null;
            PreparedStatement ps = connection.prepareStatement("select * from " + Constants.USER_TABLE_NAME
                    + " where " + Constants.COLUMN_USER_EMAIL + " =? and " + Constants.COLUMN_USER_PASSWORD + " =?");
            ps.setString(1, email);
            ps.setString(2, password);

            rs = ps.executeQuery();
            while (rs.next()) {
//                user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5),
//                        rs.getString(6), rs.getString(7), rs.getDate(8), rs.getDouble(9));
                /*    public User(String id, String firstName, String lastName, String email,
            String password, String job,String birthday, double creditlimits)*/
                user = new User();
                user.setId(rs.getInt(Constants.COLUMN_USER_ID));
                user.setFirstName(rs.getString(Constants.COLUMN_USER_FIRST_NAME));
                user.setLastName(rs.getString(Constants.COLUMN_USER_LAST_NAME));
                user.setRole(rs.getString(Constants.COLUMN_USER_ROLE));
                user.setEmail(rs.getString(Constants.COLUMN_USER_EMAIL));
                user.setPassword(rs.getString(Constants.COLUMN_USER_PASSWORD));
                user.setJob(rs.getString(Constants.COLUMN_USER_JOP));
                user.setCreditlimits(rs.getDouble(Constants.COLUMN_USER_CREDIT_LIMIT));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();    
        }
        return user;
    }

    public User addUser(String firstName, String lastName, String email, String passwrd, String jop) {
        PreparedStatement pst;

        try {
           int id= (int) getSequence(Constants.USERSES_SEQUENCES);
           //   Date date=new SimpleDateFormat("yyyy-mm-dd").parse(birthday);  
            pst = connection.prepareStatement("insert into " + Constants.USER_TABLE_NAME
                    + "( "
                    + Constants.COLUMN_USER_ID + ","
                    + Constants.COLUMN_USER_FIRST_NAME + ","
                    + Constants.COLUMN_USER_LAST_NAME + ","
                    + Constants.COLUMN_USER_ROLE + ","
                    + Constants.COLUMN_USER_EMAIL + ","
                    + Constants.COLUMN_USER_PASSWORD + ","
                    + Constants.COLUMN_USER_JOP +
                    ")"
                    + " values (?,?,?,?,?,?,?)");
            pst.setInt(1,id );
            pst.setString(2, firstName);
            pst.setString(3, lastName);
            pst.setString(4, "user");
            pst.setString(5, email);
            pst.setString(6, passwrd);
            pst.setString(7, jop);
          //  pst.setDate(8, new java.sql.Date( date.getTime()));
          
            int i = pst.executeUpdate();
          
            if (i != 0) {
                System.out.print("success");
                System.out.println("id in add user "+id);
               User user=new User(id,firstName,lastName,"user",email,passwrd,jop);
               return user;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public boolean isEmailExist(String userEmail) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from " + Constants.USER_TABLE_NAME
                    + " where " + Constants.COLUMN_USER_EMAIL + " =?");
            ps.setString(1, userEmail);
            if (ps.execute()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public long getSequence(String sequenceName) {
        PreparedStatement pst;
        long myId = 0;
        try {
            pst = connection.prepareStatement("select " + sequenceName + ".NEXTVAL from dual");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                myId = rs.getLong(1);
            }
        } catch (SQLException ex) {
        }
        System.out.println("my id = " + myId);

        return myId;
    }
    // Get caegories id/ name -> Return list of Categories

    public List<User> getUsersList() {
        PreparedStatement pst;
        List<User> usersList = new ArrayList<>();
        try {
            pst = connection.prepareStatement("SELECT * FROM " + Constants.USER_TABLE_NAME
                    + " ORDER BY " + Constants.COLUMN_USER_ID + " ASC");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(Constants.COLUMN_USER_ID));
                user.setFirstName(rs.getString(Constants.COLUMN_USER_FIRST_NAME));

                user.setLastName(rs.getString(Constants.COLUMN_USER_LAST_NAME));
                user.setRole(rs.getString(Constants.COLUMN_USER_ROLE));
                user.setEmail(rs.getString(Constants.COLUMN_USER_EMAIL));
                user.setPassword(rs.getString(Constants.COLUMN_USER_PASSWORD));
                user.setJob(rs.getString(Constants.COLUMN_USER_JOP));
                user.setCreditlimits(rs.getDouble(Constants.COLUMN_USER_CREDIT_LIMIT));
                usersList.add(user);
            }
            pst.close();
        } catch (SQLException ex) {
        }
        return usersList;
    }

    public User getUser(int userId) {
        PreparedStatement pst;
        User user = null;
        try {
            pst = connection.prepareStatement("SELECT E.* , A.* FROM " + Constants.USER_TABLE_NAME + " E, "
                    + Constants.ADDRESSES_TABLE_NAME + " A WHERE E." + Constants.COLUMN_USER_ID + " = ?");
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(Constants.COLUMN_USER_ID));
                user.setFirstName(rs.getString(Constants.COLUMN_USER_FIRST_NAME));
                user.setLastName(rs.getString(Constants.COLUMN_USER_LAST_NAME));
                user.setEmail(rs.getString(Constants.COLUMN_USER_EMAIL));
                user.setPassword(rs.getString(Constants.COLUMN_USER_PASSWORD));
                user.setJob(rs.getString(Constants.COLUMN_USER_JOP));
                user.setBirthday(rs.getString(Constants.COLUMN_USER_BIRTHDAY));
                user.setCreditlimits(rs.getDouble(Constants.COLUMN_USER_CREDIT_LIMIT));
                user.setStreet(rs.getString(Constants.COLUMN_ADDRESSES_STREET));
                user.setCity(rs.getString(Constants.COLUMN_ADDRESSES_CITY));
                user.setCountry(rs.getString(Constants.COLUMN_ADDRESSES_COUNTRY));
            }
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;

    }

    public User updateUserData(User user, InputStream picInputStream) {

        PreparedStatement pst;
        try {
            pst = connection.prepareStatement("update " + Constants.USER_TABLE_NAME
                    + " set "
                    + Constants.COLUMN_USER_FIRST_NAME + "= ? ,"

                    + Constants.COLUMN_USER_LAST_NAME + "= ? ,"
                    + Constants.COLUMN_USER_EMAIL + "= ? ,"
                    + Constants.COLUMN_USER_PASSWORD + "= ? ,"
                    + Constants.COLUMN_USER_IMAGE + " = ? ,"
                    + Constants.COLUMN_USER_BIRTHDAY + " = ? ,"
                    + Constants.COLUMN_USER_JOP + "= ? where "

                    + Constants.COLUMN_USER_ID + "= ?" 
                           );
            pst.setString(1, user.getFirstName());
            pst.setString(2, user.getLastName());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getPassword());
            pst.setBlob(5,picInputStream);
          //  Date date=new SimpleDateFormat("yyyy-mm-dd").parse(user.getBirthday());
            pst.setString(6, user.getBirthday());
            pst.setString(7, user.getJob());
              pst.setInt(8,user.getId());  
              System.out.println(user.getId()+user.getFirstName());
            int i = pst.executeUpdate();
            if (i != 0) {
                return user;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void updateUserCreditLimit(double creditLimit, int userId) {

        PreparedStatement pst;

        try {
            pst = connection.prepareStatement("update " + Constants.USER_TABLE_NAME
                    + " SET  "
                    + Constants.COLUMN_USER_CREDIT_LIMIT
                    + " =? Where " + Constants.COLUMN_USER_ID + "  =?");
            pst.setDouble(1, creditLimit);
            pst.setInt(2, userId);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserCartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
