package AbstractClass;

import java.util.ArrayList;

public abstract class Tile {
    private String type;
    private Plant plant;
    private ArrayList<Zombie> zombies = new ArrayList<Zombie>();

    public abstract void plant(Plant plant);

    public Plant getPlant() {
        return plant;
    }

    public boolean hasPlant() {
        return (plant != null);
    }

    public void setPlant(Plant plant) { // DO NOT INVOKE THIS METHOD, ONLY FOR PLANTING USE ONLY
        this.plant = plant;
    }

    public boolean hasZombie() {
        return (!zombies.isEmpty());
    }

    public void addZombie(Zombie zombie) {
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
