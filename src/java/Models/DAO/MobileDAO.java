/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.DAO;

import Models.DTO.Mobile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mainh
 */
public class MobileDAO {

    public Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionString
                    = "jdbc:sqlserver://localhost:1433;databaseName=WorkShop1;instanceName=SQL2022";
            Connection cnn = DriverManager.getConnection(connectionString, "CheeseCreams", "Catnap123@");
            return cnn;
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }

    public Mobile getMobileById(String mobileId) throws Exception {
        Mobile mobile = null;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "select description, price, mobileName, yearOfProduction, quantity, notSale "
                    + "from tbl_Mobile where [mobileId] = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, mobileId);
            rs = preStm.executeQuery();
            while (rs.next()) {
                String description = rs.getString(1);
                float price = rs.getFloat(2);
                String mobileName = rs.getString(3);
                int yearOfProduction = rs.getInt(4);
                int quantity = rs.getInt(5);
                boolean notSale = rs.getBoolean(6);
                mobile = new Mobile(mobileId, description, price, mobileName, yearOfProduction, quantity, notSale);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return mobile;
    }

    public List<Mobile> getMobileByName(String mobileName) throws Exception {
        List<Mobile> mobileList = new ArrayList();
        Mobile mobile = null;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "select description, price, mobileName, mobileId, yearOfProduction, quantity, notSale "
                    + "from tbl_Mobile where [mobileName] like ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + mobileName + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                String description = rs.getString(1);
                float price = rs.getFloat(2);
                String mobileRealName = rs.getString(3);
                String mobileId = rs.getString(4);
                int yearOfProduction = rs.getInt(5);
                int quantity = rs.getInt(6);
                boolean notSale = rs.getBoolean(7);
                mobile = new Mobile(mobileId, description, price, mobileRealName, yearOfProduction, quantity, notSale);
                mobileList.add(mobile);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return mobileList;
    }
    public List<Mobile> getMobileList() throws Exception {
        List<Mobile> mobileList = new ArrayList();
        Mobile mobile = null;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "select description, price, mobileName, mobileId, yearOfProduction, quantity, notSale "
                    + "from tbl_Mobile";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                String description = rs.getString(1);
                float price = rs.getFloat(2);
                String mobileRealName = rs.getString(3);
                String mobileId = rs.getString(4);
                int yearOfProduction = rs.getInt(5);
                int quantity = rs.getInt(6);
                boolean notSale = rs.getBoolean(7);
                mobile = new Mobile(mobileId, description, price, mobileRealName, yearOfProduction, quantity, notSale);
                mobileList.add(mobile);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return mobileList;
    }

    public boolean deleteMobile(String mobileId) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        try {
            cnn = getConnection();
            String sql = "delete tbl_Mobile where mobileId=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, mobileId);
            return preStm.executeUpdate() > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public boolean updateMobile(Mobile mobile) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        try {
            cnn = getConnection();
            String sql = "UPDATE tbl_Mobile SET description = ?, price = ?, quantity = ?, notSale = ? WHERE mobileId = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, mobile.getDescription());
            preStm.setFloat(2, mobile.getPrice());
            preStm.setInt(3, mobile.getQuantity());
            preStm.setBoolean(4, mobile.isNotSale());
            preStm.setString(5, mobile.getMobileID());
            return preStm.executeUpdate() > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public boolean addMobile(Mobile mobile) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        try {
            cnn = getConnection();
            String sql = "insert into tbl_Mobile values (?, ?, ?, ?, ?, ?, ?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, mobile.getMobileID());
            preStm.setString(2, mobile.getDescription());
            preStm.setFloat(3, mobile.getPrice());
            preStm.setString(4, mobile.getMobileName());
            preStm.setInt(5, mobile.getYearOfProduction());
            preStm.setInt(6, mobile.getQuantity());
            preStm.setBoolean(7, mobile.isNotSale());
            return preStm.executeUpdate() > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }
}
