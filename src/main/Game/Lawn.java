package Game;

import java.util.ArrayList;

import java.util.Iterator;

import AbstractClass.*;
import Exception.InvalidPlantingException;
import Plant.*;
import Tile.*;
import Zombie.*;

public class Lawn {
    private static Lawn instance;
    private static ArrayList<ArrayList<Tile>> lawn = new ArrayList<>();

    public Lawn() {
        initializeLawn();

        // lawn.get(0).get(9).spawnZombie()
        // lawn.get(0).get(9).addZombie(new NormalZombie(0, 9));
        // lawn.get(1).get(9).addZombie(new NormalZombie(1, 9));
        // lawn.get(2).get(9).addZombie(new NormalZombie(2, 9));
        // lawn.get(3).get(9).addZombie(new NormalZombie(3, 9));
        // lawn.get(4).get(9).addZombie(new NormalZombie(4, 9));
        // lawn.get(5).get(9).addZombie(new NormalZombie(5, 9));

        // lawn.get(0).get(1).plant(new SnowPea(0, 1));
        // lawn.get(1).get(2).plant(new Peashooter(1, 2));
        // lawn.get(2).get(1).plant(new Lilypad(2, 1));
        // lawn.get(3).get(2).plant(new Lilypad(3, 2));
        // lawn.get(4).get(1).plant(new Peashooter(4, 1));
        // lawn.get(5).get(2).plant(new Kubis(5, 2));
        // lawn.get(5).get(3).plant(new Jalapeno(5, 3));
        // lawn.get(4).get(8).plant(new Squash(4, 8));

        try {
            plant(0, 5, new Wallnut(0,5));
            plant(0, 6,new SnowPea(0,6));
            plant(0, 3,new Wallnut(0,3));
            plant(0, 7, new Wallnut(0,7));
            lawn.get(0).get(9).getZombies().add(new PoleVaultingZombie(0, 9));
        } catch (InvalidPlantingException e) {
            System.out.println(e.getMessage());
        }
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
        for (int col = tileRow.size() - 1; col > 0; col--) {
            if (tileRow.get(col).hasZombie()) {
                ArrayList<Zombie> zombies = tileRow.get(col).getZombies();
                Iterator<Zombie> iterator = zombies.iterator();
                while (iterator.hasNext()) {
                    Zombie z = iterator.next();
                    if (z.getHealth() > 0)
                    {
                        System.out.println("current speed: " + z.getCurrentMovementSpeed());
                        System.out.println("frozen time: " + z.getFrozenTime());
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
                            z.setCurrentMovementSpeed((float)Math.ceil(z.getCurrentMovementSpeed() / 2)); // kenapa di 1 soalnya karena di 1 ama 0 harusnya sama aja toh nti diubah lagi di 0 kan biat lebih ez aja
                            z.setIsWillNotFreeze(false);
                            System.out.println("current speed: " + z.getCurrentMovementSpeed());
                        }
                        if (z.getCurrentMovementSpeed() <= 0.0f) 
                        {
                            if (z.getFrozenTime() > 0) 
                            {
                                System.out.println("dingin bang");
                                // float temp = z.getCurrentMovementSpeed();
                                z.setCurrentMovementSpeed(z.getMovementSpeed() + z.getMovementSpeed());
                                z.setFrozenTime(z.getFrozenTime()-1);
                            } else 
                            { 
                                z.setCurrentMovementSpeed(z.getMovementSpeed());
                            }
                            // }
                            tileRow.get(col - 1).getZombies().add(z);
                            z.setZombiePosition(row, col-1);
                            iterator.remove(); // Safe removal
                        } 
                        else 
                        {
                            z.setCurrentMovementSpeed(z.getCurrentMovementSpeed() - 1f);
                            if (z.getFrozenTime() > 0) 
                                z.setFrozenTime(z.getFrozenTime()-1);
                        }
                        // if(z.getFrozenTime() == 0)
                        // {
                        //     z.setIsFrozen(false);
                        // }
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
        for (int i = 0; i < 6; i++) {
            ArrayList<Tile> tileRow = lawn.get(i);
    
            // Plant attack
            for (int j = 1; j < tileRow.size(); j++) {
                if (tileRow.get(j).hasPlant()) {
                    Plant plant = tileRow.get(j).getPlant();
                    plant.action();
                    if (plant.getHealth() <= 0) {
                        tileRow.get(j).removePlant(); // Remove plant if health is zero
                    }
                }
            }
    
            for (int j = tileRow.size() - 1; j > 0; j--) {
                if (tileRow.get(j).hasZombie()) {
                    Iterator<Zombie> iterator = tileRow.get(j).getZombies().iterator();
                    while (iterator.hasNext()) {
                        Zombie z = iterator.next();
                        z.action();
                        if (z.getZombieCol() != j) {                            // TREATMENT KHUSUS UNTUK POLE VAULTER
                                                                                // DI ZOMBIE VAULTER, OBJECT ZOMBIE TIDAK DIPINDAHKAN LANGSUNG
                            iterator.remove();                                  // POSITION DIBUAT TIDAK SINKRON DENGAN CURRENT TILE LALU DIUBAH DISINI
                        }
                        if (z.getHealth() <= 0) {
                            System.out.println("ZOMBIE MATI!!!");
                            iterator.remove();
                        }
                    }
                }
            }
        }
    }

    public void plant(int row, int col, Plant plant) 
        throws InvalidPlantingException
    {
        if (row < 0 || row > 5 || col < 1 || col > 9)
        {
            throw new InvalidPlantingException("Tidak Bisa Menaruh Plant!");
        }
        if (row == 2 || row == 3)                                               // WATER TILE PLANTING
        {
            if (!getLawn().get(row).get(col).hasPlant()                         // KONDISI JIKA BELUM ADA LILYPAD
                &&
                plant.getName().equals("Lilypad")                      // PLANT YANG INGIN DITARO ADALAH LILYPAD
            )
            {
                lawn.get(row).get(col).setPlant(plant);
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
            } else
            {
                throw new InvalidPlantingException("Sudah ada tanaman");
            }
        }
    }

    public void printLawn() 
    {
        for (int i = 0; i < 6; i++) 
        {
            for (int j = 0; j < 10; j++) 
            {
                if (j == 0) 
                {
                    System.out.print("[ ]");
                } else 
                {
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
}
