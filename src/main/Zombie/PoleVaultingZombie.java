package Zombie;

import AbstractClass.Plant;
import AbstractClass.Zombie;
import Game.Lawn;
import Interface.Vaultable;

public class PoleVaultingZombie extends Zombie implements Vaultable {
    private boolean isJump = false;
    public PoleVaultingZombie(int row, int col) {
        setName("Pole Vaulting Zombie");
        setHealth(175);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(false);
        setZombiePosition(row, col);
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
            if(!isJump)
            {
                if (Lawn.getLawn().get(row).get(col-2).hasPlant())
                {
                    Lawn.getLawn().get(row).get(col-2).removePlant();
                }
                Lawn.getLawn().get(row).get(col).removeZombie(this);
                setZombiePosition(row, col-2);
                Lawn.getLawn().get(row).get(col-2).addZombie(this);
                isJump = true;
            }
            else
            {
                Plant plant = Lawn.getLawn().get(row).get(col-1).getPlant(); 
                plant.setHealth(plant.getHealth() - getAttackDamage());
                System.out.println("NYAM!!! DARAH PLANT: " + plant.getHealth());
                setMovementSpeed(getMovementSpeed() + 1);    
            }
                                   // MENGKOMPENSASI WAKTU ATTACK                         
        }
    }
}   
