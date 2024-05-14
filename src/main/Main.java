
import Game.Lawn;

public class Main {
    public static void main(String[] args) {
       
        Lawn lawn = Lawn.getLawnInstance();

        Thread moveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    lawn.moveAll();
                    try {
                        Thread.sleep(1000); // Sleep for 1 second
                    } catch (InterruptedException e) {
                        System.out.println("Move thread interrupted.");
                        return;
                    }
                }
            }
        });


        Thread printThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    lawn.printLawn();
                    try {
                        Thread.sleep(1000); // Sleep for 5 seconds
                    } catch (InterruptedException e) {
                        System.out.println("Print thread interrupted.");
                        return;
                    }
                }
            }
        });

        Thread attackThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    lawn.INITIALIZE_ATTACK();
                    try {
                        Thread.sleep(1000); // Sleep for 5 seconds
                    } catch (InterruptedException e) {
                        System.out.println("Print thread interrupted.");
                        return;
                    }
                }
            }
        });

        // Start both threads
        moveThread.start();
        attackThread.start();
        printThread.start();
    }
}
