package Game;

import java.util.ArrayList;

import java.util.Iterator;

import AbstractClass.Plant;
import AbstractClass.Tile;
import AbstractClass.Zombie;

import Plant.*;
import Tile.*;

import Zombie.NormalZombie;

public class Lawn {
    private static Lawn instance;
    private static ArrayList<ArrayList<Tile>> lawn = new ArrayList<>();

    public Lawn() {
        initializeLawn();

        // lawn.get(0).get(9).spawnZombie()
        lawn.get(0).get(9).addZombie(new NormalZombie(0, 9));
        lawn.get(1).get(9).addZombie(new NormalZombie(1, 9));
        lawn.get(2).get(9).addZombie(new NormalZombie(2, 9));
        lawn.get(3).get(9).addZombie(new NormalZombie(3, 9));
        lawn.get(4).get(9).addZombie(new NormalZombie(4, 9));
        lawn.get(5).get(9).addZombie(new NormalZombie(5, 9));

        lawn.get(0).get(6).plant(new Peashooter(0, 6));
        lawn.get(1).get(6).plant(new Peashooter(1, 6));
        lawn.get(2).get(1).plant(new Lilypad(2, 1));
        lawn.get(3).get(2).plant(new Lilypad(3, 2));
        lawn.get(4).get(6).plant(new Peashooter(4, 6));
        lawn.get(5).get(6).plant(new Peashooter(5, 6));
    }

    public static Lawn getLawnInstance()                                        // SINGLETON DESIGN PATTERN
    {
        if (instance == null)
            instance = new Lawn();
        return instance;
    }

    public static ArrayList<ArrayList<Tile>> getLawn()
    {
        return lawn;
    }

