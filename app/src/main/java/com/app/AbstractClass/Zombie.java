package com.app.AbstractClass;

public class Zombie {
    private String name;
    private int health;
    private int attackDamage;
    private int attackSpeed = 1;
    private float movementSpeed = 10;
    private float currentMovementSpeed = movementSpeed;
    private boolean isAquatic, isDead = false;
    private int frozenTime = 0;
    private static int zombieCount = 0;
    protected int row, col;
    private boolean isWillNotFreeze = false;
    public void attack() {
        
    }

    public int getZombieCol()
    {
        return this.col;
    }

    public void setZombiePosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

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

    public float getCurrentMovementSpeed() {
        return currentMovementSpeed;
    }
    
    public boolean getIsWillNotFreeze() {
        return isWillNotFreeze;
    }

    public boolean getIsAquatic() {
        return isAquatic;
    }
    
    public int getFrozenTime() {
        return frozenTime;
    }

    public boolean getIsDead() {
        return isDead;
    }

    public static int getZombieCount() {
        return zombieCount;
    }

    public static void addZombieCount()
    {
        zombieCount++;
    }

    public static void decreaseZombieCount()
    {
        zombieCount--;
    }

    public static void setZombieCount(int newCount)
    {
        zombieCount = newCount;
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

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed  = attackSpeed;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public void setCurrentMovementSpeed(float currentMovementSpeed) {
        this.currentMovementSpeed = currentMovementSpeed;
    }

    public void setIsAquatic(boolean isAquatic) {
        this.isAquatic = isAquatic;
    }

    public void setFrozenTime(int frozenTime) {
        this.frozenTime = frozenTime;
    }

    public void setIsWillNotFreeze(boolean isWillNotFreeze) {
        this.isWillNotFreeze = isWillNotFreeze;
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
