package Plant;
import java.util.ArrayList;

import AbstractClass.Plant;
import AbstractClass.Tile;
import Interface.Action;

public class Peashooter extends Plant implements Action{
    
    public Peashooter(int row, int col) {
        setName("Peashooter");
        setCost(100);
        setHealth(100);
        setAttackDamage(25);
        setAttackSpeed(4);
        setAttackCooldown(getAttackSpeed());
        setRange(-1);
        setPlantPosition(row, col);
    }

    public void action() {
        System.out.println("peashooter");
    }
}
