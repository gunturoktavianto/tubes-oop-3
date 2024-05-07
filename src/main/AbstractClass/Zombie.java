package AbstractClass;
import Interface.Moveable;

public abstract class Zombie implements Moveable {
    protected String name;
    protected int health;
    protected int attackDamage;
    protected float attackSpeed;
    protected float movementSpeed = 5;
    protected boolean isAquatic, isFrozen = false, isDead = false;
    protected static int zombieCount = 0;

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
