/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.DTO;

/**
 *
 * @author Mainh
 */
public class User {
    private String userID;
    private int password;
    private String fullName;
    private int role;
    public User() {
        this.userID = null;
        this.password = 0;
        this.fullName = null;
        this.role = 0;
    }

    public User(String userID, int password, String fullName, int role) {
        this.userID = userID;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    @Override
    public String toString() {
        return String.format("%s,%d,%s,%d", userID, password, fullName, role);
    }
}
