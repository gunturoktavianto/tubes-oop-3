package com.app.Tile;

import java.util.Random;

import com.app.AbstractClass.*;
import com.app.Zombie.*;
import com.app.GameCLI;

public class GroundTile extends Tile {
    public GroundTile(int row, int col) {
        setType("Ground");
        setTilePosition(row, col);
    }

    public void spawnZombie() {
        if ((GameCLI.getPassedTime() > 80 && GameCLI.getPassedTime() < 100))
        {
            if (Zombie.getZombieCount() > 24)
            {
                return;
            }
        }
        else if((GameCLI.getPassedTime() > 20 && GameCLI.getPassedTime() < 30))
        {
            if (Zombie.getZombieCount() > 2)
            {
                return;
            } 
        }
        else if((GameCLI.getPassedTime() > 30 && GameCLI.getPassedTime() < 50))
        {
            if (Zombie.getZombieCount() > 5)
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
        switch (rand.nextInt(8)) {
            case 0:
                addZombie(new PoleVaultingZombie(getTileRow(), getTileCol()));
                break;
            case 1:
                addZombie(new BucketheadZombie(getTileRow(), getTileCol()));
                break;
            case 2:
                addZombie(new TerminatorZombie(getTileRow(), getTileCol()));
                break;
            case 3:
                addZombie(new ConeheadZombie(getTileRow(), getTileCol()));
                break;
            case 4:
                addZombie(new NormalZombie(getTileRow(), getTileCol()));
                break;
            case 5:
                addZombie(new NewspaperZombie(getTileRow(), getTileCol()));
                break;
            case 6:
                addZombie(new FootballZombie(getTileRow(), getTileCol()));
                break;
            case 7:
                addZombie(new WallnutZombie(getTileRow(), getTileCol()));
                break;
            default:
                return;
        }
    }
}
