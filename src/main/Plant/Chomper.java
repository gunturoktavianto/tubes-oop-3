package Plant;
import AbstractClass.Plant;
import Interface.Action;

public class Chomper extends Plant implements Action {
    
    public Chomper() {
        setName("Chomper");
        setCost(150);
        setHealth(100);
        setAttackDamage(500);
        setAttackSpeed(4);
        setAttackCooldown(9999);
        setRange(1);
    }

    public void action() {
        System.out.println("chomper");
    }
}
