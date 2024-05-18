package com.app.Zombie;

import com.app.AbstractClass.*;
import com.app.Game.Lawn;

public class WalnutZombie extends Zombie {
    
    public WalnutZombie(int row, int col) {
        setName("WalnutZombie");
        setHealth(600);
        setAttackDamage(50);
        setAttackSpeed(1);
        setIsAquatic(false);
        setZombiePosition(row, col);
        Zombie.addZombieCount();
    }

    public void moveForward(){
        
    }

    public void action()
    {
        if (getHealth() > 0)
        {
            if (Lawn.getLawn().get(row).get(col-1).hasPlant())                      // CEK APAKAH DIDEPAN ZOMBIE ADA PLANT ATAU TIDAK    
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
