package Zombie;

import AbstractClass.Plant;
import AbstractClass.Zombie;
import Game.Lawn;
import Interface.Vaultable;

public class FootballZombie extends Zombie implements Vaultable {
    
    public FootballZombie(int row, int col) {
        setName("Football Zombie");
        setHealth(600);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(true);
        setZombiePosition(row, col);
        setMovementSpeed(3);
        setCurrentMovementSpeed(3);
        zombieCount ++;
    }

    public void jumpOver(){
        System.out.println("gas lompat");
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
