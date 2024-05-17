package com.app.Plant;
import java.util.ArrayList;

import com.app.AbstractClass.*;
import com.app.Game.Lawn;
import com.app.Interface.Action;

public class Chomper extends Plant implements Action {
    int ranges;
    public Chomper(int row, int col) {
        setName("Chomper");
        setCost(150);
        setHealth(100);
        setAttackDamage(500);
        setAttackSpeed(10);
        setAttackCooldown(0);
        setRange(1);
        setPlantPosition(row, col);
    }

    public void action() {
        ArrayList<Tile> tileRow = Lawn.getLawn().get(row);
        
        if (getRange() + col > tileRow.size()-1) 
            ranges = tileRow.size()-1;
        else 
            ranges = col+getRange();
        
        for (int i=col; i < ranges; i++)                                        // JIKA PLANT BARU DITANAM DAN ADA ZOMBIE DI TILE TERSEBUT, 
        {                                                                       // AKAN LANGSUNG NEMBAK DI TILE TERSEBUT
            if (tileRow.get(i).hasZombie())
            {
                Zombie z = tileRow.get(i).getZombies().get(0);            //CUMA MAKAN 1 ZOMBIE
                z.setHealth(0);                                          // lsg mati anggepannya 
                setAttackCooldown(getAttackSpeed());                            // RESET COOLDOWN
                return;                                                         // LANGSUNG DI RETURN AGAR NEMBAK HANYA 1 TILE PALING DEPAN SAJA
            }                                                            
        }
    }
}
