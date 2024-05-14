package Zombie;

import AbstractClass.Zombie;
import Interface.SpellCaster;

public class StitcherZombie extends Zombie implements SpellCaster{
    
    public StitcherZombie(int row, int col) {
        setName("Stitcher Zombie");
        setHealth(10000);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(false);
        setZombiePosition(row, col);
        zombieCount ++;
    }

    public void castSpell(){
        System.out.println("shing shing");
    }

    public void moveForward(){
        
    }

    public void action()
    {
        System.out.println("stit");
    }
}   
