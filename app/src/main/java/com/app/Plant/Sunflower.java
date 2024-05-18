package com.app.Plant;
import com.app.AbstractClass.Plant;
import com.app.Game.Sun;
import com.app.Interface.Action;

public class Sunflower extends Plant implements Action {
    
    public Sunflower() {
        setName("Sunflower");
        setCost(50);
        setHealth(100);
        setAttackSpeed(3); // attack speed dsnai waktu dia produce sunnya
        setAttackCooldown(3);
        setPlantPosition(row, col);
    }

    public void action() {
        if (getAttackCooldown() > 0)                                                 // TIDAK PERLU MELAKUKAN SHOOT
        {
            setAttackCooldown(getAttackCooldown() - 1);                         // MENGURANGI COOLDOWN
            return;
        }
        else
        {   
            Sun.setSun(Sun.getSun() + 25);
            setAttackCooldown(getAttackSpeed());                            // RESET COOLDOWN
            return; 
        }
        
    }
}
