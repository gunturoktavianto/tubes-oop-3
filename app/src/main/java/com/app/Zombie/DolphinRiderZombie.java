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

    public void jumpOver(int place)
    {
        if (place == 0)
        {
            Lawn.getLawn().get(row).get(col).removePlant();
        }
        else if (place == 1)
        {
            if (Lawn.getLawn().get(row).get(col-2).hasPlant())
            {
                Lawn.getLawn().get(row).get(col-2).removePlant();
            }
            Lawn.getLawn().get(row).get(col - 2).getZombies().add(this);
            setZombiePosition(row, col-2);
            isJump = true;
        }
    }

    public void action()
    {
        if (Lawn.getLawn().get(row).get(col).hasPlant())                      // CEK APAKAH DIDEPAN ZOMBIE ADA PLANT ATAU TIDAK    
        {
            if(!isJump)
            {
                jumpOver(0);
            }
            else
            {
                if (getFrozenTime() == 3 || getFrozenTime() == 1) 
                {
                    setCurrentMovementSpeed(getCurrentMovementSpeed() + 1); 
                    return;
                }
                Plant plant = Lawn.getLawn().get(row).get(col).getPlant(); 
                plant.setHealth(plant.getHealth() - getAttackDamage());
                setCurrentMovementSpeed(getCurrentMovementSpeed() + 1);                       // MENGKOMPENSASI WAKTU ATTACK  
            }                       
        }
        else if (Lawn.getLawn().get(row).get(col-1).hasPlant())                      // CEK APAKAH DIDEPAN ZOMBIE ADA PLANT ATAU TIDAK    
        {
            if(!isJump)
            {
                jumpOver(1);
            }
            else
            {
                if (getFrozenTime() == 3 || getFrozenTime() == 1) 
                {
                    setCurrentMovementSpeed(getCurrentMovementSpeed() + 1); 
                    return;
                }
                Plant plant = Lawn.getLawn().get(row).get(col-1).getPlant(); 
                plant.setHealth(plant.getHealth() - getAttackDamage());
                setCurrentMovementSpeed(getCurrentMovementSpeed() + 1);                       // MENGKOMPENSASI WAKTU ATTACK  
            }                       
        }
    }
}   
