package Game;

import java.util.ArrayList;

import AbstractClass.Plant;
import Interface.Stockable;
import Plant.*;

public class Inventory implements Stockable {
    private ArrayList<Plant> inventory;

    public Inventory() {
        this.inventory = new ArrayList<Plant>();
        addItem(new Peashooter(0,0)); 
        // INITILIZE WITH OTHER PLANTS 
    }

    public void addItem(Plant plant) {
        inventory.add(plant);
    }

    public void removeItem(Plant plant) {
        inventory.remove(plant);
    }   

    public void switchPlacement(int index1, int index2) {
        // Validate indices within inventory bounds
        if (index1 >= 0 && index1 < inventory.size() &&
            index2 >= 0 && index2 < inventory.size()) {
      
            Plant temp = inventory.get(index1);
            inventory.set(index1, inventory.get(index2));
            inventory.set(index2, temp);
        } else {
            System.out.println("Invalid placement indices!");
        }
    }
    
    public void printInventory() {
        System.out.println("Inventory:");
        for (int i = 0; i < inventory.size(); i++) {
            Plant plant = inventory.get(i);
            System.out.println("  - " + plant.getName() + " (Index: " + i + ")");
        }
      }
}


