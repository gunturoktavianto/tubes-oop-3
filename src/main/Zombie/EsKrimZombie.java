package Zombie;

import AbstractClass.Zombie;
import Interface.SpellCaster;

public class EsKrimZombie extends Zombie implements SpellCaster{
    
    public EsKrimZombie() {
        setName("Es Krim Zombie");
        setHealth(10000);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(false);
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
