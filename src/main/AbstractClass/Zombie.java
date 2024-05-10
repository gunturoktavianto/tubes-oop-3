package AbstractClass;

import Interface.Moveable;

public abstract class Zombie implements Moveable {
    private String name;
    private int health;
    private int attackDamage;
    private int x; // row posiition
    private int y = 8       ; // column position
    private float attackSpeed; // yang konstan
    private float currentAttackSpeed = 0; // yang kurang
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

    public float getCurrentAttackSpeed() {
        return currentAttackSpeed;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public void setCurrentAttackSpeed(float currentAttackSpeed) {
        this.currentAttackSpeed  = currentAttackSpeed;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void makeDead() {
        this.isDead = true;
    }
    
    public void moveForward() {
        return;
    }

    public void action() {
        return;
    }
}
