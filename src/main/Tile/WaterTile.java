package main.Tile;

import java.util.Random;

import main.AbstractClass.*;
import main.Plant.Lilypad;
import main.Zombie.DolphinRiderZombie;
import main.Zombie.DuckyTubeZombie;

public class WaterTile extends Tile {
    public WaterTile(int row, int col) {
        setType("Water");
        setTilePosition(row, col);
    }
    
    public boolean hasLilypad() {
        if (getPlant() != null)
            return (getPlant().getName().equals("Lilypad"));
        return false;
    }

    // public void plant(Plant plant) {
    //     if (getPlant() == null && plant.getName().equals("Lilypad"))
    //         setPlant(new Lilypad(999, 999));
    //     else if (hasLilypad())
    //         setPlant(plant);
    //     else
    //         System.out.println("\u001B[31m" + "TIDAK BISA MENARUH TANAMAN" + "\u001B[0m");
    // }

    @Override
    public Zombie spawnZombie() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            return new DolphinRiderZombie(getTileRow(), getTileCol());
        } else {
            return new DuckyTubeZombie(getTileRow(), getTileCol());
        }
    }
}
