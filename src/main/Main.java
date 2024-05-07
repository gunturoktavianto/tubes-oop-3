import Zombie.*;

public class Main {
    public static void main(String[] args) {
        NormalZombie z1 = new NormalZombie();
        NormalZombie z2 = new NormalZombie();
        z2.setMovementSpeed(2.5f);
        System.out.println(z1.getName() + " " + z2.getName());
        System.out.println(z1.getHealth() + " " + z2.getHealth());
        System.out.println(z1.getAttackDamage()  + " " + z2.getAttackDamage());
        System.out.println(z1.getAttackSpeed() + " " + z2.getAttackSpeed());
        System.out.println(z1.getMovementSpeed() + " " + z2.getMovementSpeed());
        System.out.println(z1.getIsAquatic() + " " + z2.getIsAquatic());
        // System.out.println(getZombieCount());
    }
}
