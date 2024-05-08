package Plant;
import AbstractClass.Plant;
import Interface.Action;

public class Peashooter extends Plant implements Action{
    
    public Peashooter() {
        setName("Peashooter");
        setCost(100);
        setHealth(100);
        setAttackDamage(25);
        setAttackSpeed(4);
        setRange(-1);
        setCooldown(10);
    }

    public void action() {
        
    }
}
