package Lawn;

import AbstractClass.Plant;
import AbstractClass.Tile;

public class GroundTile extends Tile {
    public GroundTile() {
        setType("Ground");
    }

    public void plant(Plant plant) {
        setPlant(plant);
    }
}
