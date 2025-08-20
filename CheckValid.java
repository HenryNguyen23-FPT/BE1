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
public class CheckValid {
     public boolean isValidCustomerCode(String code) {
        if (code == null || code.length() != 5) 
            return false;
        char first = code.charAt(0);
        if (first != 'C' && first != 'G' && first != 'K') 
            return false;
        String numP = code.substring(1);
        for (int i = 0; i < numP.length(); i++) {
            if (!Character.isDigit(numP.charAt(i))) {
                return false;
            }
        }
        return true;
    }
     public boolean isValidName(String name) {
        if (name == null) 
            return false;
        name = name.trim();
        return name.length() >= 2 && name.length() <= 25;
    }
     public boolean isValidPhone(String phoneNum) {
        if (phoneNum== null || phoneNum.length() != 10) 
            return false;
        for (int i = 0; i < phoneNum.length(); i++) {
            if (!Character.isDigit(phoneNum.charAt(i))) {
                return false;
            }
        }
        return true;
    }
      public static boolean isValidEmail(String email) {
        if (email == null) 
            return false;
        email = email.trim();
        return (email.contains("@") && email.contains("."));
    }
      public boolean isValidTables(int tables) {
        return tables > 0;
    }
      public boolean isValidMenuCode(String code, List<Menu> menus) {
        if (code == null || code.isEmpty()) return false;
        for (Menu m : menus) {
            if (m.getCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }
      
}
