import Game.Lawn;
import Zombie.NormalZombie;

public class Main {
    public static void main(String[] args) {
        Lawn lawn = new Lawn();
        lawn.printLawn();
        NormalZombie z1 = new NormalZombie();
        z1.moveForward();
    }
}
