package Zombie;

import AbstractClass.Zombie;

public class DoorZombie extends Zombie {
    
    public DoorZombie() {
        setName("Door Zombie");
        setHealth(300);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(false);
        zombieCount ++;
    }
}   
