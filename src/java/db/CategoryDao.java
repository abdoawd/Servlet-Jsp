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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utility.Constants;

/**
 *
 * @author A7med
 */
public class CategoryDao implements DbInterface {


    Connection connection;
    HandlerConnection handlerConnection;

    public CategoryDao() {
        handlerConnection = new HandlerConnection();
        connection = handlerConnection.establishConnection();
    }


    @Override
    public long getSequence(String sequenceName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int addCategory(String categoryName) {
        PreparedStatement pst;
        try {
            // Step 1: Check if the same product name already exist
            pst = connection.prepareStatement("SELECT * FROM " + Constants.CATEGORY_TABLE_NAME
                    + " WHERE LOWER(" + Constants.COLUMN_CATEGORY_NAME + ") = LOWER(?)");
            pst.setString(1, categoryName);
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


    public int editCategory(int categoryId, String categoryName) {
        PreparedStatement pst;
        try {
            pst = connection.prepareStatement("UPDATE " + Constants.CATEGORY_TABLE_NAME + " SET "
                    + Constants.COLUMN_CATEGORY_NAME + " = ? WHERE " + Constants.COLUMN_CATEGORY_ID + " = ?");
            pst.setString(1, categoryName);
            pst.setInt(2, categoryId);

            int i = pst.executeUpdate();
            if (i != 0) {
                return Constants.ERROR_SUCCESS;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return Constants.ERROR_FAILED;
        }
        return Constants.ERROR_FAILED;
    }

    public int deleteCategory(int categoryIdInput) {
        if (categoryIdInput == Constants.UNDEFINED_CATEGORY_ID){
            return Constants.ERROR_FAILED;
        }
        
        // 1. Check if the "Undefined" category exist
        PreparedStatement pst;
        try {
            pst = connection.prepareStatement("SELECT * FROM " + Constants.CATEGORY_TABLE_NAME + " WHERE "
                    + Constants.COLUMN_CATEGORY_NAME + " = ?");
            pst.setString(1, Constants.UNDEFINED_CATEGORY);
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) { // Create Undefined category and assign id calue to 0
                pst = connection.prepareStatement("INSERT INTO " + Constants.CATEGORY_TABLE_NAME
                        + " (" + Constants.COLUMN_CATEGORY_ID + ", " + Constants.COLUMN_CATEGORY_NAME + ") VALUES (?, ?)");
                pst.setInt(1, Constants.UNDEFINED_CATEGORY_ID);
                pst.setString(2, Constants.UNDEFINED_CATEGORY);
                int i = pst.executeUpdate();
                if (i != 0) {
                    System.out.println("Error 1");
                    return Constants.ERROR_FAILED;
                }

                pst = connection.prepareStatement("UPDATE " + Constants.CATEGORY_TABLE_NAME
                        + " SET " + Constants.COLUMN_CATEGORY_ID + " = ? WHERE " + Constants.COLUMN_CATEGORY_NAME + " = ?");
                pst.setInt(1, Constants.UNDEFINED_CATEGORY_ID);
                pst.setString(2, Constants.UNDEFINED_CATEGORY);
                int j = pst.executeUpdate();
                if (j != 0) {
                    System.out.println("Error 1");
                    return Constants.ERROR_FAILED;
                }
            }

            // 2. if has products, link them to undefined category
            Statement s = connection.createStatement();
            String s1 = "UPDATE " + Constants.PRODUCT_TABLE_NAME + " SET " + Constants.COLUMN_CATEGORY_ID + " = "
                    + Constants.UNDEFINED_CATEGORY_ID + " WHERE " + Constants.COLUMN_CATEGORY_ID + " = " + categoryIdInput;

            // 3. Delete interests with the same product id
            String s2 = "DELETE FROM " + Constants.INTERESTS_TABLE_NAME + " WHERE "
                    + Constants.COLUMN_CATEGORY_ID + " = " + categoryIdInput;

            // 4. delete the category
            String s3 = "DELETE FROM " + Constants.CATEGORY_TABLE_NAME + " WHERE "
                    + Constants.COLUMN_CATEGORY_ID + " = " + categoryIdInput;

            s.addBatch(s1);
            s.addBatch(s2);
            s.addBatch(s3);
            int[] k = s.executeBatch();
            if (k.length == 3) {
                return Constants.ERROR_SUCCESS;
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Constants.ERROR_FAILED;
        }
        return Constants.ERROR_FAILED;
    }
}
