/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.Constants;

/**
 *
 * @author Abdelrahman
 */
public class OrderDao {

    Connection connection;
    HandlerConnection handlerConnection;

    public OrderDao() {
        handlerConnection = new HandlerConnection();
        connection = handlerConnection.establishConnection();
    }

    public boolean addOrder(Order order) {
        PreparedStatement pst;
        boolean queryStatus = false;
        try {

            pst = connection.prepareStatement("insert into " + Constants.ORDER_TABLE_NAME
                    + "( "
                    + Constants.COLUMN_ORDER_USER_ID + ","
                    + Constants.COLUMN_ORDER_TIME + ","
                    + Constants.COLUMN_ORDER_STATUS + ","
                    + Constants.COLUMN_ORDER_TOTAL_AMOUNT + ")"
                    + "values (?,?,?,?)");
            pst.setInt(1, order.getUserId());
            pst.setString(2, order.getOrderTime());
            pst.setString(3, order.getStatus());
            pst.setInt(4, order.getTotalAmount());

            int i = pst.executeUpdate();
            if (i != 0) {
                queryStatus = true;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
            ex.printStackTrace();
        }
           
        return queryStatus;
    }

    public List<Order> getAllOrder(int userId) {
        PreparedStatement pst = null;
        List<Order> list = new ArrayList<>();
        Order order;
        try {
            switch (userId) {
                case 0:
                    pst = connection.prepareStatement("SELECT O.*, (U."
                            + Constants.COLUMN_USER_FIRST_NAME+" || ' ' ||  U."
                            + Constants.COLUMN_USER_LAST_NAME+") AS  USERNAME FROM "
                            + Constants.ORDER_TABLE_NAME+" O, "
                            + Constants.USER_TABLE_NAME+" U WHERE O."
                            + Constants.COLUMN_ORDER_USER_ID+" = U."
                            + Constants.COLUMN_USER_ID);
                    break;
                default:
                    pst = connection.prepareStatement("SELECT O.*, (U."
                            + Constants.COLUMN_USER_FIRST_NAME+" || ' ' ||  U."
                            + Constants.COLUMN_USER_LAST_NAME+") AS  USERNAME FROM "
                            + Constants.ORDER_TABLE_NAME+" O, "
                            + Constants.USER_TABLE_NAME+" U where O."
                            + Constants.COLUMN_ORDER_USER_ID + " = ? AND O."
                            + Constants.COLUMN_ORDER_USER_ID+" = U. "
                            + Constants.COLUMN_USER_ID);
                    pst.setInt(1, userId);
            }

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                order = new Order();

                order.setOrderNumber(rs.getInt(Constants.COLUMN_ORDER_NUMBER));
                order.setTotalAmount(rs.getInt(Constants.COLUMN_ORDER_TOTAL_AMOUNT));
                order.setUserId(rs.getInt(Constants.COLUMN_ORDER_USER_ID));
                order.setOrderTime(rs.getString(Constants.COLUMN_ORDER_TIME));
                order.setStatus(rs.getString(Constants.COLUMN_ORDER_STATUS));
                order.setUserName(rs.getString("USERNAME"));

                list.add(order);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
            ex.printStackTrace();
        }
          
        return list;
    }

    public int getOrderNumber(int userId, String ordeTime) {
        PreparedStatement pst;
        int orderNumber = 0;
        try {
            pst = connection.prepareStatement("select " + Constants.COLUMN_ORDER_NUMBER + " from  "
                    + Constants.ORDER_TABLE_NAME + " where "
                    + Constants.COLUMN_ORDER_USER_ID + " =? and " + Constants.COLUMN_ORDER_TIME + " =? ");
            pst.setInt(1, userId);
            pst.setString(2, ordeTime);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                orderNumber = rs.getInt(Constants.COLUMN_ORDER_NUMBER);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
            ex.printStackTrace();
        }
           
        return orderNumber;
    }
}
