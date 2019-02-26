/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public boolean InsertInerests(int user_id,String category_id) {

PreparedStatement pst=null;
int added;
        try {
            pst = connection.prepareStatement("insert into " + Constants.INTERESTS_TABLE_NAME 
                    + "("
                    + Constants.COLUMN_INTERESTS_USER_ID  + " , "
                    + Constants.COLUMN_INTERESTS_CATEGORY_ID + "  "
  
                      + ") values(?,?)");

            pst.setInt(1,user_id );
            pst.setInt(2, Integer.parseInt(category_id));
            added=pst.executeUpdate();
            
            if(added>0){
            return true;
            }
        } catch (SQLException ex) {
             Logger.getLogger(InterestsDao.class.getName()).log(Level.SEVERE, null, ex);
         }
        finally{
    try {
        pst.close();
    } catch (SQLException ex) {
        Logger.getLogger(InterestsDao.class.getName()).log(Level.SEVERE, null, ex);
    }
        }
        
return false;
}
    
    
}
