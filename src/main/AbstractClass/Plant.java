package AbstractClass;

import java.util.ArrayList;

import Game.Lawn;

public abstract class Plant {
    private String        name;
    private int           cost;
    private int         health;
    private int   attackDamage;
    private int    attackSpeed;
    private int attackCooldown;
    private int          range;
    private boolean  isAquatic;
    protected int       row, col;                                                 //INFO UNTUK SHOOTING 

    public void shoot() {
        if (getAttackCooldown() > 0)                                                 // TIDAK PERLU MELAKUKAN SHOOT
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
    public int getPlantCol()
    {
        return col;
    }
    
    // public int configRange(int col, int range) // untuk tetapin petak range
    // {
    //     ArrayList<Tile> tileRow = Lawn.getLawn().get(row);
    //     if(range == -1) return tileRow.size();
    //     else if(range + col >= tileRow.size()-1) return tileRow.size();
    //     else if(range + col < tileRow.size()-1 && range + col > 0) return range + col;
    //     else return 0;
    // }

    public void setPlantPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public int getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public void setAttackCooldown(int attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public boolean isAquatic() {
        return isAquatic;
    }

    public void setAquatic(boolean aquatic) {
        isAquatic = aquatic;
    }

    public abstract void action();          // NANTI DULU
}
