package AbstractClass;
import Interface.Moveable;

public abstract class Zombie implements Moveable {
    private String name;
    private int health;
    private int attackDamage;
    private float attackSpeed;
    private float movementSpeed = 5;
    private boolean isAquatic, isFrozen = false, isDead = false;
    public static int zombieCount = 0;

    // Getter
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }
    
    public int getAttackDamage() { // Doesnt have a setter since it wont change
        return attackDamage;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public boolean getIsAquatic() {
        return isAquatic;
    }

    public boolean getIsFrozen() {
        return isFrozen;
    }

    public boolean getIsDead() {
        return isDead;
    }

    public static int getZombieCount() {
        return zombieCount;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed  = attackSpeed;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public void setIsAquatic(boolean isAquatic) {
        this.isAquatic = isAquatic;
    }

    public void setFrozen() {
        this.isFrozen = true;
    }

    public void makeDead() {
        this.isDead = true;
    }

    public void moveForward() {
        System.out.println("Maju satu langkah");
    }
}
