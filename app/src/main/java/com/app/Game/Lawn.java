package com.app.Game;

import java.util.ArrayList;

import java.util.Iterator;

import com.app.GameCLI;
import com.app.AbstractClass.*;
import com.app.Exception.InvalidPlantingException;
import com.app.Plant.*;
import com.app.Tile.*;
import com.app.Zombie.*;

public class Lawn {
    private static Lawn instance;
    private static ArrayList<ArrayList<Tile>> lawn = new ArrayList<>();

    public Lawn() 
    {
        initializeLawn();

        // try {
        //     plant(0, 1,new SnowPea());
        //     plant(0, 2, new Wallnut());
        //     plant(0, 3, new Wallnut());

        //     plant(1, 1,new SnowPea());
        //     plant(1, 2,new SnowPea());
        //     plant(1, 3,new Wallnut());

        //     plant(2, 1, new Lilypad());
        //     plant(2, 2, new Lilypad());
        //     plant(2, 3, new Lilypad());
        //     plant(2, 1, new SnowPea());
        //     plant(2, 2, new SnowPea());
        //     plant(2, 3, new SnowPea());


        //     plant(3, 1, new Lilypad());
        //     plant(3, 2, new Lilypad());
        //     plant(3, 3, new Lilypad());
        //     plant(3, 1, new SnowPea());
        //     plant(3, 2, new SnowPea());
        //     plant(3, 3, new SnowPea());

        //     plant(4, 1,new SnowPea());
        //     plant(4, 2,new Wallnut());
        //     plant(4, 3, new Wallnut());

        //     plant(5, 1,new SnowPea());
        //     plant(5, 2,new Wallnut());
        //     plant(5, 3, new Wallnut());
        //     // lawn.get(0).get(9).getZombies().add(new PoleVaultingZombie(0, 9));
        // } catch (Exception e) {
        //     System.out.println(e.getMessage());
        // }
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
        for (int i = 0; i < 6; i++) 
        {
            ArrayList<Tile> row = new ArrayList<>();
            for (int j = 0; j < 10; j++) 
            {
                if (j == 0) 
                { // THE HOUSE TILE
                    row.add(new HouseTile());
                } 
                else 
                {
                    if (i == 2 || i == 3) 
                    {
                        row.add(new WaterTile(i, j));
                    } 
                    else 
                    {
                        row.add(new GroundTile(i, j));
                    }
                }
            }
            lawn.add(row);
        }
    }

    public void moveForward(int row) { 
        ArrayList<Tile> tileRow = lawn.get(row);
        if (tileRow.get(0).hasZombie()) 
        {
            System.out.println("Zombies have reached the house! Game Over.");
            GameCLI.endGame();
        }

        // Moving zombies from right to left
        for (int col = tileRow.size() - 1; col > 0; col--) {
            if (tileRow.get(col).hasZombie()) {
                ArrayList<Zombie> zombies = tileRow.get(col).getZombies();
                Iterator<Zombie> iterator = zombies.iterator();
                while (iterator.hasNext()) {
                    Zombie z = iterator.next();
                    if (z.getHealth() > 0)
                    {
                        if(z.getFrozenTime() == 3)
                        {
                            z.setCurrentMovementSpeed(z.getCurrentMovementSpeed() + z.getCurrentMovementSpeed());
                        }
                        else if(z.getFrozenTime() == 1)
                        {
                            z.setIsWillNotFreeze(true);
                        }
                        else if(z.getFrozenTime() == 0 && z.getIsWillNotFreeze() == true)
                        {
                            z.setCurrentMovementSpeed((float)Math.ceil(z.getCurrentMovementSpeed() / 2) - 1); // kenapa di 1 soalnya karena di 1 ama 0 harusnya sama aja toh nti diubah lagi di 0 kan biat lebih ez aja
                            z.setIsWillNotFreeze(false);
                        }
                        if (z.getCurrentMovementSpeed() <= 0.0f) 
                        {
                            if (z.getFrozenTime() > 0) 
                            {
                                z.setCurrentMovementSpeed(z.getMovementSpeed() + z.getMovementSpeed());
                                z.setFrozenTime(z.getFrozenTime()-1);
                            } 
                            else 
                            { 
                                z.setCurrentMovementSpeed(z.getMovementSpeed());
                            }
                            tileRow.get(col - 1).getZombies().add(z);
                            z.setZombiePosition(row, col-1);
                            iterator.remove();
                        } 
                        else 
                        {
                            z.setCurrentMovementSpeed(z.getCurrentMovementSpeed() - 1f);
                            if (z.getFrozenTime() > 0) 
                                z.setFrozenTime(z.getFrozenTime()-1);
                        }
                    }
                }
            }
        }
    }

    public void moveAll() {
        for (int i=0; i<6; i++) {
            moveForward(i);
        }
    }

    public void INITIALIZE_ATTACK() {
        for (int i = 0; i < 6; i++) 
        {
            ArrayList<Tile> tileRow = lawn.get(i);
    
            // Plant attack
            for (int j = 1; j < tileRow.size(); j++) 
            {
                if (tileRow.get(j).hasPlant()) 
                {
                    Plant plant = tileRow.get(j).getPlant();
                    plant.action();
                    if (plant.getHealth() <= 0) 
                    {
                        tileRow.get(j).removePlant(); // Remove plant if health is zero
                    }
                }
            }
    
            for (int j = tileRow.size() - 1; j > 0; j--) 
            {
                if (tileRow.get(j).hasZombie()) {
                    Iterator<Zombie> iterator = tileRow.get(j).getZombies().iterator();
                    while (iterator.hasNext()) 
                    {
                        Zombie z = iterator.next();
                        z.action();
                        if (z.getZombieCol() != j) 
                        {                                                       // TREATMENT KHUSUS UNTUK POLE VAULTER
                                                                                // DI ZOMBIE VAULTER, OBJECT ZOMBIE TIDAK DIPINDAHKAN LANGSUNG
                            iterator.remove();                                  // POSITION DIBUAT TIDAK SINKRON DENGAN CURRENT TILE LALU DIUBAH DISINI
                        }
                        if (z.getHealth() <= 0) 
                        {
                            Zombie.decreaseZombieCount();
                            iterator.remove();
                        }
                    }
                }
            }
        }
    }

