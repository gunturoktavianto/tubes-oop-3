package Plant;
import AbstractClass.Plant;
import Game.Game;
import Interface.Action;

public class Sunflower extends Plant implements Action {
    private int sunGenerated;
    private int sunCooldown;
    
    public Sunflower(int row, int col) {
        setName("Sunflower");
        setCost(50);
        setHealth(100);
        setAttackDamage(0);
        setAttackSpeed(3); // attack speed dsnai waktu dia produce sunnya
        setAttackCooldown(3);
        setRange(0);
        sunGenerated = 0;
        // sunCooldown = 0;
        setPlantPosition(row, col);
    }

    // public int generateSun(){
    //     sunCooldown++;
    //     if (sunCooldown == 10){
    //         sunGenerated += 25;
    //         sunCooldown = 0;
    //     }
    //     return sunGenerated;
    // }

    public void action() {
        System.out.println("sunflower");
        if (getAttackCooldown() > 0)                                                 // TIDAK PERLU MELAKUKAN SHOOT
        {
            setAttackCooldown(getAttackCooldown() - 1);                         // MENGURANGI COOLDOWN
            return;
        }
        else
        {   
            Game.setSun(Game.getSun() + 25);
            setAttackCooldown(getAttackSpeed());                            // RESET COOLDOWN
            return; 
        }
        
    }
}
