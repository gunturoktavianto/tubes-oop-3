package Plant;

import AbstractClass.Plant;
import Interface.Action;

public class Peashooter extends Plant implements Action{
    
    public Peashooter() {
        setName("Peashooter");
        setCost(100);
        setHealth(100);
        setAttackDamage(25);
        setAttackSpeed(4);
        setAttackCooldown(getAttackSpeed());
        setRange(-1);
        setPlantPosition(row, col);
        setPlantingCooldown(10);
    }

    public void action() {
        System.out.println("peashooter");
    }
}
