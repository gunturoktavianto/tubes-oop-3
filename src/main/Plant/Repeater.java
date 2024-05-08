package Plant;
import AbstractClass.Plant;
import Interface.Action;

public class Repeater extends Plant implements Action {
    
    public Repeater() {
        setName("Repeater");
        setCost(100);
        setHealth(100);
        setAttackDamage(50);
        setAttackSpeed(4);
        setRange(-1);
        setCooldown(10);
    }

    public void action() {
        
    }
}
