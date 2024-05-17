package com.app;

import java.util.Scanner;

import com.Main;
import com.app.AbstractClass.Zombie;
import com.app.Game.*;

public class GameCLI extends Main {
    private final Scanner scanner;
    private Lawn lawn;
    private Deck deck;
    private static boolean isGameOver;
    private boolean isStarted;
    private boolean isPaused;
    private Thread gameThread, generatorThread, displayThread, spawnThread; 

    public GameCLI() {
        scanner = new Scanner(System.in);
        this.lawn = Lawn.getLawnInstance();
        this.deck = new Deck();
        isStarted = false;
        isGameOver = false;
        isPaused = false;
    }

    public void start() {
        System.out.println("WELCOME TO PLANT vs ZOMBIES");

        while (!isStarted) {
            synchronized (scanner) {
                if (!isPaused)
                {
                    System.out.println("Masukkan Perintah");
                    String menu = scanner.nextLine();
                    System.out.println();
    
                    if (menu.equals("START")) {
                        if (!isStarted) {
                            isStarted = true;
                            isPaused = false;
                            startThreads();
                            startDisplayThread();
                        }
                    } else if (menu.equals("EXIT")) {
                        System.out.println("Anda Keluar Dari Game");
                        System.exit(0);
                    } else if (menu.equals("HELP")) {
                        System.out.println("-----------MENU-----------");
                    } else {
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
                        // Wait for user input to trigger display
                        System.out.println("Press ENTER to display lawn and input commands.");
                        scanner.nextLine();

                        if (!isPaused) {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();

                            System.out.println("\033[0;33mTotal Sun: \u001B[0m" + Sun.getSun());
                            System.out.println("\033[0;33mJumlah Zombie di Map: \u001B[0m" + Zombie.getZombieCount());
                            lawn.printLawn();
                            System.out.println("Masukkan Perintah");

                            String menu = scanner.nextLine();
                            System.out.println();
                            
                            if (menu.equals(""))
                            {

                            }
                            else if (menu.equals("PAUSE")) 
                            {
                                isPaused = true;
                            }
                            else 
                            {
                                System.out.println("Perintah tidak valid! Ketik 'HELP' untuk melihat menu game.");
                            }
                        }
                        else
                        {
                            System.out.println("GAME DI PAUSE!, KETIK CONTINUE UNTUK MELANJUTKAN!");
                            String menu = scanner.nextLine();
                            System.out.println();

                            if (menu.equals("CONTINUE"))
                            {
                                isPaused = false;
                                System.out.println("GAME CONTINUES!");
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

    public static void endGame() {
        isGameOver = true;
    }
}