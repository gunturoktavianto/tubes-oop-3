package Game;

import java.util.ArrayList;

import AbstractClass.Tile;
import AbstractClass.Zombie;
import Plant.*;
import Tile.*;

public class Lawn {
    private ArrayList<ArrayList<Tile>> lawn = new ArrayList<>();

    public Lawn() {
        initializeLawn();

        lawn.get(0).get(9).addZombie(lawn.get(0).get(9).spawnZombie());
        lawn.get(1).get(9).addZombie(lawn.get(1).get(9).spawnZombie());
        lawn.get(2).get(9).addZombie(lawn.get(2).get(9).spawnZombie());
        lawn.get(3).get(9).addZombie(lawn.get(3).get(9).spawnZombie());
        lawn.get(4).get(9).addZombie(lawn.get(4).get(9).spawnZombie());
        lawn.get(5).get(9).addZombie(lawn.get(5).get(9).spawnZombie());

        lawn.get(0).get(1).plant(new Peashooter());
        lawn.get(1).get(2).plant(new Peashooter());
        lawn.get(2).get(1).plant(new Lilypad());
        lawn.get(3).get(2).plant(new Lilypad());
        lawn.get(4).get(1).plant(new Peashooter());
        lawn.get(5).get(2).plant(new Peashooter());
    }

    public void initializeLawn() {
        for (int i = 0; i < 6; i++) {
            ArrayList<Tile> row = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                if (j == 0) { // THE HOUSE TILE
                    row.add(new HouseTile());
                } else {
                    if (i == 2 || i == 3) {
                        row.add(new WaterTile());
                    } else {
                        row.add(new GroundTile());
                    }
                }
            }
            lawn.add(row);
        }
    }

    public void moveForward() {
        for (int row = 0; row < 6; row++) {
            ArrayList<Tile> tileRow = lawn.get(row);
            if (tileRow.get(0).hasZombie()) {
                System.out.println("Zombies have reached the house! Game Over.");
                System.exit(0);
            }
        }

        // Moving zombies from right to left
        for (int row = 0; row < lawn.size(); row++) {
            ArrayList<Tile> tileRow = lawn.get(row);
            for (int col = 1; col < tileRow.size(); col++) {                    // Start from 1 to prevent array out of bounds
                if (tileRow.get(col).hasZombie()) {
                    ArrayList<Zombie> zombies = tileRow.get(col).getZombies();
                    for (Zombie z : zombies) {
                        if (z.getMovementSpeed() == 0) {                        // Cek dulu mana zombie yang movement_speednya udah 0 yang perlu dipindah
                            if (z.getIsFrozen()) {                              // Reset its movement_speed (move cooldown)
                                z.setMovementSpeed(10.0f);        // Check if its still frozen or not (changing its speed if its back to not frozen again NOT YET IMPLEMENTED)
                            } else {
                                z.setMovementSpeed(5.0f);         // Reset Normal
                            }
                            tileRow.get(col - 1).getZombies().add(z);           // Place each zombie to the next tile
                        } else if (z.getMovementSpeed() > 0) {                  // Need to decrease its movement_speed (as a cooldown)
                            z.setMovementSpeed(z.getMovementSpeed() - 0.5f);    // Decrease its movement speed
                        }
                    }
                }
            }
        }
    }

    public void printLawn() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    System.out.print("[ ]");
                } else {
                    if (lawn.get(i).get(j).hasZombie() && lawn.get(i).get(j).hasPlant()) // IF THERE IS A ZOMBIE AND PLANT IN A TILE
                        System.out.print((i == 2 || i == 3 ? "\u001B[34m" : "\u001B[32m") + "[X]" + "\u001B[0m");
                    else if (lawn.get(i).get(j).hasZombie()) // ONLY HAS A ZOMBIE IN A TILE
                        System.out.print((i == 2 || i == 3 ? "\u001B[34m" : "\u001B[32m") + "[Z]" + "\u001B[0m");
                    else if (lawn.get(i).get(j).hasPlant()) // ONLY HAS A PLANT IN A TILE
                        System.out.print((i == 2 || i == 3 ? "\u001B[34m" : "\u001B[32m") + "[P]" + "\u001B[0m");
                    else 
                        System.out.print((i == 2 || i == 3 ? "\u001B[34m" : "\u001B[32m") + "[ ]" + "\u001B[0m");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
