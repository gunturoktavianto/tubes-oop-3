package Zombie;

import java.util.ArrayList;

import AbstractClass.Plant;
import AbstractClass.Tile;
import AbstractClass.Zombie;
import Game.Lawn;

public class TerminatorZombie extends Zombie {
    
    public TerminatorZombie(int row, int col) {
        setName("Terminator Zombie");
        setHealth(125);
        setAttackDamage(20);
        setAttackSpeed(3);
        setIsAquatic(false);
        setZombiePosition(row, col);
        zombieCount ++;
    }

    public void moveForward(){
        
    }

    public void action()
    {
        ArrayList<Tile> tileRow = Lawn.getLawn().get(row);
        for (int i=col; i>0; i--)                                  // JIKA PLANT BARU DITANAM DAN ADA ZOMBIE DI TILE TERSEBUT, 
        {                                                                       // AKAN LANGSUNG NEMBAK DI TILE TERSEBUT
            if (tileRow.get(i).hasPlant())
            {
                System.out.println("DOR!!! plant di tile" + i);
                Plant p = tileRow.get(i).getPlant();
                p.setHealth(p.getHealth() - getAttackDamage());                           // RESET COOLDOWN
                return;                                                         // LANGSUNG DI RETURN AGAR NEMBAK HANYA 1 TILE PALING DEPAN SAJA
            }                                                            
        }
    }
}   
