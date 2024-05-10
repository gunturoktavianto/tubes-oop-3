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
        setAttackDamage(0);
        setAttackSpeed(0);
        setRange(0);
        setCooldown(10);
        sunGenerated = 0;
        // sunCooldown = 0;
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
