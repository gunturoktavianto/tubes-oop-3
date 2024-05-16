package main;

import java.util.Scanner;

import main.Game.*;

public class GameCLI extends Main {
    private final Scanner scanner;
    private Lawn lawn;
    private Deck deck;
    private static boolean isGameOver;
    private boolean isStarted;

    public GameCLI() {
        scanner = new Scanner(System.in); 
        this.lawn = Lawn.getLawnInstance();
        this.deck = new Deck();
        isStarted = false;
        isGameOver = false; 
    }

    public void start()
    {
        System.out.println("WELCOME TO PLANT vs ZOMBIES");

        while (!isStarted)
        {
            System.out.println("Masukkan Perintah");
            String menu = scanner.nextLine();
            System.out.println();
            
            if (menu.equals("START GAME")) 
            {
                isStarted = true;
            } 
            else if (menu.equals("EXIT")) 
            {
                System.out.println("Anda Keluar Dari Game");
                System.exit(0);
            } 
            else if (menu.equals("HELP")) 
            {
                System.out.println("-----------MENU-----------");
            } 
            else 
            {
                System.out.println("Perintah tidak valid! Ketik 'HELP' untuk melihat menu game.");
            }
        }

        while (isStarted)
        {
            Thread gameThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!isGameOver) 
                    {
                        lawn.INITIALIZE_ATTACK();
                        lawn.moveAll();
                        lawn.printLawn();
                        try {
                            Thread.sleep(1000); // Sleep for 1 second
                        } catch (InterruptedException e) {
                            System.out.println("Move thread interrupted.");
                            return;
                        }
                    }
                }
            });
    
            Thread generatorThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!isGameOver)
                    {
                        Sun.generateSun();
                    }
                } 
            });
    
            gameThread.start();
        }
    }

    public void initializeGame() {

    }

    public static void endGame() 
    {
        isGameOver = true;
    }
}
