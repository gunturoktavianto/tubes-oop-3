package com.app.Game;

import java.util.ArrayList;

import com.app.AbstractClass.Plant;
import com.app.Exception.IndexOutOfRangeException;
import com.app.Exception.InvalidInventorySwapException;
import com.app.Exception.RemoveNullException;
import com.app.Exception.InvalidDeckSwapException;
import com.app.Exception.InvalidStoringException;
import com.app.Interface.Stockable;
import com.app.Plant.*;


public class Inventory{
    private ArrayList<Plant> inventory;
    private Inventory instance;
    private Deck deck;
    
    public Inventory() {
        this.inventory = new ArrayList<>();
        deck = new Deck();
        addItem(new Peashooter());
        addItem(new SnowPea()); 
        addItem(new Wallnut());
        addItem(new Chomper());
        addItem(new Jalapeno());
        addItem(new Squash());
        addItem(new Sunflower());
        addItem(new Repeater());
        addItem(new Lilypad());
        addItem(new Kubis());
    }

    public Inventory getInventoryInstance()                                        // SINGLETON DESIGN PATTERN
    {
        if (instance == null)
            instance = new Inventory();
        return instance;
    }

    public void addItem(Plant plant) {
        inventory.add(plant);
    }
    
    public ArrayList<Plant> getInventory(){
        return inventory;
    }

    public Deck getDeck(){
        return deck;
    }

    public void swapDeckPlacement (int x, int y) 
        throws IndexOutOfBoundsException, InvalidDeckSwapException
    {
        if (x < 0 || x > 6 || y < 0 || y > 6) 
        {
            throw new IndexOutOfBoundsException("INDEX TIDAK VALID");
        }
        
        if (deck.getDeck().get(x-1) == null || deck.getDeck().get(y-1) == null) 
        {
            throw new InvalidDeckSwapException("TIDAK BISA SWAP DENGAN SLOT NULL");
        }
        
        System.out.println("BERHASIL MELAKUKAN SWAP ANTARA " + 
            deck.getDeck().get(x-1).getKey().getName() + " DENGAN " + 
            deck.getDeck().get(y-1).getKey().getName()
        );

        Pair<Plant, Integer> temp = deck.getDeck().get(x-1);
        deck.getDeck().set(x-1, deck.getDeck().get(y-1));
        deck.getDeck().set(y-1, temp);
    }

    public void swapInventoryPlacement (int x, int y) 
        throws IndexOutOfBoundsException, InvalidDeckSwapException
    {
        if (x < 0 || x > 6 || y < 0 || y > 6) 
        {
            throw new IndexOutOfBoundsException("INDEX TIDAK VALID");
        }
        
        if (inventory.get(x-1) == null || inventory.get(y-1) == null) 
        {
            throw new InvalidDeckSwapException("TIDAK BISA SWAP DENGAN SLOT NULL");
        }
        
        System.out.println("BERHASIL MELAKUKAN SWAP ANTARA " + 
            inventory.get(x-1).getName() + " DENGAN " + 
            inventory.get(y-1).getName()
        );

        Plant temp = inventory.get(x-1);
        inventory.set(x-1, inventory.get(y-1));
        inventory.set(y-1, temp);
    }

    public void deletePlantFromDeck (int index){
        if (index < 1 || index > 6) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        else if (deck.getDeck().isEmpty()){
            throw new IllegalArgumentException("Deck is empty");
        }
        else if (deck.getDeck().get(index-1).getKey() == null){
            throw new IllegalArgumentException("No plant in deck");
        }
        try {
            Pair<Plant, Integer> newPair = new Pair<Plant,Integer>(null, 0);
            deck.getDeck().set(index-1, newPair);
            System.out.println(deck.getDeck().get(index-1).getKey().getName() + " has been deleted from deck");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean findPlantInDeck(Plant plant)
    {
        String t1 = plant.getClass().getSimpleName();
        
        for(int i = 0; i < deck.getDeck().size(); i++)
        {
            if(deck.getDeck().get(i).getKey() != null && deck.getDeck().get(i).getKey().getName().equals(t1))
            {
                return true;
            }
        }
        return false;
        // Pair<Plant, Integer> newPair = new Pair<Plant, Integer>(plant, plant.getPlantingCooldown());
        // if(deck.getDeck().contains(newPair)) return true;
        // else return false; // tidak ada
    }

    public void deletePlant (Plant plant){
        deck.getDeck().remove(plant);
    }

    public void addPlantToDeck (int i)
    {
        if (i > 10) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        // else if (deck.getDeck().size() >= 6){
        //     throw new IllegalArgumentException("Deck is full");
        // }
        else if (findPlantInDeck(inventory.get(i-1))){
            throw new IllegalArgumentException("Plant already in deck");
        }
        Pair<Plant, Integer> newPair = new Pair<Plant, Integer>(inventory.get(i-1), 0);
        System.out.println(inventory.get(i-1).getName() + " has been added to deck");
        deck.getDeck().add(newPair);
    }

    public void setPlantInDeck (int i, int j) throws InvalidStoringException// i deck, j inventory
    { 
        if (j > 10) {
            throw new IllegalArgumentException("Inventory hanya bisa memiliki 10 plant.");
        }
        if (i > 6) {
            throw new IllegalArgumentException("Deck hanya bisa menampung 6 plant.");
        }
        // else if (deck.getDeck().size() >= 6){
        //     throw new IllegalArgumentException("Deck is full");
        // }
        else if (findPlantInDeck(inventory.get(j-1))){
            System.out.println("Plant udah ada di deck");
            return;
        }
        try {
            
            Pair<Plant, Integer> newPair = new Pair<Plant,Integer>(inventory.get(j-1), 0);
            deck.getDeck().set(i-1, newPair);
            System.out.println(inventory.get(j-1).getName() + " has been added to deck");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // deck.getDeck().get(i-1).setKey(inventory.get(j-1));
        // deck.getDeck().get(i-1).setValue(inventory.get(j-1).getPlantingCooldown());
    }

    public void addPlantToDeck (Plant plant)
    {
        Pair<Plant, Integer> newPair = new Pair<Plant, Integer>(plant, plant.getPlantingCooldown());
        deck.getDeck().add(newPair);
    }
        
    public void switchPlacement (int index1, int index2) 
        throws InvalidInventorySwapException 
    {
        // Validate indices within inventory bounds
        if (index1 >= 0 && index1 < inventory.size() &&
            index2 >= 0 && index2 < inventory.size()) {
      
            Plant temp = inventory.get(index1);
            inventory.set(index1, inventory.get(index2));
            inventory.set(index2, temp);
        } else {
            System.out.println("Invalid placement indices!");
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
            System.out.println(i+1 + ". " + inventory.get(i).getClass().getSimpleName());
        }
        System.out.println();
      }

    // public void removeItem(Plant plant) throws RemoveNullException {
    //     if (plant == null) {
    //         throw new RemoveNullException("Cannot remove null from inventory.");
    //     }
    //     boolean removed = false;
    //     for (Plant slot : inventory) {
    //         if (plant.equals(slot.getItem())) {
    //             slot.setItem(null);
    //             removed = true;
    //             break;
    //         }
    //     }
    //     if (!removed) {
    //         System.out.println("Plant not found in the inventory.");
    //     }
    // }   

    //   public Plant getPlant(int index) {
    //     if (index >= 0 && index < inventory.size()) {
    //         Plant slot = inventory.get(index);
    //         return slot.getItem();
    //     } else {
    //         System.out.println("Invalid index!");
    //         return null;
    //     }
    // }
}


