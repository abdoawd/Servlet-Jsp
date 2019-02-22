/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;

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
}
