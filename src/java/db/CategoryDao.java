/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.ProductCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utility.Constants;

/**
 *
 * @author A7med
 */
public class CategoryDao implements DbInterface{

    public CategoryDao() {
         handlerConnection = new HandlerConnection();
        connection = handlerConnection.establishConnection();
    }
    
    
    Connection connection;
    HandlerConnection handlerConnection;

    @Override
    public long getSequence(String sequenceName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int addCategory(String categoryName) {
        PreparedStatement pst;
        try {
            // Step 1: Check if the same product name already exist
            pst = connection.prepareStatement("SELECT * FROM " + Constants.CATEGORY_TABLE_NAME + " WHERE LOWER(?) = LOWER(?)");
            pst.setString(1, Constants.COLUMN_CATEGORY_NAME);
            pst.setString(2, categoryName);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return Constants.ERROR_ALREADY_EXIST;
            } else {
                // Step 2: Add product
                pst = connection.prepareStatement("INSERT INTO "
                        + Constants.CATEGORY_TABLE_NAME + " (" + Constants.COLUMN_CATEGORY_NAME + ") VALUES (?)");
                pst.setString(1, categoryName);

                int i = pst.executeUpdate();
                if (i != 0) {
                    return Constants.ERROR_SUCCESS;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Constants.ERROR_FAILED;
        }
        return Constants.ERROR_FAILED;
    }
    
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
        }
        return productCategotyList;
    }
    
}
