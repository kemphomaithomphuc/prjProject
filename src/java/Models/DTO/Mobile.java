/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.DTO;

/**
 *
 * @author Mainh
 */
public class Mobile {
    private String mobileID;
    private String description;
    private float price;
    private String mobileName;
    private int yearOfProduction;
    private int quantity;
    private boolean notSale;
    public Mobile() {
        this.mobileID = null;
        this.description = null;
        this.price = 0;
        this.mobileName = null;
        this.yearOfProduction = 0;
        this.quantity = 0;
        this.notSale = false;
    }

    public Mobile(String mobileID, String description, float price, String mobileName, int yearOfProduction, int quantity, boolean notSale) {
        this.mobileID = mobileID;
        this.description = description;
        this.price = price;
        this.mobileName = mobileName;
        this.yearOfProduction = yearOfProduction;
        this.quantity = quantity;
        this.notSale = notSale;
    }

    public String getMobileID() {
        return mobileID;
    }

    public void setMobileID(String mobileID) {
        this.mobileID = mobileID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isNotSale() {
        return notSale;
    }

    public void setNotSale(boolean notSale) {
        this.notSale = notSale;
    }
    @Override
    public String toString() {
        return String.format("%s,%s,%f,%s,%d,%d,%b", mobileID, description, price, mobileName, yearOfProduction, quantity, notSale);
    }
}
