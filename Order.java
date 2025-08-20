/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hi;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author pc
 */
public class Order  implements Serializable{
    private String orderId;
    private String customerCode;
    private String setMenu;
    private Date eventDate;
    private int numOfTable;
    private double totalCost;

    public Order(String orderId, String customerCode, String setMenu, Date eventDate, int numOfTable, double totalCost) {
        this.orderId = orderId;
        this.customerCode = customerCode;
        this.setMenu = setMenu;
        this.eventDate = eventDate;
        this.numOfTable = numOfTable;
        this.totalCost = totalCost;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public String getSetMenu() {
        return setMenu;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public void setSetMenu(String setMenu) {
        this.setMenu = setMenu;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public int getNumOfTable() {
        return numOfTable;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setNumOfTable(int numOfTable) {
        this.numOfTable = numOfTable;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
}
