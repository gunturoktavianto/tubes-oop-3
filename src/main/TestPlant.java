import Plant.*;

public class TestPlant {
    public static void main(String[] args) {
        Peashooter p1 = new Peashooter();
        Peashooter p2 = new Peashooter();
        p2.setCost(50);
        p2.setHealth(50);
        System.out.println(p1.getName() + " " + p2.getName());
        System.out.println(p1.getHealth() + " " + p2.getHealth());
        System.out.println(p1.getAttackDamage()  + " " + p2.getAttackDamage());
        System.out.println(p1.getAttackSpeed() + " " + p2.getAttackSpeed());
        System.out.println(p1.getCost() + " " + p2.getCost());
        System.out.println(p1.getRange() + " " + p2.getRange());
    }
}
