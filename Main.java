/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hi;

import java.util.Scanner;

/**
 *
 * @author pc
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ManageCustomer manageCustomer = new ManageCustomer();
        ManageMenu manageMenu = new ManageMenu();
        ManageOrder manageOrder = new ManageOrder(manageCustomer, manageMenu);
        ManageFile manageFile = new ManageFile();
        manageMenu.loadMenusFromCSV("feastMenu.csv");

        int choice;
        do {
            System.out.println("========= Traditional Feast Order Management =========");
            System.out.println("1. Register customers");
            System.out.println("2. Update customer information");
            System.out.println("3. Search for customer information by name");
            System.out.println("4. Display feast menus");
            System.out.println("5. Place a feast order");
            System.out.println("6. Update order information");
            System.out.println("7. Save data to file");
            System.out.println("8. Display Customer or Order lists");
            System.out.println("9. Quit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number 1-9.");
                choice = 0;
            }

            switch (choice) {
                case 1:
                    manageCustomer.registerCustomer();
                    break;
                case 2:
                    manageCustomer.updateCustomer();
                    break;
                case 3:
                    manageCustomer.searchCustomerByName();
                    break;
                case 4:
                    manageMenu.displayMenus();
                    break;
                case 5:
                    manageOrder.placeOrder();
                    break;
                case 6:
                    manageOrder.updateOrder();
                    break;
                case 7:
                    manageFile.saveCustomers(manageCustomer.getCustomers());
                    manageFile.saveOrders(manageOrder.getOrders());
                    break;
                case 8:
                    System.out.println("1. Display customers");
                    System.out.println("2. Display orders");
                    System.out.print("Your choice: ");
                    String subChoice = sc.nextLine().trim();
                    if (subChoice.equals("1")) {
                        manageCustomer.displayCustomers();
                    } else if (subChoice.equals("2")) {
                        manageOrder.displayOrders();
                    } else {
                        System.out.println("Invalid choice!");
                    }
                    break;
                case 9:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        } while (choice != 9);
    }
}
