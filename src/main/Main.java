import java.util.ArrayList;

import AbstractClass.Tile;
import Game.Lawn;
import Zombie.NormalZombie;

public class Main {
    public static void main(String[] args) {
        Lawn lawn = new Lawn();
        

        lawn.printLawn();
        lawn.moveForward();
        
        lawn.moveForward();
        lawn.moveForward();
        lawn.moveForward();
        lawn.moveForward();
        lawn.printLawn();
    }
}
