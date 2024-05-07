package Zombie;

import AbstractClass.Zombie;
import Interface.SpellCaster;

public class StitcherZombie extends Zombie implements SpellCaster{
    
    public StitcherZombie() {
        setName("Stitcher Zombie");
        setHealth(10000);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(false);
        zombieCount ++;
    }

    public void castSpell(){
        System.out.println("shing shing");
    }
}   
