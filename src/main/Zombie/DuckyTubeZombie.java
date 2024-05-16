package Zombie;

import AbstractClass.Plant;
import AbstractClass.Zombie;
import Game.Lawn;

public class DuckyTubeZombie extends Zombie {
    
    public DuckyTubeZombie(int row, int col) {
        setName("Ducky Tube Zombie");
        setHealth(100);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(true);
        setZombiePosition(row, col);
        zombieCount ++;
    }

    public void moveForward(){
        
    }

    public void action()
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
