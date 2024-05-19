package com.app.Zombie;

import com.app.AbstractClass.*;
import com.app.Game.Lawn;
import com.app.Interface.SpellCaster;

public class NewspaperZombie extends Zombie implements SpellCaster{
    
    public NewspaperZombie(int row, int col) {
        setName("Newspaper Zombie");
        setHealth(250);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(false);
        setZombiePosition(row, col);
        // Zombie.addZombieCount();
    }

    public void castSpell(){
        System.out.println("shing shing");
    }

    public void moveForward(){
        
    }

    public void action()
    {
        if(getHealth() < 125) {
            setMovementSpeed(5);
        }
    
        if (Lawn.getLawn().get(row).get(col).hasPlant())                      // CEK APAKAH DIDEPAN ZOMBIE ADA PLANT ATAU TIDAK    
        {
            if (getFrozenTime() == 3 || getFrozenTime() == 1) 
            {
                setCurrentMovementSpeed(getCurrentMovementSpeed() + 1); 
                return;
            }
            Plant plant = Lawn.getLawn().get(row).get(col).getPlant(); 
            plant.setHealth(plant.getHealth() - getAttackDamage());
            setCurrentMovementSpeed(getCurrentMovementSpeed() + 1);              // MENGKOMPENSASI WAKTU ATTACK                         
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
            setCurrentMovementSpeed(getCurrentMovementSpeed() + 1);              // MENGKOMPENSASI WAKTU ATTACK                         
        }
    }
}   