    public void initializeLawn() {
        for (int i = 0; i < 6; i++) {
            ArrayList<Tile> row = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                if (j == 0) { // THE HOUSE TILE
                    row.add(new HouseTile());
                } else {
                    if (i == 2 || i == 3) {
                        row.add(new WaterTile(i, j));
                    } else {
                        row.add(new GroundTile(i, j));
                    }
                }
            }
            lawn.add(row);
        }
    }

    public void moveForward(int row) { 
        ArrayList<Tile> tileRow = lawn.get(row);
        if (tileRow.get(0).hasZombie()) {
            System.out.println("Zombies have reached the house! Game Over.");
            System.exit(0);
        }

        // Moving zombies from right to left
        for (int col = 1; col < tileRow.size(); col++) {
            if (tileRow.get(col).hasZombie()) {
                ArrayList<Zombie> zombies = tileRow.get(col).getZombies();
                Iterator<Zombie> iterator = zombies.iterator();
                while (iterator.hasNext()) {
                    Zombie z = iterator.next();
                    if (z.getHealth() <= 0)
                    {
                        System.out.println("ZOMBIE MATI!!!");
                        iterator.remove();
                    }
                    if (z.getMovementSpeed() == 0.0f) {
                        if (z.getIsFrozen()) {
                            z.setMovementSpeed(10.0f);
                        } else {
                            z.setMovementSpeed(5.0f);
                        }
                        tileRow.get(col - 1).getZombies().add(z);
                        iterator.remove(); // Safe removal
                    } else if (z.getMovementSpeed() > 0) {
                        z.setMovementSpeed(z.getMovementSpeed() - 1f);
                    }
                }
            }
        }
    }

    public void INITIALIZE_ATTACK() {
        for (int i=0; i<6; i++)                                                 // PLANT ATTACK
        {
            ArrayList<Tile> tileRow = lawn.get(i);
            for (int j=1; j < tileRow.size(); j++)
            {
                if (tileRow.get(j).hasPlant())
                {
                    Plant plant = tileRow.get(j).getPlant();
                    plant.shoot();
                    if (plant.getHealth() <= 0)
                    {
                        tileRow.get(j).removePlant();                           // REMOVE PLANT JIKA DARAH SUDAH HABIS
                    }
                }
                if (tileRow.get(j).hasZombie())
                {
                    for (Zombie z : tileRow.get(j).getZombies())
                    {
                        z.attack();
                    }
                }
            }
        }
    }

    // public void shoot(int row) {
    //     ArrayList<Tile> tileRow = lawn.get(row);
        
    //     for (int col = 1; col < tileRow.size() - 1; col++ )
    //     {
    //         if (tileRow.get(col).hasPlant()) 
    //         {
    //             Plant plant = tileRow.get(col).getPlant();
    //             if (plant.getAttackCooldown() == 0)                             // SUDAH BISA MELAKUKAN ATTACK
    //             {
    //                 for (int j = col + 1; j < tileRow.size(); j++)
    //                 {
    //                     if (tileRow.get(j).hasZombie())                         // TILE PALING DEKET AKAN DIKENAI
    //                     {
    //                         System.out.println("DOR!!!!");                    // DELETE LATER
    //                         for (Zombie z : tileRow.get(j).getZombies())
    //                         {
    //                             z.setHealth(z.getHealth() - plant.getAttackDamage()); // SEMUA ZOMBIE DI TILE TERSEBUT TERKENA DAMAGE
    //                         }
    //                         plant.setAttackCooldown(plant.getAttackSpeed());    // DI RESTART COOLDOWNNYA
    //                         if (tileRow.get(j-1).hasPlant())                    // CEK APAKAH DIDEPANNYA ADA PLANT ATAU GA UNTUK DI ATTACK ZOMBIE
    //                         {
    //                             for (Zombie z : tileRow.get(j).getZombies())
    //                             {
    //                                 plant.setHealth(plant.getHealth() - z.getAttackDamage()); // ZOMBIE SELALU ATTACK SETIAP DETIK
    //                                 if (plant.getHealth() <= 0)
    //                                 {
    //                                     System.out.println("PLANT MATI!!!");  //DELETE LATER    
    //                                     tileRow.get(j-1).removePlant();
    //                                 }
    //                             }
    //                         }
    //                         return;                                             // JANGAN DILANJUTIN BIAR GAKENA ZOMBIE BELAKANGNYA
    //                     }   
    //                 }
    //             } else
    //             {
    //                 plant.setAttackCooldown(plant.getAttackCooldown() - 1);     // KURANGI COOLDOWN
    //             }
    //         }
    //     }
    // }

    public void moveAll() {
        for (int i=0; i<6; i++) {
            moveForward(i);
        }
    }

    public void printLawn() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    System.out.print("[ ]");
                } else {
                    if (lawn.get(i).get(j).hasZombie() && lawn.get(i).get(j).hasPlant()) // IF THERE IS A ZOMBIE AND PLANT IN A TILE
                        System.out.print((i == 2 || i == 3 ? "\u001B[34m" : "\u001B[32m") + "[X]" + "\u001B[0m");
                    else if (lawn.get(i).get(j).hasZombie()) // ONLY HAS A ZOMBIE IN A TILE
                        System.out.print((i == 2 || i == 3 ? "\u001B[34m" : "\u001B[32m") + "[Z]" + "\u001B[0m");
                    else if (lawn.get(i).get(j).hasPlant()) // ONLY HAS A PLANT IN A TILE
                        System.out.print((i == 2 || i == 3 ? "\u001B[34m" : "\u001B[32m") + "[P]" + "\u001B[0m");
                    else 
                        System.out.print((i == 2 || i == 3 ? "\u001B[34m" : "\u001B[32m") + "[ ]" + "\u001B[0m");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public boolean isTailPlanted(int x, int y)
    {
        return (lawn.get(x).get(y).hasPlant());
    }

    public boolean isAnyZombie(int x, int y)
    {
        return (lawn.get(x).get(y).hasZombie());
    }

    // public void planting(int x, int y, Plant plant)
    // {
        
    //     if(plant.getCurrentCooldown() > 0)
    //     {
    //         plant.setCooldown(plant.getCooldown() - 1f);
    //     }
    //     else
    //     {
    //         if(Game.getSun() > plant.getCost())
    //         {
    //             if(!lawn.get(x).get(y).hasPlant()) 
    //             {
    //                 lawn.get(x).get(y).plant(plant);
    //                 plant.setX(x);
    //                 plant.setY(y);
    //                 plant.setCurrentCooldown(plant.getCooldown());
    //             }
    //             else System.out.println("cannot plant plants in the tile that already have plants.");
    //         }
    //         else System.out.println("You r Broke");
    //     }
    // }

    // public void zombieAction()
    // {
    //     for (int row = 0; row < lawn.size(); row++) {
    //         ArrayList<Tile> tileRow = lawn.get(row);
    //         for (int col = 1; col < tileRow.size(); col++) {                                   // Start from 1 to prevent array out of bounds
    //             if (tileRow.get(col).hasZombie()) {
    //                 ArrayList<Zombie> zombies = tileRow.get(col).getZombies();
    //                 for (Zombie z : zombies) {
    //                     if (z.getCurrentAttackSpeed() == 0 && z.getAttackDamage() > 0) {        // pastiin zombienya bisa attack
    //                         z.action();                                                         // Cek dulu mana zombie yang attack_speed udah 0 dilakuin action
    //                         z.setCurrentAttackSpeed(z.getAttackSpeed());                        // Reset its attack speed (attackspeed cooldown)
    //                     } else if (z.getCurrentAttackSpeed() > 0 && z.getAttackDamage() > 0) {  // Need to decrease its attack_speed (as a cooldown)
    //                         z.setCurrentAttackSpeed(z.getCurrentAttackSpeed() - 1f);            // Decrease its attack speed
    //                     }
    //                 }
    //             }
    //         }
    //     }
    // }

//     public void plantAction()
//     {
//         for (int row = 0; row < lawn.size(); row++) {
//             ArrayList<Tile> tileRow = lawn.get(row);
//             for (int col = 1; col < tileRow.size(); col++) {                    // Start from 1 to prevent array out of bounds
//                 if (tileRow.get(col).hasPlant()) {
//                     Plant p = tileRow.get(col).getPlant();
//                     if (p instanceof Chomper || p instanceof Jalapeno) {   
//                             p.action();        
//                     }                                                 
//                     else 
//                     {
//                         if(p.getCurrentAttackSpeed() == 0)
//                         {
//                             p.action();
//                             p.setCurrentAttackSpeed(p.getAttackSpeed());
//                         }
//                         else
//                         {
//                             p.setCurrentAttackSpeed(p.getCurrentAttackSpeed() - 1f);
//                         }
//                     }             
//                 }
//             }
//         }
//     }

//     public void shoot(Plant plant)
//     {
//         int range = 0; 
//         if(plant.getRange() == -1)
//         {
//             range = 8;
//         } 
//         else 
//         {
//             if(plant.getY()+plant.getRange() > 8) range = 8;
//             else range =plant.getY()+ plant.getRange();
//         }
        
//         for(int i = plant.getY()+1; i <= range; i++)
//         {
//             if(lawn.get(plant.getX()).get(i).hasZombie())
//             {
//                 ArrayList<Zombie> zombies = lawn.get(plant.getX()).get(i).getZombies();
//                 for(Zombie z : zombies)
//                 {
//                     if(z.getHealth() - plant.getAttackDamage() < 0) z.setHealth(0);
//                     else z.setHealth(z.getHealth() - plant.getAttackDamage());
//                 }
//             }
//         }
//     }
}
