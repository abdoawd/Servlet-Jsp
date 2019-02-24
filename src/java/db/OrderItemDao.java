/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.Order;
import beans.OrderItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utility.Constants;

/**
 *
 * @author Abdelrahman
 */
public class OrderItemDao {

    Connection connection;
    HandlerConnection handlerConnection;

    public OrderItemDao() {
        handlerConnection = new HandlerConnection();
        connection = handlerConnection.establishConnection();
    }

    public boolean addOrder(OrderItem item) {
        PreparedStatement pst;
        boolean queryStatus = false;
        if(item==null)
            System.out.println("item is null ");
            
        try {
            pst = connection.prepareStatement("insert into " + Constants.ORDER_ITEM_TABLE_NAME
                    + " ( "
                    + Constants.COLUMN_ORDER_ITEM_ORDER_NUMBER + " , "
                    + Constants.COLUMN_ORDER_ITEM_ORDER_QUANTITY + " , "
                    + Constants.COULMN_ORDER_ITEM_PRODUCT_ID + " ) "
                    + "values (?,?,?)");
            pst.setInt(1, item.getOrderNumber());
            pst.setInt(2, item.getQuantity());
            pst.setInt(3, item.getProductId());
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
}
