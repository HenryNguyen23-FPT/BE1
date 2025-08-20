/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author pc
 */
public class ManageMenu implements IMenu{
    private List<Menu> mu = new ArrayList<>();
    @Override
    public void loadMenusFromCSV(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("Cannot read data from feastMenu.csv. Please check it.");
            return;
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String code = parts[0].trim();
                    String name = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                List<String> ingredients = new ArrayList<>();
                String[] is = parts[3].split(";");
                for (String ing : is) {
                ingredients.add(ing.trim());
                }
            Menu m = new Menu(code, name, price, ingredients);
            mu.add(m);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        } finally {
            try {
                if (br != null) br.close();  
            } catch (IOException e) {}
        }
    }

    @Override
    public void displayMenus() {
        if (mu.isEmpty()) {
            System.out.println("No data in the system.");
            return;
        }
        mu.sort(Comparator.comparingDouble(Menu::getPrice));
        System.out.println("----------------------------------------------------------------");
        System.out.printf("%-6s | %-20s | %-10s | %s\n", "Code", "Menu Name", "Price", "Ingredients");
        System.out.println("----------------------------------------------------------------");
        for (Menu m : mu) {
            System.out.printf("%-6s | %-20s | %10.2f | %s\n", m.getCode(), m.getName(), m.getPrice(), String.join("; ", m.getIngredient()));
        }
        System.out.println("----------------------------------------------------------------");
    }

    @Override
    public Menu findByCode(String code) {
        for (Menu m : mu) {
            if (m.getCode().equalsIgnoreCase(code)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public List<Menu> getMenus() {
        return mu;
    }
}
