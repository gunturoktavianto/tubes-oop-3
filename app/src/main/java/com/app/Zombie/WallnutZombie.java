package com.app.Zombie;

import com.app.AbstractClass.*;
import com.app.Game.Lawn;

public class WallnutZombie extends Zombie {
    
    public WallnutZombie(int row, int col) {
        setName("Wallnut Zombie");
        setHealth(600);
        setAttackDamage(20);
        setAttackSpeed(1);
        setIsAquatic(false);
        setZombiePosition(row, col);
        // Zombie.addZombieCount();
    }

    public void moveForward(){
        
    }

    public void action()
    {
        if (getHealth() > 0)
        {
            if (Lawn.getLawn().get(row).get(col).hasPlant())                      // CEK APAKAH DIDEPAN ZOMBIE ADA PLANT ATAU TIDAK    
            {
                if (getFrozenTime() == 3 || getFrozenTime() == 1) 
                {
                    setCurrentMovementSpeed(getCurrentMovementSpeed() + 1); 
                    return;
                }
                Plant plant = Lawn.getLawn().get(row).get(col).getPlant(); 
                plant.setHealth(plant.getHealth() - getAttackDamage());
                setCurrentMovementSpeed(getCurrentMovementSpeed() + 1);                           // MENGKOMPENSASI WAKTU ATTACK                         
            }
            else if (Lawn.getLawn().get(row).get(col-1).hasPlant())                      // CEK APAKAH DIDEPAN ZOMBIE ADA PLANT ATAU TIDAK    
            {
                if (getFrozenTime() == 3 || getFrozenTime() == 1) 
                {
                    setCurrentMovementSpeed(getCurrentMovementSpeed() + 1); 
                    return;
                }
                Plant plant = Lawn.getLawn().get(row).get(col-1).getPlant(); 
                plant.setHealth(plant.getHealth() - getAttackDamage());
                setCurrentMovementSpeed(getCurrentMovementSpeed() + 1);                           // MENGKOMPENSASI WAKTU ATTACK                         
            }
        }
    }
}   
