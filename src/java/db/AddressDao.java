/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.Address;
import beans.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.Constants;

/**
 *
 * @author Nesma
 */
public class AddressDao implements DbInterface {

    Connection connection;
    HandlerConnection handlerConnection;

    public AddressDao() {
        handlerConnection = new HandlerConnection();
        connection = handlerConnection.establishConnection();
    }

    public Address addAdress(int id, String street, String city, String country) {
        Address address = new Address();
        PreparedStatement pst;
        try {
            pst = connection.prepareStatement("insert into " + Constants.ADDRESSES_TABLE_NAME
                    + "("
                    + Constants.COLUMN_ADDRESS_ID + " , "
                    + Constants.COLUMN_ADDRESSES_USER_ID + " , "
                    + Constants.COLUMN_ADDRESSES_STREET + " , "
                    + Constants.COLUMN_ADDRESSES_CITY + " , "
                    + Constants.COLUMN_ADDRESSES_COUNTRY
                      + ") values(?,?,?,?,?)");

            pst.setLong(1, getSequence(Constants.ADDRESSES_SEQUENCES));
            pst.setInt(2, id);
            pst.setString(3, street);
            pst.setString(4, city);
            pst.setString(5, country);
            int check = pst.executeUpdate();
            System.out.println("id  = " + id);
            connection.commit();
            if (check > 0) {
                address.setCity(city);
                address.setCountry(country);
                address.setStreet(street);
                return address;

            }

        } catch (SQLException ex) {
            Logger.getLogger(AddressDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return address;
    }

    public Address getAddress(int id) {

        Address userAddress = null;
        try {
            ResultSet rs;
            PreparedStatement ps = connection.prepareStatement("select * from " + Constants.ADDRESSES_TABLE_NAME
                    + " where " + Constants.COLUMN_USER_ID + " =? ");
            ps.setInt(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {

                userAddress = new Address();
                userAddress.setStreet(rs.getString(Constants.COLUMN_ADDRESSES_STREET));
                userAddress.setCity(rs.getString(Constants.COLUMN_ADDRESSES_CITY));

                userAddress.setCountry(rs.getString(Constants.COLUMN_ADDRESSES_COUNTRY));
                return userAddress;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userAddress;
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

}
