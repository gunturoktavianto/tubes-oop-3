package Zombie;

import AbstractClass.Zombie;

public class DoorZombie extends Zombie {
    
    public DoorZombie(int row, int col) {
        setName("Door Zombie");
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
        System.out.println("door");
    }
}   
