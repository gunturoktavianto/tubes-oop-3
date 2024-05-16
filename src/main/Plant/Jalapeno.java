package main.Plant;
import java.util.ArrayList;

import main.AbstractClass.*;
import main.Game.Lawn;
import main.Interface.Action;

public class Jalapeno extends Plant implements Action {
    
    public Jalapeno(int row, int col) {
        setName("Jalapeno");
        setCost(125);
        setHealth(100000);
        setAttackDamage(5000);
        setAttackSpeed(0);
        setAttackCooldown(3);
        setRange(-1); //bingung mo -1 ato gimana
        setPlantPosition(row, col);
    }

    public void action() {
        if (getAttackCooldown() > 0)                                                 // TIDAK PERLU MELAKUKAN SHOOT
        {
            setAttackCooldown(getAttackCooldown() - 1);                         // MENGURANGI COOLDOWN
            return;
        }
        ArrayList<Tile> tileRow = Lawn.getLawn().get(row);
        for (int i=1; i < tileRow.size(); i++)                                 // JIKA PLANT BARU DITANAM DAN ADA ZOMBIE DI TILE TERSEBUT, 
        {                                                                       // AKAN LANGSUNG NEMBAK DI TILE TERSEBUT
            if (tileRow.get(i).hasZombie())
            {
                System.out.println("DOR!!! Jalapeno ne bos");
                for (Zombie z : tileRow.get(i).getZombies())
                {
                    z.setHealth(0);
                }
                tileRow.get(col).removePlant(); // lsg remove
            }                                                            
        }
    }
}
