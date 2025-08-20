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
public interface IOrder {
    void placeOrder();
    void updateOrder();
    void displayOrders();
    List<Order> getOrders();
}
