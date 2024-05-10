package Zombie;

import AbstractClass.Zombie;

public class NormalZombie extends Zombie {
    
    public NormalZombie() {
        setName("Normal Zombie");
        setHealth(125);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(false);
        zombieCount ++;
    }

    public void moveForward(){
        
    }

    public void action()
    {
        System.out.println("normal");
    }
}   
