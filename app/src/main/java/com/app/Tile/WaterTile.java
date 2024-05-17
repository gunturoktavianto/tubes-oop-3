package com.app.Tile;

import java.util.Random;

import com.app.AbstractClass.*;
import com.app.Zombie.DolphinRiderZombie;
import com.app.Zombie.DuckyTubeZombie;

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

    public void spawnZombie() {
        if (Zombie.getZombieCount() > 10)
        {
            return;
        }
        Random rand = new Random();
        if (rand.nextBoolean()) {
            addZombie(new DolphinRiderZombie(getTileRow(), getTileCol()));
        } else {
            addZombie(new DuckyTubeZombie(getTileRow(), getTileCol()));
        }
    }
}
