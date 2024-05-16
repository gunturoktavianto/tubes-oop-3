package main.Tile;

import main.AbstractClass.*;

public class HouseTile extends Tile {
    public HouseTile() {
        setType("House");
    }

    public void plant(Plant plant) {
        return;
    }

    @Override
    public Zombie spawnZombie() {
        return null;
    }
}
