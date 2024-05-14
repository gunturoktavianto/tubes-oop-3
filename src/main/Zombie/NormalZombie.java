package Zombie;

import AbstractClass.Zombie;

public class NormalZombie extends Zombie {
    
    public NormalZombie(int row, int col) {
        setName("Normal Zombie");
        setHealth(125);
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
        System.out.println("normal");
    }
}   
