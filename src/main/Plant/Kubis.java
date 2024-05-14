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
        setAttackCooldown(getAttackSpeed());
        setRange(-1);
    }

    public void action() {
        System.out.println("kubis");
    }
}
