package Zombie;

import AbstractClass.Plant;
import AbstractClass.Zombie;
import Game.Lawn;
import Interface.SpellCaster;

public class NewspaperZombie extends Zombie implements SpellCaster{
    
    public NewspaperZombie(int row, int col) {
        setName("Newspaper Zombie");
        setHealth(250);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(false);
        setZombiePosition(row, col);
        zombieCount ++;
    }

    public void castSpell(){
        System.out.println("shing shing");
    }

    public void moveForward(){
        
    }

    public void action()
    {
        if(getHealth() < 125) {
            System.out.println("im angry");
            setMovementSpeed(3);
        }

        
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
                                      // MENGKOMPENSASI WAKTU ATTACK                         
        }
    }
}   
