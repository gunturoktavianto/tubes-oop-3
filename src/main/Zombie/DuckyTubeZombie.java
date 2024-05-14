package Zombie;

import AbstractClass.Zombie;

public class DuckyTubeZombie extends Zombie {
    
    public DuckyTubeZombie(int row, int col) {
        setName("Ducky Tube Zombie");
        setHealth(100);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(true);
        setZombiePosition(row, col);
        zombieCount ++;
    }

    public void moveForward(){
        
    }

    public void action()
    {
        System.out.println("duck");
    }
}   
