package com.app.Plant;
import java.util.ArrayList;

import com.app.AbstractClass.*;
import com.app.Game.Lawn;
import com.app.Interface.Action;

public class PotatoMine extends Plant implements Action {
    
    public PotatoMine(int row, int col) {
        setName("PotatoMine");
        setCost(25);
        setHealth(100);
        setAttackDamage(0);
        setAttackSpeed(0);
        setAttackCooldown(8);
        setPlantPosition(row, col);
        setAquatic(true);
    }

    public void action() {
        if (getAttackCooldown() > 0)                                                 // TIDAK PERLU MELAKUKAN SHOOT
        {
            setAttackCooldown(getAttackCooldown() - 1);                         // MENGURANGI COOLDOWN
            return;
        }
        ArrayList<Tile> tileRow = Lawn.getLawn().get(row);                                                                  // AKAN LANGSUNG NEMBAK DI TILE TERSEBUT
        if (tileRow.get(col).hasZombie())
        {
            for (Zombie z : tileRow.get(col).getZombies())
            {
                z.setHealth(z.getHealth() - z.getHealth());
            }
            tileRow.get(col).removePlant(); //lsg remove
            return;                                                         // LANGSUNG DI RETURN AGAR NEMBAK HANYA 1 TILE PALING DEPAN SAJA
        }                                                            
    }
    
}
