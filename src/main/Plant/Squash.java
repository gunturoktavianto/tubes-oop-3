package Plant;
import AbstractClass.Plant;
import Interface.Action;

public class Squash extends Plant implements Action {
<<<<<<< Updated upstream
    
=======
    int ranges;
    int cols;
>>>>>>> Stashed changes
    public Squash() {
        setName("Squash");
        setCost(50);
        setHealth(100);
        setAttackDamage(5000);
        setAttackSpeed(0);
        setAttackCooldown(99999);
        setRange(1);
<<<<<<< Updated upstream
=======
        setPlantPosition(row, col);
        setPlantingCooldown(10);
>>>>>>> Stashed changes
    }

    public void action() {
        System.out.println("squash");
    }
}
