package Zombie;

import AbstractClass.Zombie;
import Interface.Vaultable;

public class DolphinRiderZombie extends Zombie implements Vaultable {
    
    public DolphinRiderZombie() {
        setName("Dolphin Rider Zombie");
        setHealth(175);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(true);
        zombieCount ++;
    }

    public void jumpOver(){
        System.out.println("gas lompat");
    }

    public void moveForward(){
        
    }

    public void action()
    {
        System.out.println("dolph");
    }
}   
