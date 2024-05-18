package com.app.Zombie;

import java.util.ArrayList;

import com.app.AbstractClass.*;
import com.app.Game.Lawn;

public class TuyulZombie extends Zombie{
    
    public TuyulZombie(int row, int col) {
        setName("Tuyul Zombie");
        setHealth(125);
        setAttackDamage(100);
        setAttackSpeed(1);
        setIsAquatic(false);
        setZombiePosition(row, col);
        Zombie.addZombieCount();
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
                if (getHealth() >= 50)
                {
                    p.setHealth(p.getHealth() - getAttackDamage());                                                 // RESET COOLDOWN
                }
                else
                {
                    p.setHealth(0);
                }
                setHealth(0);
                return;                                                         // LANGSUNG DI RETURN AGAR NEMBAK HANYA 1 TILE PALING DEPAN SAJA
            }                                                            
        }
        
    }

}   
