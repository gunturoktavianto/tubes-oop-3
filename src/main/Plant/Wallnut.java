package Plant;
import AbstractClass.Plant;

public class Wallnut extends Plant {
    
    public Wallnut() {
        setName("Wall nut");
        setCost(50);
        setHealth(1000);
        setAttackDamage(0);
        setAttackSpeed(0);
        setAttackCooldown(99999);
        setRange(0);
    }
    public void action(){}
}
