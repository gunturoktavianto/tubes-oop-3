package com.app.Zombie;

import java.util.ArrayList;

import com.app.AbstractClass.*;
import com.app.Game.Lawn;

public class TerminatorZombie extends Zombie {
    
    public TerminatorZombie(int row, int col) {
        setName("Terminator Zombie");
        setHealth(125);
        setAttackDamage(5);
        setAttackSpeed(3);
        setIsAquatic(false);
        setZombiePosition(row, col);
        Zombie.addZombieCount();
    }

    public void moveForward(){
        
    }

    public void action()
    {
        ArrayList<Tile> tileRow = Lawn.getLawn().get(row);
        if (Lawn.getLawn().get(row).get(col).hasPlant() || Lawn.getLawn().get(row).get(col-1).hasPlant())                      // CEK APAKAH DIDEPAN ZOMBIE ADA PLANT ATAU TIDAK    
        {
            setCurrentMovementSpeed(getCurrentMovementSpeed() + 1); 
        }
        for (int i=col; i>0; i--)                                               // JIKA PLANT BARU DITANAM DAN ADA ZOMBIE DI TILE TERSEBUT, 
        {                                                                       // AKAN LANGSUNG NEMBAK DI TILE TERSEBUT
            if (tileRow.get(i).hasPlant())
            {
                if (getFrozenTime() == 3 || getFrozenTime() == 1) 
                {
                    setCurrentMovementSpeed(getCurrentMovementSpeed() + 1); 
                    return;
                }
                Plant p = tileRow.get(i).getPlant();
                p.setHealth(p.getHealth() - getAttackDamage());                 // RESET COOLDOWN
                return;                                                         // LANGSUNG DI RETURN AGAR NEMBAK HANYA 1 TILE PALING DEPAN SAJA
            }                                                            
        }
    }
}   
