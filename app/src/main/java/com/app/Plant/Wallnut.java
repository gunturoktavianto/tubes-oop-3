package com.app.Plant;
import com.app.AbstractClass.Plant;

public class Wallnut extends Plant {
    
    public Wallnut(int row, int col) {
        setName("Wallnut");
        setCost(50);
        setHealth(1000);
        setAttackDamage(0);
        setAttackSpeed(0);
        setAttackCooldown(99999);
        setRange(0);
        setPlantPosition(row, col);
    }
    public void action() {

    }
}