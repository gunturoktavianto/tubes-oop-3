package com.app.Game;

import java.util.ArrayList;
import java.util.List;

import com.app.AbstractClass.Plant;
import com.app.Exception.IndexOutOfRangeException;
import com.app.Exception.InvalidInventorySwapException;
import com.app.Exception.RemoveNullException;
import com.app.Exception.InvalidPlantingException;
import com.app.Interface.Stockable;
import com.app.Plant.*;
import com.app.Game.Sun;


public class Deck {
    private ArrayList<Pair<Plant, Integer>> deck;
    private final int MAX_SIZE = 6;
    
    public void plantCooldownThread() // panggil di main atau di game balll
    {
        for(int i = 0; i < deck.size(); i++)
        {
            if (deck.get(i).getValue() > 0) 
                deck.get(i).setValue(deck.get(i).getValue() - 1);
        }
    }

    public Deck(){
        deck = new ArrayList<>();
        Pair<Plant, Integer> newPair = new  Pair<Plant, Integer>(null, 0);
        for(int i = 0; i < MAX_SIZE; i++)
        {
            deck.add(newPair);
        }
    }

    
    public List<Pair<Plant, Integer>> getDeck(){
        return deck;
    }

    // public void setDeckElement(int i, Pair<Plant, Integer> newPair)
    // {
    //     deck.get(i).setPair(newPair);
    // }

    // public void setDeckElement(int i, Plant plant, Integer cd)
    // {

    //     deck.get(i).setKey(plant);
    //     deck.get(i).setValue(cd);
    // }
    public boolean isEmpty(){
        int i =0;
        while(i < 6)
        {
            if(deck.get(i).getKey() != null) return false;
            i++;
        }
        return true;
    }

    public boolean isFull(){
        int i =0;
        while(i < 6)
        {
            if(deck.get(i).getKey() == null) return false;
            i++;
        }
        return true;
    }

    public void showDeck(){
        int i = 1;
        System.out.println();
        System.out.println("Deck : ");
        if (!deck.isEmpty()){
            for (Pair<Plant, Integer> p: deck){
                System.out.println(i + ". " + deck.get(i-1).getKey().getName() + " Cooldown : " + deck.get(i-1).getValue());
                i++;
            }
            for (int j = i; j<7; j++)
            {
                System.out.println(i + ". Empty");
                i++;
            }
        } else {
            System.out.println("Deck is empty");
        }
        System.out.println();
    }

    public void showDeckRev(){
        int i = 1;
        System.out.println();
        System.out.println("Deck : ");
        if (!deck.isEmpty()){
            for (Pair<Plant, Integer> p: deck){
                if(p.getKey() == null)
                {
                    System.out.println(i + ". Empty");
                }
                else System.out.println(i + ". " + deck.get(i-1).getKey().getName());
                i++;
            }
        } else {
            System.out.println("Deck is empty");
        }
        System.out.println();
    }

    public void showDeckStatus(){
        int i = 1;
        System.out.println();
        System.out.println("Deck : ");
        if (!deck.isEmpty()){
            for (Pair<Plant, Integer> p: deck){
                if(p.getKey() == null)
                {
                    System.out.println(i + ". Empty");
                }
                else System.out.println(i + ". " + deck.get(i-1).getKey().getName() + " Cooldown : " + deck.get(i-1).getValue());
                i++;
            }
        } else {
            System.out.println("Deck is empty");
        }
        System.out.println();
    }

