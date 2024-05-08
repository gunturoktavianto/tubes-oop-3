package Plant;
import AbstractClass.Plant;
import Interface.Action;

public class Sunflower extends Plant implements Action {
    
    public Sunflower() {
        setName("Sunflower");
        setCost(50);
        setHealth(100);
        setAttackDamage(0);
        setAttackSpeed(0);
        setRange(0);
        setCooldown(10);
    }

    public void action() {
        
    }
}
