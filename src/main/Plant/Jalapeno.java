package Plant;
import AbstractClass.Plant;
import Interface.Action;

public class Jalapeno extends Plant implements Action {
    
    public Jalapeno() {
        setName("Jalapeno");
        setCost(125);
        setHealth(100);
        setAttackDamage(5000);
        setAttackSpeed(0);
        setRange(1); //bingung mo -1 ato gimana
        setCooldown(20);
    }

    public void action() {
        System.out.println("jalapeno");
    }
}
