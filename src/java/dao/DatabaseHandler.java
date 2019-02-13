package dao;



import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.Constants;

/**
 * @author Mohamed Ramadan
 * @author Amer Shaker
 */
public class DatabaseHandler {

    private String ipAddress;
    private int portNumber;
    private String databaseName;
    private String userName;
    private String password;
    private boolean autoCommit;
     private Connection connection;
    private Statement statement;
//establish connection to db method 
    public  void establishConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
                        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", Constants.DATABASE_NAME, Constants.DATABASE_PASSWORD);

              connection.setAutoCommit(true);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (ClassNotFoundException ex) {
        }
        catch (SQLException ex) {
        }
          
    }
    // Connection and Statement for creating queries


    public DatabaseHandler(String databaseName, String userName, String password) {
        this( databaseName, userName, password, true);
    }

    public DatabaseHandler( String databaseName, String userName, String password, boolean autoCommit) {
        
        this.databaseName = databaseName;
        this.userName = userName;
        this.password = password;
        this.autoCommit = autoCommit;

        // Initialize database connection and create a Statement object
        establishConnection();
    }

  

    // Close the connection
    public void closeConnection() throws SQLException {
        statement.close();
        connection.close();
    }

    public void login(String emailAddress, String password) throws SQLException {
       
        
    }

    public boolean sinUp(String emailAddress, String userName, String password, String ipAddressString) throws SQLException {
       return false ;
    }

    public boolean deleteUser(String emailAddress) throws SQLException {
       return false ;
    }

   

    public void getAllUsers() throws SQLException {
       
    }
    public void getAllProduct() throws SQLException {
       
    }

    boolean updateColumn(String emailAddress, String columnName, String columnValue) throws SQLException {
       return false ;
    }

    boolean updateColumn(String emailAddress, String columnName, int columnValue) throws SQLException {
        return false ;
    }
    

}
