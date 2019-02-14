package dao;

import beans.User;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.Constants;

public class DatabaseHandler {


    private Connection connection;
    private Statement statement;

    // Connection and Statement for creating queries
    public DatabaseHandler() {
        connection = establishConnection();

    }

    //establish connection to db method 
    public Connection establishConnection() {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", Constants.DATABASE_NAME, Constants.DATABASE_PASSWORD);

            connection.setAutoCommit(true);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        }
        return connection;

    }

    public void closeConnection() throws SQLException {
        statement.close();
        connection.close();
    }

    public int testConnection() {
        int i = 0;
        try {
            ResultSet rs = null;
            PreparedStatement s = connection.prepareStatement("select * from " + Constants.USER_TABLE_NAME);
            rs = s.executeQuery();
            while (rs.next()) {
                i = 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return i;
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
                user = new User(rs.getString(2), rs.getString(3));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public boolean addUser(String firstName, String lastName, String email, String passwrd, String jop) {
        PreparedStatement pst;
        boolean isScuccess = false;

        try {
            pst = connection.prepareStatement("insert into " + Constants.USER_TABLE_NAME
                    + "( "
                    + Constants.COLUMN_USER_ID + ","
                    + Constants.COLUMN_USER_FIRST_NAME + ","
                    + Constants.COLUMN_USER_LAST_NAME + ","
                    + Constants.COLUMN_USER_ROLE + ","
                    + Constants.COLUMN_USER_EMAIL + ","
                    + Constants.COLUMN_USER_PASSWORD + ","
                    + Constants.COLUMN_USER_JOP+ ")"
                    + "values (?,?,?,?,?,?,?)");
            pst.setInt(1, (int) getUserSequence());
            pst.setString(2, firstName);
            pst.setString(3, lastName);
            pst.setString(4, "user");
            pst.setString(5, email);
            pst.setString(6, passwrd);
            pst.setString(7, jop);
//            pst.setInt(9, 400);

            int i = pst.executeUpdate();
            if (i != 0) {
                isScuccess = true;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
            ex.printStackTrace();
        }
        return isScuccess;
    }

    public long getUserSequence() {

        PreparedStatement pst;
        long myId = 0;
        try {
            pst = connection.prepareStatement("select "+Constants.USERSES_SEQUENCES+".NEXTVAL from dual");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                myId = rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("my id = " + myId);

        return myId;
    }

    public boolean deleteUser(String emailAddress) throws SQLException {
        return false;
    }

    public void getAllUsers() throws SQLException {

    }

    public void getAllProducts() throws SQLException {

    }

    public boolean updateColumn(String emailAddress, String columnName, String columnValue) throws SQLException {
        return false;
    }

    public boolean updateColumn(String emailAddress, String columnName, int columnValue) throws SQLException {
        return false;
    }
    
    
    

}
