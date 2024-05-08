package Tile;

import java.util.Random;

import AbstractClass.*;
import Plant.Lilypad;
import Zombie.DolphinRiderZombie;
import Zombie.DuckyTubeZombie;

public class WaterTile extends Tile {
    public WaterTile() {
        setType("Water");
    }
    public boolean hasLilypad() {
        if (getPlant() != null)
            return (getPlant().getName().equals("Lilypad"));
        return false;
    }

    public void plant(Plant plant) {
        if (getPlant() == null && plant.getName().equals("Lilypad"))
            setPlant(new Lilypad());
        else if (hasLilypad())
            setPlant(plant);
        else
            System.out.println("\u001B[31m" + "TIDAK BISA MENARUH TANAMAN" + "\u001B[0m");
    }

    @Override
    public Zombie spawnZombie() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            return new DolphinRiderZombie();
        } else {
            return new DuckyTubeZombie();
        }
    }
}
