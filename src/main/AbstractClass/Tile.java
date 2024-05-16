package main.AbstractClass;

import java.util.ArrayList;
import java.util.Random;

public abstract class Tile {
    private String type;
    private Plant plant = null;
    private ArrayList<Zombie> zombies = new ArrayList<Zombie>();
    private int row, col;

    public abstract Zombie spawnZombie();

    public int getTileRow() {
        return this.row;
    }

    public int getTileCol() {
        return this.col;
    }

    public void setTilePosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Plant getPlant() {
        return plant;
    }

    public boolean hasPlant() {
        return (plant != null);
    }

    public void setPlant(Plant plant) {                                         // DO NOT INVOKE THIS METHOD, ONLY FOR PLANTING USE ONLY
        this.plant = plant;
    }

    public void removePlant()
    {
        System.out.println("remove");
        this.plant = null;
    }

    public boolean hasZombie() {
        return (!zombies.isEmpty());
    }

    public ArrayList<Zombie> getZombies() {
        return this.zombies;
    }

    public void addZombie(Zombie zombie) {
        if (new Random().nextDouble() < 0.3)
            zombies.add(zombie);
    }

    public void removeZombie(Zombie zombie) {
        zombies.remove(zombie);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
}
