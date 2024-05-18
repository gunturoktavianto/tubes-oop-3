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
        setAttackCooldown(getAttackSpeed());
        setRange(-1);
<<<<<<< Updated upstream
=======
        setPlantPosition(row, col);
        setPlantingCooldown(10);
>>>>>>> Stashed changes
    }

    public void action() {
        System.out.println("snowpea");
    }
}
