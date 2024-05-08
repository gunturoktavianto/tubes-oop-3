package Plant;
import AbstractClass.Plant;
import Interface.Action;

public class SnowPea extends Plant implements Action {
    
    public SnowPea() {
        setName("SnowPea");
        setCost(175);
        setHealth(100);
        setAttackDamage(25);
        setAttackSpeed(4);
        setRange(-1);
        setCooldown(10);
    }

    public void action() {
        
    }
}
