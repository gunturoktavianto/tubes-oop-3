package com.app.Plant;
import com.app.AbstractClass.Plant;

public class Lilypad extends Plant{
    private Plant plantedPlant;

    public Lilypad(int row, int col) {
        setName("Lilypad");
        setCost(25);
        setHealth(100);
        setAttackDamage(0);
        setAttackSpeed(0);
        setAttackCooldown(99999);
        setRange(0);
        setPlantPosition(row, col);
        setAquatic(true);
    }

    public Plant getPlantedPlant() {
        return plantedPlant;
    }

    public void plantOnTop(Plant plant) {
        this.plantedPlant = plant;
        setHealth(getHealth() + plantedPlant.getHealth());
    } 

    public boolean isOccupied() {
        return (plantedPlant != null);
    }

    public void action() {
        if (plantedPlant !=  null)
            plantedPlant.action();
    }
}
