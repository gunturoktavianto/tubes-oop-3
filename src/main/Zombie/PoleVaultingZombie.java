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
        if (Lawn.getLawn().get(row).get(col-2).hasPlant())
        {
            Lawn.getLawn().get(row).get(col-2).removePlant();
        }
        Lawn.getLawn().get(row).get(col - 2).getZombies().add(this);
        // Lawn.getLawn().get(row).get(col).getZombies().remove(this);
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
                plant.setHealth(plant.getHealth() - getAttackDamage());
                System.out.println("NYAM!!! DARAH PLANT: " + plant.getHealth());
                setCurrentMovementSpeed(getCurrentMovementSpeed() + 1);                       // MENGKOMPENSASI WAKTU ATTACK  
                System.out.println(getCurrentMovementSpeed());
            }                       
        }
    }
}   
