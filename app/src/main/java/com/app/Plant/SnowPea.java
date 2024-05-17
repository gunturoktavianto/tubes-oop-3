package com.app.Plant;
import java.util.ArrayList;

import com.app.AbstractClass.*;
import com.app.Game.Lawn;
import com.app.Interface.Action;

public class SnowPea extends Plant implements Action {
    
    public SnowPea(int row, int col) {
        setName("SnowPea");
        setCost(175);
        setHealth(100);
        setAttackDamage(25);
        setAttackSpeed(4);
        setAttackCooldown(getAttackSpeed());
        setRange(-1);
        setPlantPosition(row, col);
    }

    public void action() {
        if (getAttackCooldown() > 0)                                            // TIDAK PERLU MELAKUKAN SHOOT
        {
            setAttackCooldown(getAttackCooldown() - 1);                         // MENGURANGI COOLDOWN
            return;
        }
        ArrayList<Tile> tileRow = Lawn.getLawn().get(row);
        for (int i=col; i < tileRow.size(); i++)                                // JIKA PLANT BARU DITANAM DAN ADA ZOMBIE DI TILE TERSEBUT, 
        {                                                                       // AKAN LANGSUNG NEMBAK DI TILE TERSEBUT
            if (tileRow.get(i).hasZombie())
            {
                for (Zombie z : tileRow.get(i).getZombies())
                {
                    z.setFrozenTime(3);                                         // waktu zombie frozen di atur
                    if (z.getHealth() < getAttackDamage()) 
                        z.setHealth(0);
                    else 
                        z.setHealth(z.getHealth() - getAttackDamage());
                }
                setAttackCooldown(getAttackSpeed());                            // RESET COOLDOWN
                return;                                                         // LANGSUNG DI RETURN AGAR NEMBAK HANYA 1 TILE PALING DEPAN SAJA
            }                                                            
        }
    }
}
