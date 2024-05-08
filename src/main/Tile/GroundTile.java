package Tile;

import java.util.Random;

import AbstractClass.*;
import AbstractClass.Tile;
import Zombie.*;

public class GroundTile extends Tile {
    public GroundTile() {
        setType("Ground");
    }

    public void plant(Plant plant) {
        setPlant(plant);
    }

    @Override
    public Zombie spawnZombie() {
        Random rand = new Random();
        switch (rand.nextInt(8)) {
            case 0:
                return new PoleVaultingZombie();
            case 1:
                return new BucketheadZombie();
            case 2:
                return new DoorZombie();
            case 3:
                return new ConeheadZombie();
            case 4:
                return new NormalZombie();
            case 5:
                return new StitcherZombie();
            case 6:
                return new EsKrimZombie();
            case 7:
                return new TuyulZombie();
            default:
                return null;
        }
    }
}