    public static void plant(int row, int col, Plant plant) 
        throws InvalidPlantingException
    {
        if (row < 0 || row > 5 || col < 1 || col > 9)
        {
            throw new InvalidPlantingException("Tidak Bisa Menaruh Plant!");
        }
        if (row == 2 || row == 3)                                               // WATER TILE PLANTING
        {
            if (!getLawn().get(row).get(col).hasPlant())                        // KONDISI JIKA BELUM ADA LILYPAD                  
            {
                if (plant.getName().equals("Lilypad"))                          // PLANT YANG INGIN DITARO ADALAH LILYPAD
                {
                    lawn.get(row).get(col).setPlant(plant);
                    plant.setPlantPosition(row, col);
                }
                else
                {
                    throw new InvalidPlantingException("TIDAK BISA MENARUH TANAMAN TANPA LILYPAD");
                }
            }
            else if (((WaterTile) lawn.get(row).get(col)).hasLilypad())
            {
                if (((Lilypad) lawn.get(row).get(col).getPlant()).isOccupied())
                {
                    throw new InvalidPlantingException("Sudah ada tanaman diatas lilypad ini!");
                }
                else {
                    if (!plant.getName().equals("lilypad")) 
                    {
                        ((Lilypad) lawn.get(row).get(col).getPlant()).plantOnTop(plant);
                        plant.setPlantPosition(row, col);
                    } else
                    {
                        throw new InvalidPlantingException("Tidak bisa menaruh lilypad diatas lilypad");
                    }
                }
            }
        } else
        {
            if (!lawn.get(row).get(col).hasPlant())
            {
                lawn.get(row).get(col).setPlant(plant);
                plant.setPlantPosition(row, col);
            } else
            {
                throw new InvalidPlantingException("Sudah ada tanaman");
            }
        }
    }

    public void printLawn() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0)
                {
                    System.out.print("[     ]");
                }
                else
                {
                    System.out.print((i == 2 || i == 3 ? "\u001B[34m" : "\u001B[32m") + "[");
                    if (lawn.get(i).get(j).hasPlant()) 
                    {
                        System.out.print(getPlantInitial(lawn.get(i).get(j).getPlant()));
                    } 
                    else 
                    {
                        System.out.print(" ");
                    }
        
                    System.out.print((i == 2 || i == 3 ? "\u001B[34m" : "\u001B[32m") + "|" + "\u001B[0m");
        
                    if (lawn.get(i).get(j).hasZombie()) 
                    {
                        ArrayList<Zombie> zombies = lawn.get(i).get(j).getZombies();
                        int zombiesCount = zombies.size();
                        for (int k = 0; k < zombiesCount; k++) 
                        {
                            System.out.print(getZombieInitial(zombies.get(k)));
                        }
                        for (int k = zombiesCount; k < 4; k++) 
                        {
                            System.out.print(" ");
                        }
                    } 
                    else 
                    {
                        System.out.print("    ");
                    }
        
                    System.out.print((i == 2 || i == 3 ? "\u001B[34m" : "\u001B[32m") + "]" + "\u001B[0m");         
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private String getPlantInitial(Plant p) {
        String initial = "\033[1;92m"; // Green color
        switch (p.getName()) {
            case "Chomper":
                initial += 'C';
                break;
            case "Jalapeno":
                initial += 'J';
                break;
            case "Kubis":
                initial += 'K';
                break;
            case "Lilypad":
                initial += 'L';
                break;
            case "Peashooter":
                initial += 'P';
                break;
            case "PotatoMine":
                initial += 'M';
                break;
            case "SnowPea":
                initial += 'S';
                break;
            case "Squash":
                initial += 'Q';
                break;
            case "Sunflower":
                initial += 'F';
                break;
            case "Wallnut":
                initial += 'W';
                break;
            default:
                initial += ' '; // Add a default case to handle unknown plants
                break;
        }
        return initial + "\033[0m"; // Reset color
    }

    private String getZombieInitial(Zombie z) {
        String initial = "\033[1;31m"; // Red color
        switch (z.getName()) {
            case "Buckethead Zombie":
                initial += 'B';
                break;
            case "Conehead Zombie":
                initial += 'C';
                break;
            case "Dolphin Rider Zombie":
                initial += 'D';
                break;
            case "Ducky Tube Zombie":
                initial += 'E';
                break;
            case "Football Zombie":
                initial += 'F';
                break;
            case "Newspaper Zombie":
                initial += 'N';
                break;
            case "Normal Zombie":
                initial += 'Z';
                break;
            case "Pole Vaulting Zombie":
                initial += 'P';
                break;
            case "Terminator Zombie":
                initial += 'T';
                break;
            case "Wallnut Zombie":
                initial += 'W';
                break;
            default:
                initial += ' '; // Add a default case to handle unknown zombies
                break;
        }
        return initial + "\033[0m"; // Reset color
    }
}
