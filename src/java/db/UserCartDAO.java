/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.Product;
import beans.UserShoppingCart;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import utility.Constants;

/**
 *
 * @author abdullah
 */
public class UserCartDAO {

    Connection connection;
    HandlerConnection handlerConnection;

    public UserCartDAO() {

        handlerConnection = new HandlerConnection();
        connection = handlerConnection.establishConnection();
    }

    public void addToCart(int userId, int productId, int quantity) {

        PreparedStatement pst;

        try {
            pst = connection.prepareStatement("insert into " + Constants.SHOPPING_CART_TABLE_NAME
                    + "( "
                    + Constants.COLUMN_USER_ID + ","
                    + Constants.COLUMN_PRODUCT_ID + ","
                    + Constants.COLUMN_PRODUCT_QUANTITY
                    + ")"
                    + "values (?,?,?)");
            pst.setInt(1, userId);
            pst.setInt(2, productId);
            pst.setInt(3, quantity);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserCartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteItemFromCart(int userId, int productId) {
        PreparedStatement pst;
        try {
            pst = connection.prepareStatement("Delete from " + Constants.SHOPPING_CART_TABLE_NAME
                    + " Where user_id =? and product_id =?");
            pst.setInt(1, userId);
            pst.setInt(2, productId);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserCartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean ClearUserCart(int userId) {
        PreparedStatement pst;
        boolean isCleared = false;
        try {
            pst = connection.prepareStatement("Delete from " + Constants.SHOPPING_CART_TABLE_NAME
                    + " Where user_id =? ");
            pst.setInt(1, userId);
            int rowsEffected = pst.executeUpdate();
            if (rowsEffected > 0) {
                isCleared = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return isCleared;
    }

    public void updateToCartQuery(int userId, int productId, int quantity) {
        PreparedStatement pst;
        try {
            pst = connection.prepareStatement("update " + Constants.SHOPPING_CART_TABLE_NAME
                    + " SET  "
                    + Constants.COLUMN_PRODUCT_QUANTITY
                    + " =? Where " + Constants.COLUMN_USER_ID + "  =? and "
                    + Constants.COLUMN_PRODUCT_ID + "  =? ");

            pst.setInt(1, quantity);
            pst.setInt(2, userId);
            pst.setInt(3, productId);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserCartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<UserShoppingCart> getUserCart(int userId) {
        List<UserShoppingCart> list = new ArrayList<UserShoppingCart>();
        UserShoppingCart userShoppingCart = null;
        try {
            PreparedStatement ps = connection.prepareStatement("select"
                    + " MY_PRODUCT.PRODUCT_ID,"
                    + "MY_PRODUCT.QUANTITY,"
                    + "CART.QUANTITY,"
                    + "MY_PRODUCT.IMAGE,"
                    + "MY_PRODUCT.PRICE,"
                    + "MY_PRODUCT.PRODUCT_NAME "
                    + "from STOREUSERS.PRODUCT MY_PRODUCT,STOREUSERS.SHOPPING_CART CART where user_id = " + userId + " and MY_PRODUCT.PRODUCT_ID = CART.product_id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userShoppingCart = new UserShoppingCart();
                InputStream stream = rs.getBinaryStream(Constants.COLUMN_PRODUCT_IMAGE);
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                int a1 = stream.read();
                while (a1 >= 0) {
                    output.write((char) a1);
                    a1 = stream.read();
                }
                byte[] encodeBase64 = Base64.encodeBase64(output.toByteArray());
                String base64Encoded = new String(encodeBase64, "UTF-8");
                output.close();
                userShoppingCart.setProductId(rs.getString(1));
                userShoppingCart.setProductQuantity(rs.getString(2));
                userShoppingCart.setUserCartProductQuantity(rs.getString(3));
                userShoppingCart.setStringImage(base64Encoded);
                userShoppingCart.setProductPrice(rs.getInt(Constants.COLUMN_PRODUCT_PRICE));
                userShoppingCart.setProductName(rs.getString(Constants.COLUMN_PRODUCT_NAME));
                list.add(userShoppingCart);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
        }
        return list;
    }

    public List<UserShoppingCart> getUserCheckoutCart(int userId) {
        List<UserShoppingCart> list = new ArrayList<UserShoppingCart>();
        UserShoppingCart userShoppingCart = null;
        try {
            PreparedStatement ps = connection.prepareStatement("select MY_PRODUCT.PRODUCT_ID,"
                    + "CART.QUANTITY,"
                    + "MY_PRODUCT.PRICE,MY_PRODUCT.PRODUCT_NAME from STOREUSERS.PRODUCT MY_PRODUCT,"
                    + "STOREUSERS.SHOPPING_CART CART where user_id = " + userId + " and MY_PRODUCT.PRODUCT_ID = CART.product_id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userShoppingCart = new UserShoppingCart();
                userShoppingCart.setProductId(rs.getString(1));
                userShoppingCart.setUserCartProductQuantity(rs.getString(2));
                userShoppingCart.setProductPrice(rs.getInt(3));
                userShoppingCart.setProductName(rs.getString(4));
                list.add(userShoppingCart);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean isProductInCart(int userId, int productId) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            String selectStatment = "select * from " + Constants.SHOPPING_CART_TABLE_NAME
                    + " where " + Constants.COLUMN_USER_ID
                    + " = " + userId + " and "
                    + Constants.COLUMN_PRODUCT_ID
                    + " = " + productId;
            preparedStatement = connection.prepareStatement(selectStatment);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("result set  is Product  in cart " + resultSet);
                return true;
            }

        } catch (Exception e) {
        }
        System.out.println("result set  is  Product not in cart ");

        return false;
    }
}
