/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hi;

import java.util.List;

/**
 *
 * @author pc
 */
public interface ICustomer {
    void registerCustomer();
    void updateCustomer();
    void searchCustomerByName();
    void displayCustomers();
    Customer findByCode(String code);
    List<Customer> getCustomers();
}
