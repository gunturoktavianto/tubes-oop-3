package Zombie;

import AbstractClass.Zombie;
import Interface.Vaultable;

public class PoleVaultingZombie extends Zombie implements Vaultable {
    
    public PoleVaultingZombie() {
        setName("Pole Vaulting Zombie");
        setHealth(175);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(false);
        zombieCount ++;
    }

    public void jumpOver(){
        System.out.println("gas lompat");
    }

    public void moveForward(){
        
    }
}   
