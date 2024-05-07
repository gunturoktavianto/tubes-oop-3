package Plant;
import AbstractClass.Plant;

public class Lilypad extends Plant{
    private Plant plantedPlant;
    // private String type;

    public Lilypad() {
        setName("Lilypad");
        setCost(25);
        setHealth(100);
        setAttackDamage(0);
        setAttackSpeed(0);
        setRange(0);
        setCooldown(10);
    }

    public Plant getPlant() {
        return plantedPlant;
    }

    // public String getType() {
    //     return type;
    // }

    public void plantOnTop(Plant plant) {
        this.plantedPlant = plant;
        // this.type = plant.getClass().getName();
    } 

    public boolean isOccupied() {
        return (plantedPlant != null);
    }

    public void shoot() {
        return;
    }
}
