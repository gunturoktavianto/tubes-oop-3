package com.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;    
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;
import com.Main;
import com.app.AbstractClass.Tile;
import com.app.AbstractClass.Zombie;
import com.app.Game.*;
import com.app.Exception.*;

public class GameCLI extends Main {
    private final Scanner   scanner;
    private static long     passedTime = 0;
    private long            startTime, pauseStartTime, totalPausedTime;
    private Lawn            lawn;
    private Inventory       inventory;
    private static boolean  isGameOver, isStarted;
    private boolean         isPaused;
    private Thread          gameThread, generatorThread, displayThread, spawnThread; 

    public GameCLI() {
        scanner = new Scanner(System.in);
        this.lawn = Lawn.getLawnInstance();
        inventory = new Inventory();
        isStarted = false;
        isGameOver = false;
        isPaused = false;
    }

    private static class GameState {
        private long passedTime;
        private ArrayList<ArrayList<Tile>> lawn;
        private Inventory inventory;
        private int sun;
        private int zombieCount;

        public GameState(long passedTime, ArrayList<ArrayList<Tile>> lawn, Inventory inventory, int sun) {
            this.passedTime = passedTime;
            this.lawn = lawn;
            this.inventory = inventory;
            this.sun = Sun.getSun();
            this.zombieCount = Zombie.getZombieCount();
        }
    }

