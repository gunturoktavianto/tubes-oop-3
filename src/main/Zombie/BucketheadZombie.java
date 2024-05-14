package Zombie;

import AbstractClass.Zombie;

public class BucketheadZombie extends Zombie {
    
    public BucketheadZombie(int row, int col) {
        setName("Buckethead Zombie");
        setHealth(300);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(false);
        setZombiePosition(row, col);
        zombieCount ++;
    }

    public void moveForward(){
        
    }

    public void action()
    {
        System.out.println("bucket");
    }
}   
