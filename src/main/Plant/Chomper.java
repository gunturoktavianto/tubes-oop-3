package Plant;
import AbstractClass.Plant;
import Interface.Action;

public class Chomper extends Plant implements Action {
<<<<<<< Updated upstream
    
=======
    int ranges;
>>>>>>> Stashed changes
    public Chomper() {
        setName("Chomper");
        setCost(150);
        setHealth(100);
        setAttackDamage(500);
        setAttackSpeed(4);
        setAttackCooldown(9999);
        setRange(1);
<<<<<<< Updated upstream
=======
        setPlantPosition(row, col);
        setPlantingCooldown(10);
>>>>>>> Stashed changes
    }

    public void action() {
        System.out.println("chomper");
    }
}
