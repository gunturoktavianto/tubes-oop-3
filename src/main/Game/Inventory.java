package Game;

import java.lang.String;
import java.util.ArrayList;
<<<<<<< Updated upstream

import AbstractClass.Plant;
import Interface.Stockable;
import Plant.*;

public class Inventory implements Stockable {
    private ArrayList<Plant> inventory;

    public Inventory() {
        this.inventory = new ArrayList<Plant>();
        addItem(new Peashooter(0,0)); 
=======
import javax.crypto.spec.DESKeySpec;
import main.Pair;
import main.AbstractClass.Plant;
import main.Exception.IndexOutOfRangeException;
import main.Exception.InvalidInventorySwapException;
import main.Exception.RemoveNullException;
import main.Interface.Stockable;
import main.Plant.*;

public class Inventory{
    private ArrayList<Plant> inventory;
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
>>>>>>> Stashed changes
        // INITILIZE WITH OTHER PLANTS 
    }

    public void addItem(Plant plant) {
        inventory.add(plant);
<<<<<<< Updated upstream
    }

    public void removeItem(Plant plant) {
        inventory.remove(plant);
    }   
=======
    }
    
    public ArrayList<Plant> getInventory(){
        return inventory;
    }

    public Deck getDeck(){
        return deck;
    }

    public void swapDeck (int x, int y){
        Pair<Plant, Integer> temp = deck.getDeck().get(x);
        deck.getDeck().set(x, deck.getDeck().get(y));
        deck.getDeck().set(y, temp);
    }

    public void deletePlantFromDeck (int index){
        if (index < 0 || index >= deck.getDeck().size()) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        else if (deck.getDeck().isEmpty()){
            throw new IllegalArgumentException("Deck is empty");
        }
        else if (deck.getDeck().get(index).getKey() == null){
            throw new IllegalArgumentException("No plant in deck");
        }
        System.out.println(deck.getDeck().get(index).getKey() + " has been deleted from deck");
        deck.getDeck().remove(index);
        
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

    public void addPlantToDeck (int i){
        if (i > 6) {
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

    public void setPlantInDeck (int i, int j){ // i buat deck , j buat inventory
        if (i > 6) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        // else if (deck.getDeck().size() >= 6){
        //     throw new IllegalArgumentException("Deck is full");
        // }
        else if (findPlantInDeck(inventory.get(j-1))){
            throw new IllegalArgumentException("Plant already in deck");
        }
        System.out.println(inventory.get(j-1).getName() + " has been added to deck");
        deck.showDeck();
        Pair<Plant, Integer> newPair = new Pair<Plant,Integer>(inventory.get(j-1), 0);
        deck.getDeck().set(i-1, newPair);
        // deck.getDeck().get(i-1).setKey(inventory.get(j-1));
        // deck.getDeck().get(i-1).setValue(inventory.get(j-1).getPlantingCooldown());
    }

    public void addPlantToDeck (Plant plant){
        Pair<Plant, Integer> newPair = new Pair<Plant, Integer>(plant, plant.getPlantingCooldown());
        deck.getDeck().add(newPair);
    }
    

>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
=======
        if (index1 == index2 || (inventory.get(index1) == null && inventory.get(index2) == null)) {
            throw new InvalidInventorySwapException("Invalid swap operation.");
        }
        Plant temp = inventory.get(index1);
        inventory.set(index1, inventory.get(index2));
        inventory.set(index2, temp);
            
        
>>>>>>> Stashed changes
    }
    
    public void printInventory() {
        System.out.println("Inventory:");
        for (int i = 0; i < inventory.size(); i++) {
<<<<<<< Updated upstream
            Plant plant = inventory.get(i);
            System.out.println("  - " + plant.getName() + " (Index: " + i + ")");
=======
            System.out.println(i+1 + ". " + inventory.get(i).getClass().getSimpleName());
>>>>>>> Stashed changes
        }
        System.out.println();
      }
<<<<<<< Updated upstream
=======

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
>>>>>>> Stashed changes
}


