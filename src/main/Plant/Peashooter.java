package Plant;
import AbstractClass.Plant;

public class Peashooter extends Plant {
    
    public Peashooter() {
        setName("Peashooter");
        setCost(100);
        setHealth(100);
        setAttackDamage(25);
        setAttackSpeed(4);
        setRange(-1);
        setCooldown(10);
    }
}
