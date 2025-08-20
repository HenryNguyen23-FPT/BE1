/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hi;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author pc
 */
public class Menu implements Serializable{
    private String code;
    private String name;
    private List <String> ingredient;
    private double price;

    public Menu(String code, String name, double price,List<String> ingredient) {
        this.code = code;
        this.name = name;
        this.ingredient = ingredient;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredient() {
        return ingredient;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredient(List<String> ingredient) {
        this.ingredient = ingredient;
    }
    public String toString() {
        return String.format("%-6s | %-20s | %10.2f | %s", code, name, price, String.join("; ", ingredient));
    }
}
