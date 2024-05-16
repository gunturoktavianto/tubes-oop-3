package main.Plant;
import main.AbstractClass.Plant;
import main.Game.Sun;
import main.Interface.Action;

public class Sunflower extends Plant implements Action {
    
    public Sunflower(int row, int col) {
        setName("Sunflower");
        setCost(50);
        setHealth(100);
        setAttackSpeed(3); // attack speed dsnai waktu dia produce sunnya
        setAttackCooldown(3);
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
            Sun.setSun(Sun.getSun() + 25);
            setAttackCooldown(getAttackSpeed());                            // RESET COOLDOWN
            return; 
        }
        
    }
}
