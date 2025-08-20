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
public interface IMenu {
    void loadMenusFromCSV(String path);
    void displayMenus();
    Menu findByCode(String code);
    List<Menu> getMenus();
}
