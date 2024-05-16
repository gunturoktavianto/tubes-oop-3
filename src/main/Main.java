
import Game.Lawn;

public class Main {
    public static void main(String[] args) {
       
        Lawn lawn = Lawn.getLawnInstance();

        Thread gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
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

        
        gameThread.start();
    }
}
