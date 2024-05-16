package main.Game;

import java.util.ArrayList;

import main.AbstractClass.Plant;
import main.Exception.IndexOutOfRangeException;
import main.Exception.InvalidInventorySwapException;
import main.Exception.RemoveNullException;
import main.Interface.Stockable;
import main.Plant.*;

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

    public void removeItem(Plant plant) throws RemoveNullException {
        if (plant == null) {
            throw new RemoveNullException("Cannot remove null from inventory.");
        }
        if (!inventory.remove(plant)) {
            System.out.println("Plant not found in the inventory.");
        }
    }   

    public void switchPlacement(int index1, int index2) throws InvalidInventorySwapException, IndexOutOfRangeException  {
        if (index1 < 0 || index1 >= inventory.size() || index2 < 0 || index2 >= inventory.size()) {
            throw new IndexOutOfRangeException("Index out of range.");
        }
        if (index1 == index2 || (inventory.get(index1) == null && inventory.get(index2) == null)) {
            throw new InvalidInventorySwapException("Invalid swap operation.");
        }
            Plant temp = inventory.get(index1);
            inventory.set(index1, inventory.get(index2));
            inventory.set(index2, temp);
            
        
    }
    public void printInventory() {
        System.out.println("Inventory:");
        for (int i = 0; i < inventory.size(); i++) {
            Plant plant = inventory.get(i);
            System.out.println("  - " + plant.getName() + " (Index: " + i + ")");
        }
      }

      public Plant getPlant(int index) {
        if (index >= 0 && index < inventory.size()) {
            return inventory.get(index);
        } else {
            System.out.println("Invalid index!");
            return null;
        }
    }
}


