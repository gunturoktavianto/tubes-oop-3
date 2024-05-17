package com.app.Plant;
import java.util.ArrayList;

import com.app.AbstractClass.*;
import com.app.Game.Lawn;
import com.app.Interface.Action;

public class Squash extends Plant implements Action {
    int ranges;
    int cols;
    public Squash(int row, int col) {
        setName("Squash");
        setCost(50);
        setHealth(10000);
        setAttackDamage(5000);
        setAttackSpeed(0);
        setAttackCooldown(1);
        setRange(1);
        setPlantPosition(row, col);
    }

    public void action() {
        ArrayList<Tile> tileRow = Lawn.getLawn().get(row);
        if  (getRange() + col > tileRow.size()-1) 
            ranges = tileRow.size()-1;
        else 
            ranges = col+getRange();
        if (col - 1 < 1) 
            cols = col;
        else 
            cols = col-1;
        for (int i=cols; i <= ranges; i++)                                  // JIKA PLANT BARU DITANAM DAN ADA ZOMBIE DI TILE TERSEBUT, 
        {                                                                       // AKAN LANGSUNG NEMBAK DI TILE TERSEBUT
            if (tileRow.get(i).hasZombie())
            {
                for (Zombie z : tileRow.get(i).getZombies())
                {
                    z.setHealth(0);
                }
                tileRow.get(col).removePlant(); //lsg remove
                return;                                                         // LANGSUNG DI RETURN AGAR NEMBAK HANYA 1 TILE PALING DEPAN SAJA
            }                                                            
        }
    }
}
