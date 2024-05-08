package Plant;
import AbstractClass.Plant;

public class Sunflower extends Plant {
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
        sunCooldown = 0;
    }

    public void shoot() {
        
    }

    public int generateSun(){
        sunCooldown++;
        if (sunCooldown == 10){
            sunGenerated += 25;
            sunCooldown = 0;
        }
        return sunGenerated;
    }
}