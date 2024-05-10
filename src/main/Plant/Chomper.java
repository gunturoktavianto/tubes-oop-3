package Plant;
import AbstractClass.Plant;
import Interface.Action;

public class Chomper extends Plant implements Action {
    
    public Chomper() {
        setName("Chomper");
        setCost(150);
        setHealth(100);
        setAttackDamage(500);
        setAttackSpeed(4);//bengong dia
        setRange(1);
        setCooldown(10);
    }

    public void action() {
        System.out.println("chomper");
    }
}
