package Lawn;

import AbstractClass.Plant;
import AbstractClass.Tile;
import Plant.Lilypad;

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
}
