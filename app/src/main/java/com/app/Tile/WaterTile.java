package com.app.Tile;

import java.util.Random;

import com.app.AbstractClass.*;
import com.app.Zombie.DolphinRiderZombie;
import com.app.Zombie.DuckyTubeZombie;
import com.app.GameCLI;

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

    public void spawnZombie() {
        if ((GameCLI.getPassedTime() > 80 && GameCLI.getPassedTime() < 100) || GameCLI.getPassedTime() > 180)
        {
            if (Zombie.getZombieCount() > 24)
            {
                return;
            }
        }
        else
        {
            if (Zombie.getZombieCount() > 9)
            {
                return;
            } 
        }
        Random rand = new Random();
        if (rand.nextBoolean()) {
            addZombie(new DolphinRiderZombie(getTileRow(), getTileCol()));
        } else {
            addZombie(new DuckyTubeZombie(getTileRow(), getTileCol()));
        }
    }
}
