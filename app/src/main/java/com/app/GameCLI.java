package com.app;

import java.util.Scanner;

import com.Main;
import com.app.AbstractClass.Zombie;
import com.app.Game.*;

public class GameCLI extends Main {
    private final Scanner   scanner;
    private long            startTime, passedTime, pauseStartTime, totalPausedTime;
    private Lawn            lawn;
    private Inventory       inventory;
    private static boolean  isGameOver;
    private boolean         isStarted, isPaused;
    private Thread          gameThread, generatorThread, displayThread, spawnThread; 

    public GameCLI() {
        scanner = new Scanner(System.in);
        this.lawn = Lawn.getLawnInstance();
        inventory = new Inventory();
        isStarted = false;
        isGameOver = false;
        isPaused = false;
    }

    public void start() {
        System.out.println("WELCOME TO PLANT vs ZOMBIES");

        while (true)
        {
            while (!isStarted) {
                synchronized (scanner) {
                    System.out.println();
                    System.out.println("MAIN MENU");
                    System.out.println("1. Start");
                    System.out.println("2. Help");
                    System.out.println("3. Plants List");
                    System.out.println("4. Zombies List");
                    System.out.println("5. Exit");
                    System.out.println("Masukkan Perintah");
                    System.out.print(">>");
                    String menu = scanner.nextLine();
                    System.out.println();
        
                    if (menu.equals("START") || menu.equals("Start") || menu.equals("1")) {
                        System.out.println("WELCOME TO THE GAME");
                        System.out.println("SILAHKAN ATUR DECK TERLEBIH DAHULU");
                        boolean inven = true;
                        while(inven && !isStarted)
                        {
                            
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
                            else if (invenMenu.equals("SWITCH DECK") || invenMenu.equals("Switch Deck") || invenMenu.equals("3"))
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
                            else if (invenMenu.equals("SWITCH INVENTORY") || invenMenu.equals("Switch Inventory") || invenMenu.equals("4"))
                            {
                                System.out.print("PILIH INDEX TANAMAN 1 UNTUK DITUKAR KE INVENTORY! :");
                                int plantIdx1 = Integer.parseInt(scanner.nextLine());
                                System.out.print("PILIH INDEX TANAMAN 2 UNTUK DITUKAR KE INVENTORY! :");
                                int plantIdx2 = Integer.parseInt(scanner.nextLine());
                                try {
                                    inventory.switchPlacement(plantIdx1, plantIdx2);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            
                            else if (invenMenu.equals("SET DECK") || invenMenu.equals("Set Deck") || invenMenu.equals("5"))
                            {
                                while(!inventory.getDeck().isFull())
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
                            else if (invenMenu.equals("DELETE DECK") || invenMenu.equals("Delete Deck") || invenMenu.equals("6"))
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
                            else if (invenMenu.equals("START GAME") || invenMenu.equals("Start Game") || invenMenu.equals("7"))
                            {
                                if (inventory.getDeck().isEmpty())  
                                {
                                    System.out.println("DECK KOSONG, GAME TIDAK DAPAT DIMULAI!");
                                }
                                else
                                {
                                    if (!isStarted) {
                                        isStarted = true;
                                        isPaused = false;
                                        startTime = System.currentTimeMillis();
                                        startThreads();
                                        startDisplayThread();
                                    }
                                }
                            } 
                            else if (invenMenu.equals("BACK") || invenMenu.equals("Back") || invenMenu.equals("8"))
                            {
                                System.out.println("Anda Kembali ke tampilan utama");
                                inven = false;
                            } 
                        }
                    }
                    // else if (menu.equals("INVENTORY"))
                    // {
                    //     inventory.printInventory();
                    // }
                    // else if (menu.equals("SWITCHINVENTORY"))
                    // {
                    //     System.out.print("PILIH INDEX TANAMAN 1 UNTUK DITUKAR KE INVENTORY! :");
                    //     int plantIdx1 = Integer.parseInt(scanner.nextLine());
                    //     System.out.print("PILIH INDEX TANAMAN 1 UNTUK DITUKAR KE INVENTORY! :");
                    //     int plantIdx2 = Integer.parseInt(scanner.nextLine());
                    //     try {
                    //         inventory.switchPlacement(plantIdx1, plantIdx2);
                    //     } catch (Exception e) {
                    //         System.out.println(e.getMessage());
                    //     }
                    // }
                    // else if (menu.equals("DECK"))
                    // {
                    //     inventory.getDeck().showDeckRev();
                    // }
                    // else if (menu.equals("SETDECK"))
                    // {
                    //     inventory.printInventory();
                    //     inventory.getDeck().showDeck();
                    //     System.out.print("PILIH INDEX TANAMAN UNTUK DIMASUKKAN KE DECK! :");
                    //     int plantIdx = Integer.parseInt(scanner.nextLine());
                    //     System.out.print("PILIH INDEX PADA DECK! :");
                    //     int deckIdx = Integer.parseInt(scanner.nextLine());
                    //     try {
                    //         inventory.setPlantInDeck(deckIdx, plantIdx);
                    //     } catch (Exception e) {
                    //         System.out.println(e.getMessage());
                    //     }
                    // }
                    // else if (menu.equals("DELETEDECK"))
                    // {
                    //     inventory.getDeck().showDeck();
                    //     System.out.print("PILIH INDEX TANAMAN UNTUK DIREMOVE KE DECK! :");
                    //     int plantIdx = Integer.parseInt(scanner.nextLine());
                    //     try {
                    //         inventory.deletePlantFromDeck(plantIdx);
                    //     } catch (Exception e) {
                    //         System.out.println(e.getMessage());
                    //     }
                    // } 
                    // else if (menu.equals("SWITCHDECK"))
                    // {
                    //     System.out.print("PILIH INDEX TANAMAN 1 UNTUK DITUKAR KE DECK! :");
                    //     int plantIdx1 = Integer.parseInt(scanner.nextLine());
                    //     System.out.print("PILIH INDEX TANAMAN 2 UNTUK DITUKAR KE DECK! :");
                    //     int plantIdx2 = Integer.parseInt(scanner.nextLine());
                    //     try {
                    //         inventory.swapDeckPlacement(plantIdx1, plantIdx2);
                    //     } catch (Exception e) {
                    //         System.out.println(e.getMessage());
                    //     }
                    // }
                    
                    else if (menu.equals("HELP") || menu.equals("Help") || menu.equals("2")) 
                    {
                        System.out.println("-----------MENU-----------");
                        System.out.println("1. START");
                        System.out.println("2. HELP");
                        System.out.println("3. Plants List");
                        System.out.println("4. Zombies List");
                        System.out.println("5. EXIT");
                    } 
                    else if (menu.equals("Plants List") || menu.equals("3")) 
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
                    else if (menu.equals("Zombies List") || menu.equals("4")) 
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
                        System.out.println("10. Lorem Ipsum");
                        System.out.println("   health : 600");
                        System.out.println("   attack_damage : 50");
                        System.out.println("   attack_speed : 1");
                        System.out.println("   is_aquatic : False");
                        System.out.println("");
                    } 
                    else if (menu.equals("EXIT") || menu.equals("Exit") || menu.equals("5") ) 
                    {
                        System.out.println("Anda Keluar Dari Game");
                        System.exit(0);
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
                            passedTime = (System.currentTimeMillis() - startTime - totalPausedTime) / 1000;
        
                            printGameInfo();
                            lawn.printLawn();
                            
                            System.out.println("Press ENTER to display lawn or input commands.");
                            System.out.print(">>");
                            String menu = scanner.nextLine();
                            
                            if (menu.equals(""))                       // Will just print the map
                            {
                                
                            }
                            else if (menu.equals("PLANT"))
                            {
                                inventory.getDeck().showDeck();
                                System.out.println("PILIH TANAMANAN YANG MAU DI PLANT: ");
                                int plantIdx = Integer.parseInt(scanner.nextLine());
                                System.out.print("PILIH POSISI ROW TANAMANAN YANG MAU DI PLANT: ");
                                int x = Integer.parseInt(scanner.nextLine());
                                System.out.print("PILIH POSISI ROW TANAMANAN YANG MAU DI PLANT: ");
                                int y = Integer.parseInt(scanner.nextLine());

                                try {
                                    inventory.getDeck().plant(x, y, plantIdx);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            else if (menu.equals("PAUSE")) 
                            {
                                isPaused = true;
                                pauseStartTime = System.currentTimeMillis();    // Record the start time of the pause
                            }
                            else 
                            {
                                System.out.println("Perintah tidak valid! Ketik 'HELP' untuk melihat menu game.");
                            }
                        }
                        else
                        {
                            System.out.println("GAME DI PAUSE!, KETIK CONTINUE UNTUK MELANJUTKAN!");
                            System.out.print(">>");
                            String menu = scanner.nextLine();
                            System.out.println();

                            if (menu.equals("CONTINUE"))
                            {
                                isPaused = false;
                                totalPausedTime += System.currentTimeMillis() - pauseStartTime; // Add the pause duration to totalPausedTime
                                System.out.println("GAME CONTINUES!");
                            }
                            else if (menu.equals("MENU"))
                            {
                                System.out.println("BACK TO MENU!");
                                isStarted = true;
                                isGameOver = true;
                            }
                            else if (menu.equals("HELP")) {
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
                        Sun.generateSun();
                        try {
                            int delay = 5000 + (int) (Math.random() * 5000);
                            Thread.sleep(delay);
                        } catch (InterruptedException e) {
                            System.out.println("Sun generator thread interrupted.");
                            return;
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
                            Lawn.getLawn().get(i).get(9).spawnZombie();
                        }
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

        gameThread.start();
        spawnThread.start();
        generatorThread.start();
    }

    private void printGameInfo()
    {
        // System.out.print("\033[H\033[2J");
        // System.out.flush();
        
        System.out.println("\033[0;33mTime: \u001B[0m" + passedTime);

        System.out.println("\033[0;33mTotal Sun: \u001B[0m" + Sun.getSun());
        System.out.println("\033[0;33mJumlah Zombie di Map: \u001B[0m" + Zombie.getZombieCount());
    }

    public static void endGame() {
        isGameOver = true;
    }
}