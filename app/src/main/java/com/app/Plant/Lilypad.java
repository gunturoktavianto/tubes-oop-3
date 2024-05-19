package com.app.Plant;
import com.app.AbstractClass.Plant;

public class Lilypad extends Plant{
    private Plant plantedPlant;

    public Lilypad() {
        setName("Lilypad");
        setCost(25);
        setHealth(100);
        setAttackDamage(0);
        setAttackSpeed(0);
        setAttackCooldown((plantedPlant == null ? 9999 : 0));
        setRange(0);
        setPlantPosition(row, col);
        setAquatic(true);
        setPlantingCooldown(10);
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