    public void plant (int x, int y, int i) 
        throws InvalidPlantingException                                         // xy koordinat i index di deck
    {
        if(deck.get(i-1).getValue() == 0) // ready
        {
            if (deck.get(i-1).getKey().getCost() <= Sun.getSun())
            {
                if (deck.get(i-1).getKey().getName() != null && deck.get(i-1).getKey().getName().equals("Chomper"))
                {
                    try
                    {
                        Lawn.plant(x, y, new Chomper());
                        deck.get(i-1).setValue(deck.get(i-1).getKey().getPlantingCooldown());
                        Sun.setSun(Sun.getSun() - deck.get(i-1).getKey().getCost());
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
                else if(deck.get(i-1).getKey().getName() != null && deck.get(i-1).getKey().getName().equals("Jalapeno"))
                {
                    try {
                        Lawn.plant(x, y, new Jalapeno());
                        deck.get(i-1).setValue(deck.get(i-1).getKey().getPlantingCooldown());
                        Sun.setSun(Sun.getSun() - deck.get(i-1).getKey().getCost());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    
                }
                else if(deck.get(i-1).getKey().getName() != null && deck.get(i-1).getKey().getName().equals("Kubis"))
                {
                    try {
                        Lawn.plant(x, y, new Kubis());
                        deck.get(i-1).setValue(deck.get(i-1).getKey().getPlantingCooldown());
                        Sun.setSun(Sun.getSun() - deck.get(i-1).getKey().getCost());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    
                }
                else if(deck.get(i-1).getKey().getName() != null && deck.get(i-1).getKey().getName().equals("Lilypad"))
                {
                    try {
                        Lawn.plant(x, y, new Lilypad());
                        deck.get(i-1).setValue(deck.get(i-1).getKey().getPlantingCooldown());
                        Sun.setSun(Sun.getSun() - deck.get(i-1).getKey().getCost());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                else if(deck.get(i-1).getKey().getName() != null && deck.get(i-1).getKey().getName().equals("Peashooter"))
                {
                    try {
                        Lawn.plant(x, y, new Peashooter());
                        deck.get(i-1).setValue(deck.get(i-1).getKey().getPlantingCooldown());
                        Sun.setSun(Sun.getSun() - deck.get(i-1).getKey().getCost());
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }  
                else if(deck.get(i-1).getKey().getName() != null && deck.get(i-1).getKey().getName().equals("Repeater"))
                {
                    try {
                        Lawn.plant(x, y, new Repeater());
                        deck.get(i-1).setValue(deck.get(i-1).getKey().getPlantingCooldown());
                        Sun.setSun(Sun.getSun() - deck.get(i-1).getKey().getCost());
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } 
                else if(deck.get(i-1).getKey().getName() != null && deck.get(i-1).getKey().getName().equals("SnowPea"))
                {
                    try {
                        Lawn.plant(x, y, new SnowPea());
                        deck.get(i-1).setValue(deck.get(i-1).getKey().getPlantingCooldown());
                        Sun.setSun(Sun.getSun() - deck.get(i-1).getKey().getCost());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    
                }
                else if(deck.get(i-1).getKey().getName() != null && deck.get(i-1).getKey().getName().equals("Squash"))
                {
                    try {
                        Lawn.plant(x, y, new Squash());
                        deck.get(i-1).setValue(deck.get(i-1).getKey().getPlantingCooldown());
                        Sun.setSun(Sun.getSun() - deck.get(i-1).getKey().getCost());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    
                }
                else if(deck.get(i-1).getKey().getName() != null && deck.get(i-1).getKey().getName().equals("Sunflower"))
                {
                    try {
                        Lawn.plant(x, y, new Sunflower());
                        deck.get(i-1).setValue(deck.get(i-1).getKey().getPlantingCooldown());
                        Sun.setSun(Sun.getSun() - deck.get(i-1).getKey().getCost());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    
                }
                else if(deck.get(i-1).getKey().getName() != null && deck.get(i-1).getKey().getName().equals("Wallnut"))
                {
                    try {
                        Lawn.plant(x, y, new Wallnut());
                        deck.get(i-1).setValue(deck.get(i-1).getKey().getPlantingCooldown());
                        Sun.setSun(Sun.getSun() - deck.get(i-1).getKey().getCost());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            else
            {
                System.out.println("SUN TIDAK MENCUKUPI!");
            }
        }
        else
        {
            System.out.println("PLANT MASIH COOLDOWN");
        }
    }

    public void dig(int x, int y) throws IllegalArgumentException
    { // nti tambahin NotShovelableException 
        if (x < 0 || x >= 6 || y < 1 || y >= 10) {
            throw new IllegalArgumentException("Index out of bounds");
        } else {
            if (x == 2 || x == 3)
            {
                if (Lawn.getLawn().get(x).get(y).hasPlant())
                {
                    ((Lilypad)Lawn.getLawn().get(x).get(y).getPlant()).removePlantedPlant(); // DIG LILYPAD HANYA UNTUK PLANTED PLANT
                }
            }
            else
            {
                if (Lawn.getLawn().get(x).get(y).hasPlant())
                {
                    Lawn.getLawn().get(x).get(y).removePlant();
                }
                else 
                {
                    System.out.println("TIDAK ADA PLANT YANG BISA DI DIG");
                }
            }
        }
    }

    public void printDeck() {
        System.out.println("Current Deck:");
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i) != null) {
                System.out.println("Slot " + (i + 1) + ": ");
            } else {
                System.out.println("Slot " + (i + 1) + ": Empty");
            }
        }
        
        for (int i = deck.size(); i < MAX_SIZE; i++) {
            System.out.println("Slot " + (i + 1) + ": Empty");
        }
    }

    // // Adds a Plant to the deck if there is space and it's not already in the deck
    // public void addItem(Plant plant) throws InvalidStoringException {
    //     if (plant == null) {
    //         throw new IllegalArgumentException("Plant cannot be null");
    //     }
    //     if (deck.contains(plant)) {
    //         throw new InvalidStoringException("Plant is already in the deck.");
    //     }
    //     int emptySlot = deck.indexOf(null);
    //     if (emptySlot != -1) {
    //         deck.set(emptySlot, plant);
    //     }
    //     else {
    //         throw new InvalidStoringException("Deck is full.");
    //     }
    // }

    // Removes a Plant from the deck
    // public void removeItem(Plant plant) throws RemoveNullException {
    //     if (plant == null) {
    //         throw new IllegalArgumentException("Plant cannot be null");
    //     }
    //     if (!deck.remove(plant)) {
    //         throw new RemoveNullException("Plant not found in the deck.");
    //     }
    // }

    // // Switches the placement of two items in the deck. 
    // // Can switch a plant and an empty slot but cannot switch null with null.
    // public void switchPlacement(int index1, int index2) {
    //     if (index1 < 0 || index1 >= MAX_SIZE || index2 < 0 || index2 >= MAX_SIZE) {
    //         throw new IndexOutOfBoundsException("Index out of bounds.");
    //     }
    //     // Ensuring neither index points to null if both are beyond the current list size
    //     if (index1 >= deck.size() && index2 >= deck.size()) {
    //         System.out.println("Cannot switch null with null.");
    //     } else {
    //         // Ensuring the list has both indices covered
    //         while (deck.size() <= Math.max(index1, index2)) {
    //             deck.add(null);
    //         }
    //         Collections.swap(deck, index1, index2);
    //         // Clean up any trailing nulls that might no longer be needed
    //         cleanUpTrailingNulls();
    //     }
    // }

    // public void setDeck(Plant plant, int quantity) {
    //     for (int i = 0; i < quantity; i++) {
    //         if (deck.size() < MAX_SIZE) {
    //             deck.add(plant);
    //         } else {
    //             System.out.println("Deck is full. Cannot add more deck.");
    //             break;
    //         }
    //     }
    // }

    // public void setCoolDown(int index, int cooldown) {
    //     Plant plant = deck.get(index);
    //     if (plant != null) {
    //         plant.setAttackCooldown(cooldown);
    //     } else {
    //         System.out.println("No plant found at the given index.");
    //     }
    // }
    // public Inventory getInventory() {
    //     return inventory;
    // }

    // public void addPlantFromInventory(int inventoryIndex, int deckSlot) throws InvalidStoringException, RemoveNullException {
    //     Plant plant = inventory.getPlant(inventoryIndex);
    //     if (plant != null) {
    //         addItem(plant);
    //         inventory.removeItem(plant);
    //     } else {
    //         System.out.println("No plant found at the given inventory index.");
    //     }
    // }

    // public boolean isPlantCooldown(Plant plant)
    // {
    //     if(plant.getCurrentCooldown() > 0)
    //     {
    //         return true;
    //     }
    //     else return false;
    // }
}

