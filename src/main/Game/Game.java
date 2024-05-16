package Game;

import java.util.Scanner;

public class Game {
    // private final Scanner scanner;
    private Lawn lawn;
    private Deck deck;
    private boolean isGameOver;
    private int Timer; //mungkin?

    public Game() { 
        this.lawn = Lawn.getLawnInstance();
        this.deck = new Deck();
        this.isGameOver = false; 
    }

    public void initializeGame() {
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

    public void handleInput() {

    }

    public void endGame() 
    {
        isGameOver = true;
    }
}
