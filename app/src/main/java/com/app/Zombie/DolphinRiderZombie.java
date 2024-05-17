package com.app.Zombie;

import com.app.AbstractClass.*;
import com.app.Game.Lawn;
import com.app.Interface.Vaultable;

public class DolphinRiderZombie extends Zombie implements Vaultable{
    private boolean isJump = false;
    public DolphinRiderZombie(int row, int col) {
        setName("Dolphin Rider Zombie");
        setHealth(175);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(true);
        setZombiePosition(row, col);
        Zombie.addZombieCount();
    }

    public void jumpOver(){
        System.out.println("gas lompat");
        if (Lawn.getLawn().get(row).get(col-2).hasPlant())
        {
            Lawn.getLawn().get(row).get(col-2).removePlant();
        }
        Lawn.getLawn().get(row).get(col - 2).getZombies().add(this);
        setZombiePosition(row, col-2);
        isJump = true;
    }

    public void action()
    {
        if (Lawn.getLawn().get(row).get(col-1).hasPlant())                      // CEK APAKAH DIDEPAN ZOMBIE ADA PLANT ATAU TIDAK    
        {
            if(!isJump)
            {
                jumpOver();
            }
            else
            {
                if (getFrozenTime() == 3 || getFrozenTime() == 1) 
                {
                    setCurrentMovementSpeed(getCurrentMovementSpeed() + 1); 
                    return;
                }
                Plant plant = Lawn.getLawn().get(row).get(col-1).getPlant();
                System.out.println("Health: " + plant.getHealth()); 
                plant.setHealth(plant.getHealth() - getAttackDamage());
                setCurrentMovementSpeed(getCurrentMovementSpeed() + 1);    
            }                        
        }
    }
}   
