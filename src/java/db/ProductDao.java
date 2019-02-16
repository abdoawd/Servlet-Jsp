package db;

import beans.Product;
import beans.ProductCategory;
import java.awt.Image;
import java.awt.Toolkit;
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
import utility.Constants;

public class ProductDao implements DbInterface {

    Connection connection;
    HandlerConnection handlerConnection;

    public ProductDao() {
        handlerConnection = new HandlerConnection();
        connection = handlerConnection.establishConnection();

    }
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
        }
        return counter;
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

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<Product>();
        Product product = null;
        try {
            PreparedStatement ps = connection.prepareStatement("select * from  product ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                product = new Product();
                InputStream stream = rs.getBinaryStream(Constants.COLUMN_PRODUCT_IMAGE);

                ByteArrayOutputStream output = new ByteArrayOutputStream();
                int a1 = stream.read();
                while (a1 >= 0) {
                    output.write((char) a1);
                    a1 = stream.read();
                }
                Image myImage = Toolkit.getDefaultToolkit().createImage(output.toByteArray());
                output.close();
                product.setCategoryId(rs.getString(Constants.COLUMN_PRODUCT_CATEGORY_ID));
                product.setDescription(rs.getString(Constants.COLUMN_PRODUCT_DESCRIPTION));
                product.setDiscount(rs.getInt(Constants.COLUMN_PRODUCT_DISCOUNT));
                product.setId(rs.getString(Constants.COLUMN_PRODUCT_ID));
                product.setName(rs.getString(Constants.COLUMN_PRODUCT_NAME));
                product.setPrice(rs.getInt(Constants.COLUMN_PRODUCT_PRICE));
                product.setQuantity(rs.getString(Constants.COLUMN_PRODUCT_QUANTITY));
                product.setImage(myImage);
                list.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Product> getProductByName(String name) {
           List<Product> list = new ArrayList<Product>();
        Product product = null;
        try {
            PreparedStatement ps = connection.prepareStatement("select * from  product where "
                    +Constants.COLUMN_PRODUCT_NAME+" like ? ");
            ps.setString(1, "%"+name+"%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                product = new Product();
                InputStream stream = rs.getBinaryStream(Constants.COLUMN_PRODUCT_IMAGE);

                ByteArrayOutputStream output = new ByteArrayOutputStream();
                int a1 = stream.read();
                while (a1 >= 0) {
                    output.write((char) a1);
                    a1 = stream.read();
                }
                Image myImage = Toolkit.getDefaultToolkit().createImage(output.toByteArray());
                output.close();
                product.setCategoryId(rs.getString(Constants.COLUMN_PRODUCT_CATEGORY_ID));
                product.setDescription(rs.getString(Constants.COLUMN_PRODUCT_DESCRIPTION));
                product.setDiscount(rs.getInt(Constants.COLUMN_PRODUCT_DISCOUNT));
                product.setId(rs.getString(Constants.COLUMN_PRODUCT_ID));
                product.setName(rs.getString(Constants.COLUMN_PRODUCT_NAME));
                product.setPrice(rs.getInt(Constants.COLUMN_PRODUCT_PRICE));
                product.setQuantity(rs.getString(Constants.COLUMN_PRODUCT_QUANTITY));
                product.setImage(myImage);
                list.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
        }
        return list;
    }
}
