package Plant;
import AbstractClass.Plant;
import Interface.Action;

public class Kubis extends Plant implements Action {
    
    public Kubis() {
        setName("Kubis");
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
