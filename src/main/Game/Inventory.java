package main.Game;

import java.util.ArrayList;

import main.AbstractClass.Plant;
import main.Exception.IndexOutOfRangeException;
import main.Exception.InvalidInventorySwapException;
import main.Exception.RemoveNullException;
import main.Interface.Stockable;
import main.Plant.*;

public class Inventory implements Stockable {
    private ArrayList<Slot<Plant>> inventory;
    public Inventory() {
        this.inventory = new ArrayList<>();
        addItem(new Peashooter(0,0)); 
        // INITILIZE WITH OTHER PLANTS 
    }

    public void addItem(Plant plant) {
        Slot<Plant> newSlot = new Slot<>(plant);
        inventory.add(newSlot);
    }

    public void removeItem(Plant plant) throws RemoveNullException {
        if (plant == null) {
            throw new RemoveNullException("Cannot remove null from inventory.");
        }
        boolean removed = false;
        for (Slot<Plant> slot : inventory) {
            if (plant.equals(slot.getItem())) {
                slot.setItem(null);
                removed = true;
                break;
            }
        }
        if (!removed) {
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
        Slot<Plant> temp = inventory.get(index1);
        inventory.set(index1, inventory.get(index2));
        inventory.set(index2, temp);
            
        
    }
    public void printInventory() {
        System.out.println("Inventory:");
        for (int i = 0; i < inventory.size(); i++) {
            Slot<Plant> slot = inventory.get(i);
            if (!slot.isEmpty()) {
                Plant plant = slot.getItem();
                System.out.println("  - " + plant.getName() + " (Index: " + i + ")");
            } else {
                System.out.println("  - Empty slot (Index: " + i + ")");
            }
        }
      }

      public Plant getPlant(int index) {
        if (index >= 0 && index < inventory.size()) {
            Slot<Plant> slot = inventory.get(index);
            return slot.getItem();
        } else {
            System.out.println("Invalid index!");
            return null;
        }
    }
}


