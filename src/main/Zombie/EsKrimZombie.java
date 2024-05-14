package Zombie;

import AbstractClass.Zombie;
import Interface.SpellCaster;

public class EsKrimZombie extends Zombie implements SpellCaster{
    
    public EsKrimZombie(int row, int col) {
        setName("Es Krim Zombie");
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
        System.out.println("eskrim");
    }
}   
