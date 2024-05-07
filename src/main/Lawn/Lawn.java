package Lawn;

import java.util.ArrayList;
import AbstractClass.Tile;
import Plant.Lilypad;
import Plant.Peashooter;
import Zombie.NormalZombie;

public class Lawn {
    private ArrayList<ArrayList<Tile>> lawn = new ArrayList<>();

    public Lawn() {
        initializeLawn();
        lawn.get(0).get(8).addZombie(new NormalZombie());
        lawn.get(1).get(8).addZombie(new NormalZombie());
        lawn.get(2).get(8).addZombie(new NormalZombie());
        lawn.get(3).get(8).addZombie(new NormalZombie());
        lawn.get(4).get(8).addZombie(new NormalZombie());
        lawn.get(5).get(8).addZombie(new NormalZombie());

        lawn.get(0).get(0).plant(new Peashooter());
        lawn.get(1).get(1).plant(new Peashooter());
        lawn.get(2).get(0).plant(new Lilypad());
        lawn.get(3).get(1).plant(new Lilypad());
        lawn.get(4).get(0).plant(new Peashooter());
        lawn.get(5).get(1).plant(new Peashooter());
    }

    public void initializeLawn() {
        for (int i = 0; i < 6; i++) {
            ArrayList<Tile> row = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                if (i == 2 || i == 3) {
                    row.add(new WaterTile());
                } else {
                    row.add(new GroundTile());
                }
            }
            lawn.add(row);
        }
    }

    public void printLawn() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                if (lawn.get(i).get(j).hasZombie() && lawn.get(i).get(j).hasPlant()) // IF THERE IS A ZOMBIE AND PLANT IN A TILE
                    System.out.print((i == 2 || i == 3 ? "\u001B[34m" : "\u001B[32m") + "[X]" + "\u001B[0m");
                else if (lawn.get(i).get(j).hasZombie()) // ONLY HAS A ZOMBIE IN A TILE
                    System.out.print((i == 2 || i == 3 ? "\u001B[34m" : "\u001B[32m") + "[Z]" + "\u001B[0m");
                else if (lawn.get(i).get(j).hasPlant()) // ONLY HAS A PLANT IN A TILE
                    System.out.print((i == 2 || i == 3 ? "\u001B[34m" : "\u001B[32m") + "[P]" + "\u001B[0m");
                else 
                    System.out.print((i == 2 || i == 3 ? "\u001B[34m" : "\u001B[32m") + "[ ]" + "\u001B[0m");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
