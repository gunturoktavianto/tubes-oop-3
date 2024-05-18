package Plant;
import AbstractClass.Plant;
import Game.Game;
import Interface.Action;

public class Sunflower extends Plant implements Action {
    private int sunGenerated;
    private int sunCooldown;
    
    public Sunflower() {
        setName("Sunflower");
        setCost(50);
        setHealth(100);
<<<<<<< Updated upstream
        setAttackDamage(0);
        setAttackSpeed(0);
        setAttackCooldown(99999);
        setRange(0);
        sunGenerated = 0;
        // sunCooldown = 0;
=======
        setAttackSpeed(3); // attack speed dsnai waktu dia produce sunnya
        setAttackCooldown(3);
        setPlantPosition(row, col);
        setPlantingCooldown(10);
>>>>>>> Stashed changes
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
        Game.setSun(Game.getSun() + 25);
        System.out.println("sunflower");
    }
}
