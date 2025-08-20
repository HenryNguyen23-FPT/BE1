/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class ManageFile {
    public void saveCustomers(List<Customer> customers) {
        try {
            FileOutputStream fos = new FileOutputStream("customers.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(customers);
            oos.close();
            fos.close();
            
            System.out.println("Customer data save");
        } catch (Exception e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

    public void saveOrders(List<Order> orders) {
        try {
            FileOutputStream fos = new FileOutputStream("feast_order_service.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(orders);
            oos.close();
            fos.close();

            System.out.println("Order data saved");
        } catch (Exception e) {
            System.out.println("Error saving orders: " + e.getMessage());
        }
    }

    public List<Customer> loadCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("customers.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);

            customers = (List<Customer>) ois.readObject();
            ois.close();
            fis.close();
        
        } catch (Exception e) {
            System.out.println("No customer data found");
        }
        return customers;
    }

    public List<Order> loadOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("feast_order_service.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);

            orders = (List<Order>) ois.readObject(); 
            ois.close();
            fis.close();
        
        } catch (Exception e) {
            System.out.println("No order data found");
        }
        return orders;
    }
}
