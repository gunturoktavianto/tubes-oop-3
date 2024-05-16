package main.Zombie;

import main.AbstractClass.*;
import main.Game.Lawn;

public class NormalZombie extends Zombie {
    
    public NormalZombie(int row, int col) {
        setName("Normal Zombie");
        setHealth(125);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(false);
        setZombiePosition(row, col);
        zombieCount ++;
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
                System.out.println("NYAM!!! DARAH PLANT: " + plant.getHealth());
                setCurrentMovementSpeed(getCurrentMovementSpeed() + 1);                           // MENGKOMPENSASI WAKTU ATTACK                         
            }
        }
    }
}   
