package Plant;
import AbstractClass.Plant;
import Interface.Action;

public class Repeater extends Plant implements Action {
    
    public Repeater() {
        setName("Repeater");
        setCost(200);
        setHealth(100);
        setAttackDamage(50);
        setAttackSpeed(4);
        setAttackCooldown(getAttackSpeed());
        setRange(-1);
    }

    public void action() {
        System.out.println("repeater");
    }
}
