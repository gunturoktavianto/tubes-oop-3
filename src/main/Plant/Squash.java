package Plant;
import AbstractClass.Plant;
import Interface.Action;

public class Squash extends Plant implements Action {
    
    public Squash() {
        setName("Squash");
        setCost(50);
        setHealth(100);
        setAttackDamage(5000);
        setAttackSpeed(0);
        setRange(1);
        setCooldown(20);
    }

    public void action() {
        System.out.println("squash");
    }
}
