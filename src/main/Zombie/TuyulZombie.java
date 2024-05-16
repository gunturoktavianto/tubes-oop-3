package Zombie;

import java.util.ArrayList;

import AbstractClass.Plant;
import AbstractClass.Tile;
import AbstractClass.Zombie;
import Game.Lawn;
import Interface.SpellCaster;

public class TuyulZombie extends Zombie implements SpellCaster{
    
    public TuyulZombie(int row, int col) {
        setName("Tuyul Zombie");
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
        ArrayList<Tile> tileRow = Lawn.getLawn().get(row);
        for (int i=1; i<tileRow.size(); i++)                                    // JIKA PLANT BARU DITANAM DAN ADA ZOMBIE DI TILE TERSEBUT, 
        {                                                                       // AKAN LANGSUNG NEMBAK DI TILE TERSEBUT
            if (tileRow.get(i).hasPlant())
            {
                System.out.println("DOR!!! plant di tile" + i);
                Plant p = tileRow.get(i).getPlant();
                p.setHealth(p.getHealth() - p.getHealth());                           // RESET COOLDOWN
                tileRow.get(col).removeZombie(this);
                return;                                                         // LANGSUNG DI RETURN AGAR NEMBAK HANYA 1 TILE PALING DEPAN SAJA
            }                                                            
        }
        
    }

}   
