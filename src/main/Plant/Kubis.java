package main.Plant;
import java.util.ArrayList;

import main.AbstractClass.*;
import main.Game.Lawn;
import main.Interface.Action;

public class Kubis extends Plant implements Action {
    
    public Kubis(int row, int col) {
        setName("Kubis");
        setCost(100);
        setHealth(100);
        setAttackDamage(25);
        setAttackSpeed(4);
        setAttackCooldown(getAttackSpeed());
        setRange(-1);
        setPlantPosition(row, col);
    }

    public void action() {
        System.out.println("kubis");
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
                System.out.println("DOR!!! zombie di tile" + i + " kena damage sebesar : " + getAttackDamage() + " \n");
                for (Zombie z : tileRow.get(i).getZombies())
                {
                    if(z.getHealth() < getAttackDamage()) z.setHealth(0);
                    else z.setHealth(z.getHealth() - getAttackDamage());
                }
                setAttackCooldown(getAttackSpeed());                            // RESET COOLDOWN
                return;                                                         // LANGSUNG DI RETURN AGAR NEMBAK HANYA 1 TILE PALING DEPAN SAJA
            }                                                            
        }
    }
}
