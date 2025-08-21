/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pc
 */
public class ManageCustomer  implements ICustomer{
    private List<Customer> customers = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private CheckValid cv = new CheckValid();
    private List<Order> orders;

    public ManageCustomer(List<Customer> customers) {
        this.customers = customers;
    }
    public ManageCustomer() {
        this.customers = new ArrayList<>();
    }
    
    @Override
    public void registerCustomer() {
        String code, name, phone, email;
        System.out.println("Register");
        while (true) {
            System.out.print("Enter customer code (Cxxxx/Gxxxx/Kxxxx): ");
            code = sc.nextLine().trim();
            if (!cv.isValidCustomerCode(code)) {
                System.out.println("Invalid");
                continue;
            }
            if (findByCode(code) != null) {
                System.out.println("Customer code already exists");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("Enter customer name (2-25 characters): ");
            name = sc.nextLine().trim();
            if (cv.isValidName(name)) 
                break;
            System.out.println("Invalid name");
        }
        while (true) {
            System.out.print("Enter phone number (10 digits): ");
            phone = sc.nextLine().trim();
            if (cv.isValidPhone(phone)) 
                break;
            System.out.println("Invalid");
        }
        while (true) {
            System.out.print("Enter email: ");
            email = sc.nextLine().trim();
            if (cv.isValidEmail(email)) 
                break;
            System.out.println("Invalid");
        }
        customers.add(new Customer(code, name, phone, email));
        System.out.println("Successfully!");
        
    }

    @Override
    public void updateCustomer() {
        System.out.println("Update Customer Information");
        System.out.print("Enter customer code to update: ");
        String code = sc.nextLine().trim();
        Customer newCustomer = null;
        for (Customer c : customers) {
        if (c.getCode().equalsIgnoreCase(code)) {
            newCustomer = c;
            break;
        }
    }
    if (newCustomer == null) {
        System.out.println("This customer does not exist.");
        return;
    }
        System.out.println("Old: "+newCustomer.getCode()+" | "+newCustomer.getName()+" | "+newCustomer.getPhone()+" | "+newCustomer.getEmail());
        
        System.out.print("Enter new name: ");
        String newName = sc.nextLine().trim();
        if (!newName.isEmpty()) {
            if (cv.isValidName(newName)) {
                newCustomer.setName(newName);
            } else {
                System.out.println("Invalid.Keep OG");
            }
        }
        
        System.out.print("Enter new phone number: ");
        String newPhoneNum = sc.nextLine().trim();
        if (!newPhoneNum.isEmpty()) {
            if (cv.isValidPhone(newPhoneNum)) {
                newCustomer.setPhone(newPhoneNum);
            } else {
                System.out.println("Invalid.Keep OG");
            }
        }
        
        System.out.print("Enter new email (leave blank to keep old): ");
        String newEmail = sc.nextLine().trim();
        if (!newEmail.isEmpty()) {
            if (cv.isValidEmail(newEmail)) {
                newCustomer.setEmail(newEmail);
            } else {
                System.out.println("Invalid.Keep OG");
            }
        }
        System.out.println("Update Successfully");
        
    }

    @Override
    public void searchCustomerByName() {
        System.out.println("Search Customer By Name");
        System.out.print("Enter full or partial name: ");
        String w = sc.nextLine().trim().toLowerCase();
        List<Customer> result = new ArrayList<>();
        for(Customer c : customers) {
        if(c.getName().toLowerCase().contains(w)) {
            result.add(c);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No one matches the search criteria!");
            return;
        }
        result.sort(Comparator.comparing(Customer::getName));
        System.out.println("----------------------------------------------------------------\n" +
                           String.format("%-6s | %-20s | %-10s | %-25s", "Code", "Customer Name", "Phone", "Email") + "\n" +
                           "----------------------------------------------------------------");
        for (Customer c : result) {
            System.out.println(String.format("%-6s | %-20s | %-10s | %-25s",c.getCode(), c.getName(), c.getPhone(), c.getEmail()));
        }
            System.out.println("----------------------------------------------------------------");
    }

    @Override
    public void displayCustomers() {
        if (customers == null || customers.isEmpty()) {
            System.out.println("Not found :(");
            return;
        }
        customers.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-10s | %-10s | %-12s | %-8s | %-6s | %-10s%n", "ID", "Event date", "Customer ID", "SetMenu", "Tables", "Cost");
        System.out.println("-------------------------------------------------------------------------");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Customer c : customers) {
        System.out.printf("%-6s | %-20s | %-10s | %-25s%n",c.getCode(), c.getName(), c.getPhone(), c.getEmail());
            }
        System.out.println("----------------------------------------------------------------");
    }

    @Override
    public Customer findByCode(String code) {
        for (Customer c : customers) { 
            if (c.getCode().equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }
    
}


