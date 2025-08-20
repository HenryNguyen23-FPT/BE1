/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pc
 */
public class ManageOrder implements IOrder{
    private List<Order> orders = new ArrayList<>();
    private List<Menu> menus = new ArrayList<>();
    private ManageCustomer manageCustomer;
    private ManageMenu manageMenu;
    private Scanner sc = new Scanner(System.in);

    public ManageOrder(ManageCustomer manageCustomer, ManageMenu manageMenu) {
        this.manageCustomer = manageCustomer;
        this.manageMenu = manageMenu;
    }
    
    @Override
    public void placeOrder() {
        System.out.println("New Order");
        System.out.print("Enter customer code: ");
        String customerCode = sc.nextLine().trim();
        if (manageCustomer.findByCode(customerCode) == null) {
            System.out.println("This customer doesn't exist");
            return;
        }
        System.out.print("Enter feast menu code: ");
        String menuCode = sc.nextLine().trim();
        Menu menu = manageMenu.findByCode(menuCode);
        if (menu == null) {
            System.out.println("This menu doesn't exist");
            return;
        }
        
        System.out.print("Enter event date (dd/MM/yyyy): ");
        Date eventDate;
        try {
            String dateStr = sc.nextLine().trim();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            eventDate = sdf.parse(dateStr);
        } catch (Exception e) {
            System.out.println("Invalid date format");
            return;
        }
        
        for (Order existing : orders) {
        if (existing.getCustomerCode().equalsIgnoreCase(customerCode) &&
        existing.getSetMenu().equalsIgnoreCase(menuCode) &&
        existing.getEventDate().equals(eventDate)) {
        System.out.println("Dupplicate data !");
        return;
        }
    }
        System.out.print("Enter number of tables: ");
        int tables;
        try {
            System.out.print("Enter number of tables: ");
            tables = sc.nextInt();
            sc.nextLine();
            if (tables <= 0) {
                System.out.println("Number of tables must>0");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number of tables");
            return;
        }

        String orderId = "0"+(orders.size() + 1);
        double totalCost = menu.getPrice() * tables;
        Order order = new Order(orderId, customerCode, menuCode, eventDate, tables, totalCost);
        orders.add(order);
        System.out.println("Order successfully");
        System.out.println("Order ID: " + order.getOrderId()+" | Customer: " + order.getCustomerCode() +" | Menu: " + order.getSetMenu() +" | Date: " + new SimpleDateFormat("dd/MM/yyyy").format(order.getEventDate()) +" | Tables: " + order.getNumOfTable() +" | Total: " + order.getTotalCost());
 
    }

    @Override
    public void updateOrder() {
        System.out.print("Enter Order ID: ");
        String orderId = sc.nextLine().trim();
        Order o = null;
        for (Order or : orders) {   
        if (or.getOrderId().equalsIgnoreCase(orderId)) {
            o = or;
            break;
        }
    }
        if (o == null) {
        System.out.println("This Order doesn't exist");
        return;
        }
        Date today = new Date();   
        if (!o.getEventDate().after(today)) {
            System.out.println("Cannot update an order with past event date.");
            return;
        }
        
        System.out.print("Update menu code: ");
        String mc = sc.nextLine().trim();
        if (!mc.isEmpty()) {
            boolean found = false;
            for (Menu m : menus) {    
                if (m.getCode().equalsIgnoreCase(mc)) {
                    o.setSetMenu(mc);
                    o.setTotalCost(m.getPrice() * o.getNumOfTable());
                    found=true;
                    break;
                }
            }
            if(!found){
                System.out.println("Invalid.Keep old value");
            }
        }
        
        System.out.print("Update number of tables: ");
        String t = sc.nextLine().trim();
        if (!t.isEmpty()) {
        try {
            int tb = Integer.valueOf(t);
            if (tb > 0) {
                o.setNumOfTable(tb);  
                for (Menu m : menus) {
                    if (m.getCode().equalsIgnoreCase(o.getSetMenu())) {
                        o.setTotalCost(m.getPrice() * tb);
                        break;
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid.Keep old value.");
        }
    }
        System.out.print("Update Event Date: ");
        String d = sc.nextLine().trim();
        if (!d.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date newDate = sdf.parse(d);
                if (newDate.after(today)) {
                    o.setEventDate(newDate);
                } else {
                    System.out.println("Event date must be in the future. Keep old value");
                }
            } catch (Exception e) {
                System.out.println("Invalid. Keep old value");
            }
        }
        System.out.println("Update Successfully");
    }

    @Override
    public void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("No data in the system.");
            return;
        }
        orders.sort((o1, o2) -> o1.getEventDate().compareTo(o2.getEventDate()));
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-10s | %-10s | %-12s | %-8s | %-6s | %-10s%n","ID", "Event date", "Customer ID", "SetMenu", "Tables", "Cost");
        System.out.println("-------------------------------------------------------------------------");
        
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }
    
}
