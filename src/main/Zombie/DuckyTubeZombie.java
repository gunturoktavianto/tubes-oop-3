package Zombie;

import AbstractClass.Zombie;

public class DuckyTubeZombie extends Zombie {
    
    public DuckyTubeZombie() {
        setName("Ducky Tube Zombie");
        setHealth(100);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(true);
        zombieCount ++;
    }

    public void moveForward(){
        
    }
}   
