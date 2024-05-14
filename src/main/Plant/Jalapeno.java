package Plant;
import java.util.ArrayList;

import AbstractClass.Plant;
import AbstractClass.Tile;
import AbstractClass.Zombie;
import Game.Lawn;
import Interface.Action;

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
        for (int i=col; i < tileRow.size(); i++)                                  // JIKA PLANT BARU DITANAM DAN ADA ZOMBIE DI TILE TERSEBUT, 
        {                                                                       // AKAN LANGSUNG NEMBAK DI TILE TERSEBUT
            if (tileRow.get(i).hasZombie())
            {
                System.out.println("DOR!!! Jalapeno ne bos");
                for (Zombie z : tileRow.get(i).getZombies())
                {
                    z.setHealth(z.getHealth() - z.getHealth());
                }
                tileRow.get(col).removePlant(); // lsg remove
                return;                                                         // LANGSUNG DI RETURN AGAR NEMBAK HANYA 1 TILE PALING DEPAN SAJA
            }                                                            
        }
    }
}
