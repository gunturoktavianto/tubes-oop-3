package com.app.Tile;

import java.util.Random;

import com.app.AbstractClass.*;
import com.app.Zombie.*;

public class GroundTile extends Tile {
    public GroundTile(int row, int col) {
        setType("Ground");
        setTilePosition(row, col);
    }

    public void spawnZombie() {
        if (Zombie.getZombieCount() > 10)
        {
            return;
        }
        Random rand = new Random();
        switch (rand.nextInt(8)) {
            case 0:
                addZombie(new PoleVaultingZombie(getTileRow(), getTileCol()));
            case 1:
                addZombie(new BucketheadZombie(getTileRow(), getTileCol()));
            case 2:
                addZombie(new TerminatorZombie(getTileRow(), getTileCol()));
            case 3:
                addZombie(new ConeheadZombie(getTileRow(), getTileCol()));
            case 4:
                addZombie(new NormalZombie(getTileRow(), getTileCol()));
            case 5:
                addZombie(new NewspaperZombie(getTileRow(), getTileCol()));
            case 6:
                addZombie(new FootballZombie(getTileRow(), getTileCol()));
            case 7:
                addZombie(new TuyulZombie(getTileRow(), getTileCol()));
            default:
                return;
        }
    }
}
