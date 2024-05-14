package Tile;

import java.util.Random;

import AbstractClass.*;
import AbstractClass.Tile;
import Zombie.*;

public class GroundTile extends Tile {
    public GroundTile(int row, int col) {
        setType("Ground");
        setTilePosition(row, col);
    }

    public void plant(Plant plant) {
        setPlant(plant);
    }

    @Override
    public Zombie spawnZombie() {
        Random rand = new Random();
        switch (rand.nextInt(8)) {
            case 0:
                return new PoleVaultingZombie(getTileRow(), getTileCol());
            case 1:
                return new BucketheadZombie(getTileRow(), getTileCol());
            case 2:
                return new DoorZombie(getTileRow(), getTileCol());
            case 3:
                return new ConeheadZombie(getTileRow(), getTileCol());
            case 4:
                return new NormalZombie(getTileRow(), getTileCol());
            case 5:
                return new StitcherZombie(getTileRow(), getTileCol());
            case 6:
                return new EsKrimZombie(getTileRow(), getTileCol());
            case 7:
                return new TuyulZombie(getTileRow(), getTileCol());
            default:
                return null;
        }
    }
}
