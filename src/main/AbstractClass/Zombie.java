package AbstractClass;

import Game.Lawn;

public abstract class Zombie {
    private String name;
    private int health;
    private int attackDamage;
    private int attackSpeed = 1;
    private float movementSpeed = 5;
    private boolean isAquatic, isFrozen = false, isDead = false;
    public static int zombieCount = 0;
    private int row, col;

    public void attack() {
        if (Lawn.getLawn().get(row).get(col-1).hasPlant())                      // CEK APAKAH DIDEPAN ZOMBIE ADA PLANT ATAU TIDAK    
        {
            Plant plant = Lawn.getLawn().get(row).get(col-1).getPlant(); 
            plant.setHealth(plant.getHealth() - getAttackDamage());
            System.out.println("NYAM!!! DARAH PLANT: " + plant.getHealth());
            setMovementSpeed(getMovementSpeed() + 1);                           // MENGKOMPENSASI WAKTU ATTACK                         
        }
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

    public void setAttackSpeed(int attackSpeed) {
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
        return;
    }

    public void action() {
        return;
    }
}
