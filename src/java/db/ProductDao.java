package db;

import beans.Product;

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
                product.setCategoryId(Integer.parseInt(rs.getString(Constants.COLUMN_PRODUCT_CATEGORY_ID)));
                product.setDescription(rs.getString(Constants.COLUMN_PRODUCT_DESCRIPTION));
                product.setDiscount(rs.getInt(Constants.COLUMN_PRODUCT_DISCOUNT));
                product.setId(Integer.parseInt(rs.getString(Constants.COLUMN_PRODUCT_ID)));
                product.setName(rs.getString(Constants.COLUMN_PRODUCT_NAME));
                product.setPrice(rs.getInt(Constants.COLUMN_PRODUCT_PRICE));
                product.setQuantity(Integer.parseInt(rs.getString(Constants.COLUMN_PRODUCT_QUANTITY)));
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

    /**
     * @param productId
     * @return list of products Accept 3 types of inputs > Constants.SELECT_ALL
     * -- To get all products > Constants.SELECT_ACTIVE -- To get active
     * products only which wasn't deleted > get specific product with id
     */
    public List<Product> getAllProducts(int productId) {
        List<Product> list = new ArrayList<Product>();
        Product product = null;
        try {
            PreparedStatement ps;
            switch (productId) {
                case Constants.SELECT_ALL:
                    ps = connection.prepareStatement("select P.*, (select " + Constants.COLUMN_CATEGORY_NAME
                            + " from " + Constants.CATEGORY_TABLE_NAME + " where " + Constants.COLUMN_CATEGORY_ID
                            + " = p." + Constants.COLUMN_CATEGORY_ID + ") as CATEGORY_NAME "
                            + "from " + Constants.PRODUCT_TABLE_NAME + " p");
                    break;
                case Constants.SELECT_ACTIVE:
                    ps = connection.prepareStatement("select P.*, (select " + Constants.COLUMN_CATEGORY_NAME
                            + " from " + Constants.CATEGORY_TABLE_NAME + " where " + Constants.COLUMN_CATEGORY_ID
                            + " = p." + Constants.COLUMN_CATEGORY_ID + ") as CATEGORY_NAME "
                            + "from " + Constants.PRODUCT_TABLE_NAME + " p where "
                            + Constants.COLUMN_PRODUCT_STATUS + " = ?");
                    ps.setInt(1, Constants.PRODUCT_ACTIVE);
                    break;
                default:
                    ps = connection.prepareStatement("select P.*, (select " + Constants.COLUMN_CATEGORY_NAME
                            + " from " + Constants.CATEGORY_TABLE_NAME + " where " + Constants.COLUMN_CATEGORY_ID
                            + " = p." + Constants.COLUMN_CATEGORY_ID + ") as CATEGORY_NAME "
                            + "from " + Constants.PRODUCT_TABLE_NAME + " p where "
                            + Constants.COLUMN_PRODUCT_ID + " = ?");
                    ps.setInt(1, productId);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("insid product");
                product = new Product();

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
                product.setCategoryId(Integer.parseInt(rs.getString(Constants.COLUMN_PRODUCT_CATEGORY_ID)));
                product.setDescription(rs.getString(Constants.COLUMN_PRODUCT_DESCRIPTION));
                product.setDiscount(rs.getInt(Constants.COLUMN_PRODUCT_DISCOUNT));
                product.setId(Integer.parseInt(rs.getString(Constants.COLUMN_PRODUCT_ID)));
                product.setName(rs.getString(Constants.COLUMN_PRODUCT_NAME));
                product.setPrice(rs.getInt(Constants.COLUMN_PRODUCT_PRICE));
                product.setQuantity(Integer.parseInt(rs.getString(Constants.COLUMN_PRODUCT_QUANTITY)));
                product.setCategoryName(rs.getString(Constants.COLUMN_CATEGORY_NAME));
                product.setStringImage(base64Encoded);
                list.add(product);
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /*
    public boolean deleteMethod(String id, String tableName) { //Used for product or Category
        PreparedStatement pst;
        boolean isScuccess = false;
        try {
            String columnName;
            switch (tableName) {
                case Constants.PRODUCT_TABLE_NAME:
                    columnName = Constants.COLUMN_PRODUCT_ID;
                    break;
                case Constants.CATEGORY_TABLE_NAME:
                    columnName = Constants.COLUMN_CATEGORY_ID;
                    break;
                default:
                    return isScuccess;
            }
            pst = connection.prepareStatement("DELETE FROM " + tableName + " WHERE " + columnName + " = " + id);

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
     */
    public boolean deleteProductTemporarily(String id) {
        PreparedStatement pst;
        boolean isScuccess = false;
        try {

            String columnName = Constants.COLUMN_PRODUCT_ID;
            pst = connection.prepareStatement("UPDATE " + Constants.PRODUCT_TABLE_NAME
                    + " SET " + Constants.COLUMN_PRODUCT_STATUS + " = '0' WHERE "
                    + Constants.COLUMN_PRODUCT_ID + " = " + id);
//            pst.setString(1, tableName);
//            pst.setString(2, columnName);
//            pst.setString(3, id);

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

    public List<Product> getProductByName(String name) {
        List<Product> list = new ArrayList<Product>();
        Product product = null;
        try {
            PreparedStatement ps = connection.prepareStatement("select * from  product where "
                    + Constants.COLUMN_PRODUCT_NAME + " like ? ");
            ps.setString(1, "%" + name + "%");
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
                byte[] encodeBase64 = Base64.encodeBase64(output.toByteArray());
                String base64Encoded = new String(encodeBase64, "UTF-8");
                output.close();
                product.setCategoryId(Integer.parseInt(rs.getString(Constants.COLUMN_PRODUCT_CATEGORY_ID)));
                product.setDescription(rs.getString(Constants.COLUMN_PRODUCT_DESCRIPTION));
                product.setDiscount(rs.getInt(Constants.COLUMN_PRODUCT_DISCOUNT));
                product.setId(Integer.parseInt(rs.getString(Constants.COLUMN_PRODUCT_ID)));
                product.setName(rs.getString(Constants.COLUMN_PRODUCT_NAME));
                product.setPrice(rs.getInt(Constants.COLUMN_PRODUCT_PRICE));
                product.setQuantity(Integer.parseInt(rs.getString(Constants.COLUMN_PRODUCT_QUANTITY)));
                product.setStringImage(base64Encoded);
                list.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
        }
        return list;
    }

    public boolean updateProduct(Product product) {
        PreparedStatement pst;
        boolean isScuccess = false;
        try {
            pst = connection.prepareStatement("UPDATE " + Constants.PRODUCT_TABLE_NAME
                    + " SET " + Constants.COLUMN_PRODUCT_NAME + " = ?,"
                    + Constants.COLUMN_PRODUCT_DESCRIPTION + " = ?,"
                    + Constants.COLUMN_PRODUCT_PRICE + " = ?,"
                    + Constants.COLUMN_PRODUCT_QUANTITY + " = ?,"
                    + Constants.COLUMN_PRODUCT_CATEGORY_ID + " = ?,"
                    + Constants.COLUMN_PRODUCT_DISCOUNT + " = ? WHERE "
                    + Constants.COLUMN_PRODUCT_ID + " = ?");
            pst.setString(1, product.getName());
            pst.setString(2, product.getDescription());
            pst.setDouble(3, product.getPrice());
            pst.setInt(4, product.getQuantity());
            pst.setInt(5, product.getCategoryId());
            pst.setDouble(6, product.getDiscount());
            pst.setInt(7, product.getId());

            int i = pst.executeUpdate();

            if (i != 0) {
                isScuccess = true;
            }

            if (product.getPart() != null) {
                pst = connection.prepareStatement("UPDATE " + Constants.PRODUCT_TABLE_NAME
                        + " SET " + Constants.COLUMN_PRODUCT_IMAGE + " = ? WHERE "
                        + Constants.COLUMN_PRODUCT_ID + " = ?");
                pst.setBlob(1, product.getPart().getInputStream());
                pst.setInt(2, product.getId());

                int j = pst.executeUpdate();

                if (j == 0) { // fail
                    isScuccess = false;
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
            ex.printStackTrace();
            System.out.println("status = " + isScuccess);
        } catch (IOException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isScuccess;
    }

    public Product getProductById(int id) {
        Product product = null;
        try {
            PreparedStatement ps = connection.prepareStatement("select * from  product where "
                    + Constants.COLUMN_PRODUCT_ID + " =? ");
            ps.setInt(1, id);
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
                byte[] encodeBase64 = Base64.encodeBase64(output.toByteArray());
                String base64Encoded = new String(encodeBase64, "UTF-8");

                output.close();
                product.setCategoryId(Integer.parseInt(rs.getString(Constants.COLUMN_PRODUCT_CATEGORY_ID)));
                product.setDescription(rs.getString(Constants.COLUMN_PRODUCT_DESCRIPTION));
                product.setDiscount(rs.getInt(Constants.COLUMN_PRODUCT_DISCOUNT));
                product.setId(Integer.parseInt(rs.getString(Constants.COLUMN_PRODUCT_ID)));
                product.setName(rs.getString(Constants.COLUMN_PRODUCT_NAME));
                product.setPrice(rs.getInt(Constants.COLUMN_PRODUCT_PRICE));
                product.setQuantity(Integer.parseInt(rs.getString(Constants.COLUMN_PRODUCT_QUANTITY)));
                product.setStringImage(base64Encoded);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
        }
        return product;
    }

    public List<Product> getProductsByCategoryId(int category_id) {
        List<Product> list = new ArrayList<Product>();
        Product product = null;
        try {
            PreparedStatement ps = connection.prepareStatement("select * from  product where "
                    + Constants.COLUMN_PRODUCT_CATEGORY_ID + "  =? ");
            ps.setInt(1, category_id);
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
                byte[] encodeBase64 = Base64.encodeBase64(output.toByteArray());
                String base64Encoded = new String(encodeBase64, "UTF-8");
                output.close();
                product.setCategoryId(Integer.parseInt(rs.getString(Constants.COLUMN_PRODUCT_CATEGORY_ID)));
                product.setDescription(rs.getString(Constants.COLUMN_PRODUCT_DESCRIPTION));
                product.setDiscount(rs.getInt(Constants.COLUMN_PRODUCT_DISCOUNT));
                product.setId(Integer.parseInt(rs.getString(Constants.COLUMN_PRODUCT_ID)));
                product.setName(rs.getString(Constants.COLUMN_PRODUCT_NAME));
                product.setPrice(rs.getInt(Constants.COLUMN_PRODUCT_PRICE));
                product.setQuantity(Integer.parseInt(rs.getString(Constants.COLUMN_PRODUCT_QUANTITY)));
                product.setStringImage(base64Encoded);
                list.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
        }
        return list;
    }

    public List<Product> getProductsByNmaeAndPrice(int category_id, String productName, int startprice, int endPrice) {
        List<Product> list = new ArrayList<Product>();
        Product product = null;
        try {

            PreparedStatement ps = connection.prepareStatement("select * from  product where "
                    + Constants.COLUMN_PRODUCT_CATEGORY_ID + "  =?  and "
                    + Constants.COLUMN_PRODUCT_NAME + "  like ? and "
                    + Constants.COLUMN_PRODUCT_PRICE + " between  ? and ?");
            ps.setInt(1, category_id);
            ps.setString(2, "%" + productName + "%");
            ps.setInt(3, startprice);
            ps.setInt(4, endPrice);

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
                byte[] encodeBase64 = Base64.encodeBase64(output.toByteArray());
                String base64Encoded = new String(encodeBase64, "UTF-8");
                output.close();
                product.setCategoryId(Integer.parseInt(rs.getString(Constants.COLUMN_PRODUCT_CATEGORY_ID)));
                product.setDescription(rs.getString(Constants.COLUMN_PRODUCT_DESCRIPTION));
                product.setDiscount(rs.getInt(Constants.COLUMN_PRODUCT_DISCOUNT));
                product.setId(Integer.parseInt(rs.getString(Constants.COLUMN_PRODUCT_ID)));
                product.setName(rs.getString(Constants.COLUMN_PRODUCT_NAME));
                product.setPrice(rs.getInt(Constants.COLUMN_PRODUCT_PRICE));
                product.setQuantity(Integer.parseInt(rs.getString(Constants.COLUMN_PRODUCT_QUANTITY)));
                product.setStringImage(base64Encoded);
                list.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
        }
        return list;
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

}
