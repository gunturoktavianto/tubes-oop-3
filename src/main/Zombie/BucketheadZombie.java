package Zombie;

import AbstractClass.Zombie;

public class BucketheadZombie extends Zombie {
    
    public BucketheadZombie() {
        setName("Buckethead Zombie");
        setHealth(300);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(false);
        zombieCount ++;
    }
}   
