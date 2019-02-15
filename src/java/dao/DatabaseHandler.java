package dao;

import beans.Product;
import beans.ProductCategory;
import beans.User;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
                    + Constants.COLUMN_USER_JOP + ")"
                    + "values (?,?,?,?,?,?,?)");
            pst.setInt(1, (int) getSequence(Constants.USERSES_SEQUENCES));
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

    public List<Product> getInterstsProduct(int userId) {
        List<Product> list = new ArrayList<>();
        Product product = new Product();
        try {
            ResultSet rs = null;
            PreparedStatement ps = connection.prepareStatement("select * from " + Constants.INTERESTS_TABLE_NAME
                    + " where " + Constants.COLUMN_INTERESTS_USER_ID + " =?");
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                product.setCategoryId(rs.getString(Constants.COLUMN_PRODUCT_CATEGORY_ID));
                product.setDescription(rs.getString(Constants.COLUMN_PRODUCT_DESCRIPTION));
                product.setDiscount(rs.getInt(Constants.COLUMN_PRODUCT_DISCOUNT));
                product.setId(rs.getString(Constants.COLUMN_PRODUCT_ID));
                product.setName(rs.getString(Constants.COLUMN_PRODUCT_NAME));
                product.setPrice(rs.getInt(Constants.COLUMN_PRODUCT_PRICE));
                product.setQuantity(rs.getString(Constants.COLUMN_PRODUCT_QUANTITY));
                list.add(product);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Product> getProductsByCategoryId(int categoryId) {
        List<Product> list = new ArrayList<Product>();
        Product  product=new Product();
        try {
            ResultSet rs = null;
            PreparedStatement ps = connection.prepareStatement("select * from " + Constants.PRODUCT_TABLE_NAME
                    + " where " + Constants.COLUMN_PRODUCT_CATEGORY_ID + " =?");
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();
            while (rs.next()) {
                   product.setCategoryId(rs.getString(Constants.COLUMN_PRODUCT_CATEGORY_ID));
                product.setDescription(rs.getString(Constants.COLUMN_PRODUCT_DESCRIPTION));
                product.setDiscount(rs.getInt(Constants.COLUMN_PRODUCT_DISCOUNT));
                product.setId(rs.getString(Constants.COLUMN_PRODUCT_ID));
                product.setName(rs.getString(Constants.COLUMN_PRODUCT_NAME));
                product.setPrice(rs.getInt(Constants.COLUMN_PRODUCT_PRICE));
                product.setQuantity(rs.getString(Constants.COLUMN_PRODUCT_QUANTITY));
                list.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    // Call this method to add new product to DB --> Return true if succeed
    public boolean addProduct(String productName, int productQuantity, long productPrice,
            long productDiscount, String productCategory, InputStream picInputStream, String productDescription) {
        PreparedStatement pst;
        boolean isScuccess = false;
        try {

            pst = connection.prepareStatement("insert into " + Constants.PRODUCT_TABLE_NAME
                    + "( "
                    + Constants.COLUMN_PRODUCT_ID + ","
                    + Constants.COLUMN_PRODUCT_NAME + ","
                    + Constants.COLUMN_PRODUCT_DESCRIPTION + ","
                    + Constants.COLUMN_PRODUCT_PRICE + ","
                    + Constants.COLUMN_PRODUCT_QUANTITY + ","
                    + Constants.COLUMN_PRODUCT_IMAGE + ","
                    + Constants.COLUMN_PRODUCT_CATEGORY_ID + ","
                    + Constants.COLUMN_PRODUCT_DISCOUNT + ")"
                    + "values (?,?,?,?,?,?,?,?)");
            pst.setInt(1, (int) getSequence(Constants.PRODUCT_SEQUENCES));
            pst.setString(2, productName);
            pst.setString(3, productDescription);
            pst.setLong(4, productPrice);
            pst.setInt(5, productQuantity);
            pst.setBlob(6, picInputStream);
            pst.setString(7, productCategory);
            pst.setLong(8, productDiscount);

            int i = pst.executeUpdate();
            if (i != 0) {
                isScuccess = true;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
            ex.printStackTrace();
            System.out.println("status = " + isScuccess);
        }
        return isScuccess;
    }

    // Get statistics for admin dashboard
    public int getTablesCounter(String tableName) {
        PreparedStatement pst;
        int counter = 0;
        try {
            pst = connection.prepareStatement("SELECT COUNT(*) FROM " + tableName);
//            pst.setString(1, tableName);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                counter = rs.getInt(1);
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return counter;
    }

    // Get caegories id/ name -> Return list of Categories
    public List<ProductCategory> getProductCategories() {
        PreparedStatement pst;
        List<ProductCategory> productCategotyList = new ArrayList<>();
        try {
            pst = connection.prepareStatement("SELECT * FROM " + Constants.CATEGORY_TABLE_NAME);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ProductCategory pc = new ProductCategory(rs.getInt(1), rs.getString(2));
                productCategotyList.add(pc);
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productCategotyList;
    }

    // Get list of user bean -> Return list of user
    public List<User> getUsersList() {
        PreparedStatement pst;
        List<User> usersList = new ArrayList<>();
        try {
            pst = connection.prepareStatement("SELECT * FROM " + Constants.USER_TABLE_NAME);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString(Constants.COLUMN_USER_ID),
                        rs.getString(Constants.COLUMN_USER_FIRST_NAME),
                        rs.getString(Constants.COLUMN_USER_LAST_NAME),
                        rs.getString(Constants.COLUMN_USER_EMAIL),
                        rs.getString(Constants.COLUMN_USER_JOP),
                        rs.getDouble(Constants.COLUMN_USER_CREDIT_LIMIT));
                usersList.add(user);
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usersList;
    }
}