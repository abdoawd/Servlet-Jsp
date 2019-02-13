package dao;

import beans.User;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.Constants;

public class DatabaseHandler {

    private String ipAddress;
    private int portNumber;
    private String databaseName;
    private String userName;
    private String password;
    private boolean autoCommit;
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

    // Close the connection
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

    public User login(String emailAddress, String password) {
        try {
            ResultSet rs = null;
            PreparedStatement s = connection.prepareStatement("select * from " + Constants.USER_TABLE_NAME
                    + " where ");

            rs = s.executeQuery();
            while (rs.next()) {

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean sinUp(String emailAddress, String userName, String password, String ipAddressString) throws SQLException {
        return false;
    }

    public boolean deleteUser(String emailAddress) throws SQLException {
        return false;
    }

    public void getAllUsers() throws SQLException {

    }

    public void getAllProduct() throws SQLException {

    }

    boolean updateColumn(String emailAddress, String columnName, String columnValue) throws SQLException {
        return false;
    }

    boolean updateColumn(String emailAddress, String columnName, int columnValue) throws SQLException {
        return false;
    }

}
