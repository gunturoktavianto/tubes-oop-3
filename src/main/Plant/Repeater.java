<<<<<<< Updated upstream
package Plant;
import AbstractClass.Plant;
import Interface.Action;
=======
package main.Plant;
import java.util.ArrayList;

import main.AbstractClass.*;
import main.Game.Lawn;
import main.Interface.Action;
>>>>>>> Stashed changes

public class Repeater extends Plant implements Action {
    
    public Repeater() {
        setName("Repeater");
        setCost(200);
        setHealth(100);
<<<<<<< Updated upstream
        setAttackDamage(50);
        setAttackSpeed(4);
        setAttackCooldown(getAttackSpeed());
        setRange(-1);
    }

    public void action() {
        System.out.println("repeater");
    }
=======
        setAttackDamage(60);
        setAttackSpeed(4);
        setAttackCooldown(getAttackSpeed());
        setPlantPosition(row, col);
        setRange(-1);
        setPlantingCooldown(10);
    }

    public void action() {
        if (getAttackCooldown() > 0)                                            // TIDAK PERLU MELAKUKAN SHOOT
        {
            setAttackCooldown(getAttackCooldown() - 1);                         // MENGURANGI COOLDOWN
            return;
        }
        ArrayList<Tile> tileRow = Lawn.getLawn().get(row);
        for (int i=col; i<tileRow.size(); i++)                                  // JIKA PLANT BARU DITANAM DAN ADA ZOMBIE DI TILE TERSEBUT, 
        {                                                                       // AKAN LANGSUNG NEMBAK DI TILE TERSEBUT
            if (tileRow.get(i).hasZombie())
            {
                System.out.println("DOR!!! zombie di tile" + i);
                for (Zombie z : tileRow.get(i).getZombies())
                {
                    z.setHealth(z.getHealth() - getAttackDamage());
                }
                setAttackCooldown(getAttackSpeed());                            // RESET COOLDOWN
                return;                                                         // LANGSUNG DI RETURN AGAR NEMBAK HANYA 1 TILE PALING DEPAN SAJA
            }                                                            
        }
    }   
>>>>>>> Stashed changes
}
