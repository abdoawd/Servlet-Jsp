/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.Product;
import beans.ProductCategory;
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
 * @author FAMA
 */
public class InterestsDao {

    Connection connection;
    HandlerConnection handlerConnection;

    public InterestsDao() {
        handlerConnection = new HandlerConnection();
        connection = handlerConnection.establishConnection();
    }

    public boolean InsertInerests(int user_id, String category_id) {

        PreparedStatement pst = null;
        int added;
        try {
            pst = connection.prepareStatement("insert into " + Constants.INTERESTS_TABLE_NAME
                    + "("
                    + Constants.COLUMN_INTERESTS_USER_ID + " , "
                    + Constants.COLUMN_INTERESTS_CATEGORY_ID + "  "
                    + ") values(?,?)");

            pst.setInt(1, user_id);
            pst.setInt(2, Integer.parseInt(category_id));
            added = pst.executeUpdate();

            if (added > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterestsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterestsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    public List<ProductCategory> getAllUnSelectedCategory(int userId) {
        List<ProductCategory> categoryList = new ArrayList<>();
        PreparedStatement ps = null;

        try {
            ResultSet rs = null;
            ps = connection.prepareStatement("select * from " + Constants.CATEGORY_TABLE_NAME + " where "
                    + Constants.COLUMN_CATEGORY_ID
                    + " not in(select " + Constants.COLUMN_CATEGORY_ID + " from " + Constants.INTERESTS_TABLE_NAME + " where user_id = ?) ");
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    categoryList.add(new ProductCategory(rs.getInt(1), rs.getString(2)));
                }
            }
            return categoryList;
        } catch (SQLException ex) {
            Logger.getLogger(InterestsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();

            } catch (SQLException ex) {
                Logger.getLogger(InterestsDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return categoryList;
    }

    public List<ProductCategory> getAllSelectedCategory(int userId) {
        List<ProductCategory> categoryList = new ArrayList<>();
        PreparedStatement ps = null;

        try {
            ResultSet rs = null;
            ps = connection.prepareStatement("select * from " + Constants.CATEGORY_TABLE_NAME + " where "
                    + Constants.COLUMN_CATEGORY_ID
                    + " in(select " + Constants.COLUMN_CATEGORY_ID + " from " + Constants.INTERESTS_TABLE_NAME + " where user_id = ?) ");
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    categoryList.add(new ProductCategory(rs.getInt(1), rs.getString(2)));
                }
            }
            return categoryList;
        } catch (SQLException ex) {
            Logger.getLogger(InterestsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();

            } catch (SQLException ex) {
                Logger.getLogger(InterestsDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return categoryList;
    }

    public boolean removeInterest(int userId, String categoryId) {

        PreparedStatement pst = null;
        int removed;
        try {
            pst = connection.prepareStatement("delete from " + Constants.INTERESTS_TABLE_NAME
                    + " where "
                    + Constants.COLUMN_INTERESTS_USER_ID + " = ? and "
                    + Constants.COLUMN_INTERESTS_CATEGORY_ID + " =? ");

            pst.setInt(1, userId);
            pst.setInt(2, Integer.parseInt(categoryId));
            removed = pst.executeUpdate();

            if (removed > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterestsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterestsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
