package main.Game;

import main.Plant.Peashooter;
import main.Plant.SnowPea;

public class inventester {
    public static void main(String[] args) {
        Inventory inven = new Inventory();
        inven.printInventory();
        System.out.println(inven.findPlantInDeck(new SnowPea()));
        // inven.getDeck().showDeck();
        inven.setPlantInDeck(2,2);
        // inven.getDeck().showDeck();
        System.out.println(inven.findPlantInDeck(new SnowPea()));
        // inven.setPlantInDeck(1,2);
        inven.setPlantInDeck(3,5);
        inven.getDeck().showDeckStatus();
        // inven.setPlantInDeck(4,7);
        // inven.setPlantInDeck(5,3);
        // inven.setPlantInDeck(6,4);
        // // inven.getDeck().showDeck();
        // inven.setPlantInDeck(4,1);
        // inven.setPlantInDeck(1,8);
        // inven.getDeck().showDeck();
        // inven.getDeck().showDeck();
       
    }
}
