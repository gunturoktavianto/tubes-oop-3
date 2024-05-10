package Zombie;

import AbstractClass.Zombie;

public class ConeheadZombie extends Zombie {
    
    public ConeheadZombie() {
        setName("Conehead Zombie");
        setHealth(250);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(false);
        zombieCount ++;
    }

    public void moveForward(){
        
    }

    public void action()
    {
        System.out.println("cone");
    }
}   