    public void start() {
        System.out.println("WELCOME TO PLANT vs ZOMBIES");

        while (true)
        {
            if (!isStarted) {
                synchronized (scanner) {
                    System.out.println();
                    System.out.println("MAIN MENU");
                    System.out.println("1. Start");
                    System.out.println("2. Load");
                    System.out.println("3. Plants List");
                    System.out.println("4. Zombies List");
                    System.out.println("5. Exit");
                    System.out.println("6. Help");
                    System.out.println("Masukkan Perintah");
                    System.out.print(">>");
                    String menu = scanner.nextLine();
                    System.out.println();
        
                    if (menu.equalsIgnoreCase("START") || menu.equalsIgnoreCase("Start") || menu.equalsIgnoreCase("1")) 
                    {
                        System.out.println("WELCOME TO THE GAME");
                        System.out.println("SILAHKAN ATUR DECK TERLEBIH DAHULU");
                        boolean inven = true;
                        while(inven && !isStarted)
                        {
                            System.out.println("");
                            System.out.println("INVENTORY MENU");
                            System.out.println("1. Show Deck");
                            System.out.println("2. Show Inventory");
                            System.out.println("3. Switch Deck");
                            System.out.println("4. Switch Inventory");
                            System.out.println("5. Set Deck");
                            System.out.println("6. Delete Deck");
                            System.out.println("7. Start Game");
                            System.out.println("8. Back");
                            System.out.println("Masukkan Perintah");
                            System.out.print(">>");
                            String invenMenu = scanner.nextLine();
                            
                            if (invenMenu.equalsIgnoreCase("SHOW DECK") || invenMenu.equals("1"))
                            {
                                inventory.getDeck().showDeckRev();
                            }
                            else if (invenMenu.equalsIgnoreCase("SHOW INVENTORY") || invenMenu.equals("2"))
                            {
                                inventory.printInventory();
                            }
                            else if (invenMenu.equalsIgnoreCase("SWITCH DECK") || invenMenu.equalsIgnoreCase("3"))
                            {
                                System.out.print("PILIH INDEX TANAMAN 1 UNTUK DITUKAR KE DECK! :");
                                int plantIdx1 = Integer.parseInt(scanner.nextLine());
                                System.out.print("PILIH INDEX TANAMAN 2 UNTUK DITUKAR KE DECK! :");
                                int plantIdx2 = Integer.parseInt(scanner.nextLine());
                                try {
                                    inventory.swapDeckPlacement(plantIdx1, plantIdx2);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            else if (invenMenu.equalsIgnoreCase("SWITCH INVENTORY") || invenMenu.equalsIgnoreCase("4"))
                            {
                                System.out.print("PILIH INDEX TANAMAN 1 UNTUK DITUKAR KE INVENTORY! :");
                                int plantIdx1 = Integer.parseInt(scanner.nextLine());
                                System.out.print("PILIH INDEX TANAMAN 2 UNTUK DITUKAR KE INVENTORY! :");
                                int plantIdx2 = Integer.parseInt(scanner.nextLine());
                                try {
                                    inventory.swapInventoryPlacement(plantIdx1, plantIdx2);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            
                            else if (invenMenu.equalsIgnoreCase("SET DECK") || invenMenu.equalsIgnoreCase("5"))
                            {
                                if(inventory.getDeck().isEmpty())
                                {
                                    while(!inventory.getDeck().isFull())
                                    {
                                        inventory.printInventory();
                                        inventory.getDeck().showDeckRev(); 
                                        try {
                                            System.out.print("PILIH INDEX TANAMAN UNTUK DIMASUKKAN KE DECK! :");
                                            int plantIdx = Integer.parseInt(scanner.nextLine());
                                            System.out.print("PILIH INDEX PADA DECK! :");
                                            int deckIdx = Integer.parseInt(scanner.nextLine());
                                            inventory.setPlantInDeck(deckIdx, plantIdx);
                                        } 
                                        catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }

                                }
                                else
                                {
                                    inventory.printInventory();
                                    inventory.getDeck().showDeckRev();
                                    System.out.print("PILIH INDEX TANAMAN UNTUK DIMASUKKAN KE DECK! :");
                                    int plantIdx = Integer.parseInt(scanner.nextLine());
                                    System.out.print("PILIH INDEX PADA DECK! :");
                                    int deckIdx = Integer.parseInt(scanner.nextLine());
                                    try {
                                        inventory.setPlantInDeck(deckIdx, plantIdx);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            }
                            else if (invenMenu.equalsIgnoreCase("DELETE DECK") || invenMenu.equalsIgnoreCase("6"))
                            {
                                inventory.getDeck().showDeckRev();
                                System.out.print("PILIH INDEX TANAMAN UNTUK DIREMOVE DARI DECK! :");
                                int plantIdx = Integer.parseInt(scanner.nextLine());
                                try {
                                    inventory.deletePlantFromDeck(plantIdx);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            } 
                            else if (invenMenu.equalsIgnoreCase("START GAME") || invenMenu.equalsIgnoreCase("7"))
                            {
                                if (!inventory.getDeck().isFull())  
                                {
                                    System.out.println("DECK BELUM PENUH, GAME TIDAK DAPAT DIMULAI!");
                                }
                                else
                                {
                                    if (!isStarted) {
                                        
                                        isStarted = true;
                                        isPaused = false;
                                        isGameOver = false;
                                        startTime = System.currentTimeMillis();
                                        stopThreads();
                                        startThreads();
                                        startDisplayThread();
                                        //System.out.println(Thread.currentThread());
                                    }
                                }
                            } 
                            else if (invenMenu.equalsIgnoreCase("BACK") || invenMenu.equalsIgnoreCase("8"))
                            {
                                System.out.println("Anda Kembali ke tampilan utama");
                                inven = false;
                            } 
                            else
                            {
                                System.out.println("INVALID INPUT! PLEASE DO IT AGAIN!");
                            }
                        }
                    }
                    else if (menu.equalsIgnoreCase("LOAD") || menu.equalsIgnoreCase("2")) 
                    {   
                        String filePath;
                        System.out.println("MASUKKAN NAMA FILE UNTUK DI LOAD");
                        String input = scanner.nextLine();
                        filePath = "C:/Users/LENOVO/Documents/GitHub/tubes-oop-3/bin/" + input + ".json/";
                        loadGame(filePath);
                    } 
                    else if (menu.equalsIgnoreCase("Plants List") || menu.equalsIgnoreCase("3")) 
                    {
                        printPlantList();
                    } 
                    else if (menu.equalsIgnoreCase("Zombies List") || menu.equalsIgnoreCase("4")) 
                    {
                        printZombieList();
                    } 
                    else if (menu.equalsIgnoreCase("EXIT") || menu.equalsIgnoreCase("5") ) 
                    {
                        System.out.println("Anda Keluar Dari Game");
                        System.exit(0);
                    } 
                    else if (menu.equalsIgnoreCase("HELP") || menu.equalsIgnoreCase("6"))
                    {
                        System.out.println("-----------MENU-----------");
                        System.out.println("1. START");
                        System.out.println("2. HELP");
                        System.out.println("3. Plants List");
                        System.out.println("4. Zombies List");
                        System.out.println("5. EXIT");
                    }
                    else 
                    {
                        System.out.println("Perintah tidak valid! Ketik 'HELP' untuk melihat menu game.");
                    }
                }
            }
        }
    }

    private void startDisplayThread() {
        displayThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isGameOver) {
                    synchronized (scanner) {
                        if (!isPaused) 
                        {
                            // passedTime = (System.currentTimeMillis() - startTime - totalPausedTime) / 1000;

                            if (passedTime > 160 && Zombie.getZombieCount() == 0)
                            {
                                System.out.println("ZOMBIE TELAH HABIS, ANDA MENANG!!!");
                                endGame();
                                start();
                            }
        
                            printGameInfo();
                            lawn.printLawn();
                            
                            System.out.println("Press ENTER to display lawn or input commands.");
                            System.out.println("INPUT : ");
                            System.out.println("1. PLANT");
                            System.out.println("2. DIG");
                            System.out.println("3. PAUSE");
                            System.out.print(">>");
                            String menu = scanner.nextLine();
                            
                            if (menu.equalsIgnoreCase(""))                       // Will just print the map
                            {
                                
                            }
                            else if (menu.equalsIgnoreCase("PLANT") || menu.equalsIgnoreCase("1"))
                            {
                                inventory.getDeck().showDeck();

                                System.out.println("PILIH TANAMANAN YANG MAU DI PLANT: ");
                                int plantIdx = Integer.parseInt(scanner.nextLine());
                                System.out.print("PILIH POSISI ROW TANAMANAN YANG MAU DI PLANT: ");
                                int x = Integer.parseInt(scanner.nextLine());
                                System.out.print("PILIH POSISI COL TANAMANAN YANG MAU DI PLANT: ");
                                int y = Integer.parseInt(scanner.nextLine());   

                                try {
                                    inventory.getDeck().plant(x, y, plantIdx);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            else if ((menu.equalsIgnoreCase("DIG") || menu.equalsIgnoreCase("2")))
                            {
                                System.out.print("PILIH POSISI ROW TANAMANAN YANG MAU DI DIG: ");
                                int x = Integer.parseInt(scanner.nextLine());
                                System.out.print("PILIH POSISI COL TANAMANAN YANG MAU DI DIG: ");
                                int y = Integer.parseInt(scanner.nextLine());

                                try {
                                    inventory.getDeck().dig(x, y);
                                } catch (Exception e) {
                                    System.out.print(e.getMessage());
                                }
                            }
                            else if (menu.equalsIgnoreCase("PAUSE") || menu.equalsIgnoreCase("3"))
                            {
                                isPaused = true;
                                pauseStartTime = System.currentTimeMillis(); 
                                   // Record the start time of the pause
                            }
                            else 
                            {
                                System.out.println("Perintah tidak valid! Ketik 'HELP' untuk melihat menu game.");
                            }
                        }
                        else
                        {
                            System.out.println("GAME DI PAUSE!");
                            System.out.println("1. CONTINUE");
                            System.out.println("2. SAVE");
                            System.out.println("3. MENU");
                            System.out.println("4. HELP");
                            System.out.print(">>");
                            String menu = scanner.nextLine();
                            System.out.println();

                            if (menu.equalsIgnoreCase("CONTINUE") || menu.equalsIgnoreCase("1"))
                            {
                                isPaused = false;
                                // totalPausedTime += System.currentTimeMillis() - pauseStartTime; // Add the pause duration to totalPausedTime
                                System.out.println("GAME CONTINUES!");
                                startThreads();
                            }
                            else if (menu.equalsIgnoreCase("SAVE") || menu.equalsIgnoreCase("2"))
                            {   
                                String filePath = "C:/Guntur/ITB/Akademik/Semester 4/OOP/tubes-oop-3/bin/";
                                System.out.println("MASUKKAN NAMA FILE UNTUK DI SAVE");
                                String inputFilePath = scanner.nextLine();
                                filePath += inputFilePath + ".json";
                                saveGame(filePath);
                            }
                            else if (menu.equalsIgnoreCase("MENU")|| menu.equalsIgnoreCase("3"))
                            {
                                System.out.println("BACK TO MENU!");

                                endGame();
                                
                                //threadWait();
                                start();
                                
                                
                            }
                            else if (menu.equalsIgnoreCase("HELP")|| menu.equalsIgnoreCase("4")) {
                                System.out.println("-----------MENU-----------");
                            }
                            else 
                            {
                                System.out.println("Perintah tidak valid! Ketik 'HELP' untuk melihat menu game.");
                            }
                        }
                    }
                }

            }
        });
        displayThread.start();
    }

    private void startThreads() {
        gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isGameOver) {
                    if (!isPaused) {
                        passedTime++;
                        lawn.INITIALIZE_ATTACK();
                        lawn.moveAll();
                        inventory.getDeck().plantCooldownThread();
                        try {
                            Thread.sleep(1000); // Sleep for 1 second
                        } catch (InterruptedException e) {
                            System.out.println("Move thread interrupted.");
                            return;
                        }
                    }
                }
            }
        });

        generatorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isGameOver) {
                    if (!isPaused) {
                        if ((passedTime >=0 && passedTime <= 100) || (passedTime >=201 && passedTime <= 300))
                        {           
                            try {
                                Sun.generateSun();
                                int delay = 5000 + (int) (Math.random() * 5000);
                                Thread.sleep(delay);
                            } catch (InterruptedException e) {
                                System.out.println("Sun generator thread interrupted.");
                                e.printStackTrace();
                                return;
                            }

                        }
                    }
                }
            }
        });

        spawnThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isGameOver) {
                    if (!isPaused) {
                        for (int i=0; i<6; i++)
                        {
                            if (passedTime >= 20 && passedTime <= 160)
                            {
                                Lawn.getLawn().get(i).get(9).spawnZombie();
                            }
                        }
                        try {
                            Thread.sleep(3000); // Sleep for 1 second
                        } catch (InterruptedException e) {
                            System.out.println("Spawn thread interrupted.");
                            return;
                        }
                    }
                }
            }
        });

        gameThread.start();
        spawnThread.start();
        generatorThread.start();
    }

    public void saveGame(String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        GameState gameState = new GameState(passedTime, Lawn.getLawn(), inventory, Sun.getSun());

        
        try {
            File file = new File(filePath);
            
            // Ensure the parent directories exist
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                boolean dirsCreated = file.getParentFile().mkdirs();
                if (!dirsCreated) {
                    System.err.println("Failed to create directories for path: " + filePath);
                    return;
                }
            }
            
            // Write the JSON file
            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(gameState, writer);
                System.out.println("Game state saved to " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Failed to save game state: " + e.getMessage());
            e.printStackTrace();  // Print stack trace for detailed error information
        }
    }
    

    public void loadGame(String filePath) {
        Gson gson = new Gson();
        
        try {
            File file = new File(filePath);
            
            // Check if the file exists
            if (!file.exists()) {
                System.err.println("File does not exist: " + filePath);
                return;
            }
            
            // Read the JSON data from the file
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                GameState gameState = gson.fromJson(reader, GameState.class);
                
                // Update your game state with the loaded data
                passedTime = gameState.passedTime;
                int tempZombieCount = 0;
                for (int i = 0; i < gameState.lawn.size(); i++)
                {
                    for (int j = 0; j < gameState.lawn.get(i).size(); j++)
                    {   
                        if (!gameState.lawn.get(i).get(j).hasPlant())
                        {
                            Lawn.getLawn().get(i).get(j).setPlant(null);
                        }
                        else
                        {
                            Lawn.getLawn().get(i).get(j).setPlant(gameState.lawn.get(i).get(j).getPlant());
                        }
                        if (gameState.lawn.get(i).get(j).hasZombie())
                        {
                            for (Zombie z : gameState.lawn.get(i).get(j).getZombies())
                            {
                                Lawn.getLawn().get(i).get(j).getZombies().add(z);
                                tempZombieCount++;
                            }
                        }
                    }
                }
                inventory = gameState.inventory;
                Sun.setSun(gameState.sun);
                Zombie.setZombieCount(tempZombieCount);
                
                System.out.println("Game state loaded from " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Failed to load game state: " + e.getMessage());
            e.printStackTrace();  // Print stack trace for detailed error information
        }
    }

    private void printGameInfo()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        System.out.println("\033[0;33mTime: \u001B[0m" + passedTime + (passedTime <= 100 ? " \u001B[33m(DAY)\u001B[0m" : " \u001B[34m(NIGHT)\u001B[0m"));

        System.out.println("\033[0;33mTotal Sun: \u001B[0m" + Sun.getSun());
        System.out.println("\033[0;33mJumlah Zombie di Map: \u001B[0m" + Zombie.getZombieCount() + ((GameCLI.getPassedTime() > 80 && GameCLI.getPassedTime() < 100) || GameCLI.getPassedTime() > 180 ? "  FLAG" : " NORMAL"));
    }

    private void printZombieList()
    {
        System.out.println("1. Normal Zombie");
        System.out.println("   health : 125");
        System.out.println("   attack_damage : 100");
        System.out.println("   attack_speed : 1");
        System.out.println("   is_aquatic : False");
        System.out.println("");
        System.out.println("2. Conehead Zombie");
        System.out.println("   health : 250");
        System.out.println("   attack_damage : 100");
        System.out.println("   attack_speed : 1");
        System.out.println("   is_aquatic : False");
        System.out.println("");
        System.out.println("3. Pole Vaulting Zombie");
        System.out.println("   health : 175");
        System.out.println("   attack_damage : 100");
        System.out.println("   attack_speed : 1");
        System.out.println("   is_aquatic : False");
        System.out.println("");
        System.out.println("4. Buckethead Zombie");
        System.out.println("   health : 300");
        System.out.println("   attack_damage : 100");
        System.out.println("   attack_speed : 1");
        System.out.println("   is_aquatic : False");
        System.out.println("");
        System.out.println("5. Ducky Tube Zombie");
        System.out.println("   health : 100");
        System.out.println("   attack_damage : 100");
        System.out.println("   attack_speed : 1");
        System.out.println("   is_aquatic : True");
        System.out.println("");
        System.out.println("6. Dolphin Rider Zombie");
        System.out.println("   health : 175");
        System.out.println("   attack_damage : 100");
        System.out.println("   attack_speed : 1");
        System.out.println("   is_aquatic : True");
        System.out.println("");
        System.out.println("7. Football Zombie");
        System.out.println("   health : 600");
        System.out.println("   attack_damage : 100");
        System.out.println("   attack_speed : 1");
        System.out.println("   is_aquatic : False");
        System.out.println("");
        System.out.println("8. Newspaper Zombie");
        System.out.println("   health : 250");
        System.out.println("   attack_damage : 100");
        System.out.println("   attack_speed : 1");
        System.out.println("   is_aquatic : False");
        System.out.println("");
        System.out.println("9. Wallnut Zombie");
        System.out.println("   health : 600");
        System.out.println("   attack_damage : 50");
        System.out.println("   attack_speed : 1");
        System.out.println("   is_aquatic : False");
        System.out.println("");
        System.out.println("10. Terminator Zombie");
        System.out.println("    health : 125");
        System.out.println("    attack_damage : 25");
        System.out.println("    attack_speed : 1");
        System.out.println("    is_aquatic : False");
        System.out.println("");
    }

    private void printPlantList()
    {
        System.out.println("1. Sunflower");
        System.out.println("   cost : 50");
        System.out.println("   health : 100");
        System.out.println("   attack_damage : 0");
        System.out.println("   attack_speed : 0");
        System.out.println("   range : 0");
        System.out.println("   cooldown : 10");
        System.out.println("");
        System.out.println("2. Peashooter");
        System.out.println("   cost : 100");
        System.out.println("   health : 100");
        System.out.println("   attack_damage : 25");
        System.out.println("   attack_speed : 4");
        System.out.println("   range : -1");
        System.out.println("   cooldown : 10");
        System.out.println("");
        System.out.println("3. Wallnut");
        System.out.println("   cost : 50");
        System.out.println("   health : 1000");
        System.out.println("   attack_damage : 0");
        System.out.println("   attack_speed : 0");
        System.out.println("   range : 0");
        System.out.println("   cooldown : 20");
        System.out.println("");
        System.out.println("4. Snowpea");
        System.out.println("   cost : 175");
        System.out.println("   health : 100");
        System.out.println("   attack_damage : 25");
        System.out.println("   attack_speed : 4");
        System.out.println("   range : -1");
        System.out.println("   cooldown : 10");
        System.out.println("");
        System.out.println("5. Squash");
        System.out.println("   cost : 50");
        System.out.println("   health : 100");
        System.out.println("   attack_damage : 5000");
        System.out.println("   attack_speed : 0");
        System.out.println("   range : 1");
        System.out.println("   cooldown : 20");
        System.out.println("");
        System.out.println("6. Lilypad");
        System.out.println("   cost : 25");
        System.out.println("   health : 100");
        System.out.println("   attack_damage : 0");
        System.out.println("   attack_speed : 0");
        System.out.println("   range : 0");
        System.out.println("   cooldown : 10");
        System.out.println("");
        System.out.println("7. Chomper");
        System.out.println("   cost : 150");
        System.out.println("   health : 100");
        System.out.println("   attack_damage : 50");
        System.out.println("   attack_speed : 10");
        System.out.println("   range : 1");
        System.out.println("   cooldown : 10");
        System.out.println("");
        System.out.println("8. Jalapeno");
        System.out.println("   cost : 125");
        System.out.println("   health : 100");
        System.out.println("   attack_damage : 5000");
        System.out.println("   attack_speed : 0");
        System.out.println("   range : -1");
        System.out.println("   cooldown : 10");
        System.out.println("");
        System.out.println("9. Kubis");
        System.out.println("   cost : 100");
        System.out.println("   health : 100");
        System.out.println("   attack_damage : 25");
        System.out.println("   attack_speed : 4");
        System.out.println("   range : -1");
        System.out.println("   cooldown : 10");
        System.out.println("");
        System.out.println("10. Repeater");
        System.out.println("   cost : 200");
        System.out.println("   health : 100");
        System.out.println("   attack_damage : 50");
        System.out.println("   attack_speed : 4");
        System.out.println("   range : -1");
        System.out.println("   cooldown : 10");
        System.out.println("");
    }

    public static long getPassedTime()
    {
        return passedTime;
    }

    private void stopThreads() {
        if (gameThread != null)
         {
            gameThread.interrupt();
            generatorThread.interrupt();
            displayThread.interrupt();
            spawnThread.interrupt();
        }
    }

    private void threadWait(){
        try {
            if (gameThread != null) {
                gameThread.join(); // Wait for gameThread to finish
            }
            if (generatorThread != null) {
                generatorThread.join(); // Wait for generatorThread to finish
            }
            if (displayThread != null) {
                displayThread.join(); // Wait for displayThread to finish
            }
            if (spawnThread != null) {
                spawnThread.join(); // Wait for spawnThread to finish
            }
        } catch (InterruptedException e) {
            System.out.println("Thread wait interrupted.");
            Thread.currentThread().interrupt(); // Restore interrupted state
        }

    }

    public static void endGame() {
        isGameOver = true;
        isStarted = false;
        //threadWait();
    }
}