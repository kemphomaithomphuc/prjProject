/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.DAO;

import Models.DTO.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author Mainh
 */
public class UserDAO {

    public Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionString
                    = "jdbc:sqlserver://localhost:1433;databaseName=Workshop1;instanceName=SQL2022";
            Connection cnn = DriverManager.getConnection(connectionString, "CheeseCreams", "Catnap123@");
            return cnn;
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }

    public User login(String fullName, int password) throws Exception {
        User user = null;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "select userID, role from tbl_User where [fullName]=? and [password]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, fullName);
            preStm.setInt(2, password);
            rs = preStm.executeQuery();
            while (rs.next()) {
                String userId = rs.getString(1);
                int role = rs.getInt(2);
                user = new User(userId, password, fullName, role);
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
        return user;
    }

    public boolean addUser(User user) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        try {
            cnn = getConnection();
            String sql = "insert into tbl_User values (?, ?, ?, ?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, user.getUserID());
            preStm.setInt(2, user.getPassword());
            preStm.setString(3, user.getFullName());
            preStm.setInt(4, user.getRole());
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

    public boolean addEmail(String email, User user) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;

        try {
            cnn = this.getConnection();
            String sql = "INSERT INTO tbl_Email (userId, email) VALUES (?, ?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, user.getUserID());
            preStm.setString(2, email);
            return preStm.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            // Email already exists for this user
            System.out.println("Email already linked to user.");
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
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
