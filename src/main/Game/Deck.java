package main.Game;

import java.util.ArrayList;
import java.util.Collections;
import main.AbstractClass.Plant;
import main.Exception.InvalidStoringException;
import main.Exception.RemoveNullException;
import main.Interface.Stockable;

public class Deck implements Stockable {
    private ArrayList<Plant> plants;
    private static final int MAX_SIZE = 6;
    private Inventory inventory;

    public Deck() {
        plants = new ArrayList<>();
        inventory = new Inventory();  
    }

    // public void setDeck(Plant plant) throws InvalidStoringException {
    //     while(plants.size() < MAX_SIZE)
    //     {
    //         Inventory.printInventory();
                
    //     }
    // }

    // Adds a Plant to the deck if there is space and it's not already in the deck
    public void addItem(Plant plant) throws InvalidStoringException {
        if (plant == null) {
            throw new IllegalArgumentException("Plant cannot be null");
        }
        if (plants.contains(plant)) {
            throw new InvalidStoringException("Plant is already in the deck.");
        }
        int emptySlot = plants.indexOf(null);
        if (emptySlot != -1) {
            plants.set(emptySlot, plant);
        }
        else {
            throw new InvalidStoringException("Deck is full.");
        }
    }

    // Removes a Plant from the deck
    public void removeItem(Plant plant) throws RemoveNullException {
        if (plant == null) {
            throw new IllegalArgumentException("Plant cannot be null");
        }
        if (!plants.remove(plant)) {
            throw new RemoveNullException("Plant not found in the deck.");
        }
    }

    // Switches the placement of two items in the deck. 
    // Can switch a plant and an empty slot but cannot switch null with null.
    public void switchPlacement(int index1, int index2) {
        if (index1 < 0 || index1 >= MAX_SIZE || index2 < 0 || index2 >= MAX_SIZE) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        // Ensuring neither index points to null if both are beyond the current list size
        if (index1 >= plants.size() && index2 >= plants.size()) {
            System.out.println("Cannot switch null with null.");
        } else {
            // Ensuring the list has both indices covered
            while (plants.size() <= Math.max(index1, index2)) {
                plants.add(null);
            }
            Collections.swap(plants, index1, index2);
            // Clean up any trailing nulls that might no longer be needed
            cleanUpTrailingNulls();
        }
    }

    // Helper method to remove trailing nulls to avoid list bloat
    private void cleanUpTrailingNulls() {
        for (int i = plants.size() - 1; i >= 0; i--) {
            if (plants.get(i) == null) {
                plants.remove(i);
            } else {
                break;
            }
        }
    }

    public void printDeck() {
        System.out.println("Current Deck:");
        for (int i = 0; i < plants.size(); i++) {
            if (plants.get(i) != null) {
                System.out.println("Slot " + (i + 1) + ": " + plants.get(i));
            } else {
                System.out.println("Slot " + (i + 1) + ": Empty");
            }
        }
        
        for (int i = plants.size(); i < MAX_SIZE; i++) {
            System.out.println("Slot " + (i + 1) + ": Empty");
        }
    }
    public void setDeck(Plant plant, int quantity) {
        for (int i = 0; i < quantity; i++) {
            if (plants.size() < MAX_SIZE) {
                plants.add(plant);
            } else {
                System.out.println("Deck is full. Cannot add more plants.");
                break;
            }
        }
    }

    public void setCoolDown(int index, int cooldown) {
        Plant plant = plants.get(index);
        if (plant != null) {
            plant.setAttackCooldown(cooldown);
        } else {
            System.out.println("No plant found at the given index.");
        }
    }
    public Inventory getInventory() {
        return inventory;
    }

    public void addPlantFromInventory(int inventoryIndex, int deckSlot) throws InvalidStoringException, RemoveNullException {
        Plant plant = inventory.getPlant(inventoryIndex);
        if (plant != null) {
            addItem(plant);
            inventory.removeItem(plant);
        } else {
            System.out.println("No plant found at the given inventory index.");
        }
    }

    // public boolean isPlantCooldown(Plant plant)
    // {
    //     if(plant.getCurrentCooldown() > 0)
    //     {
    //         return true;
    //     }
    //     else return false;
    // }
}

