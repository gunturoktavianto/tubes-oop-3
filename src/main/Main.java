import java.util.ArrayList;

import AbstractClass.Tile;
import Game.Lawn;
import Plant.Jalapeno;
import Plant.Peashooter;
import Zombie.NormalZombie;
import Game.Game;
public class Main {
    public static void main(String[] args) {
       
        Lawn lawn = new Lawn();

        lawn.printLawn();
        Game.setSun(500);
        lawn.planting(1,6, new Jalapeno());
        lawn.printLawn();
        lawn.plantAction();
        lawn.printLawn();
        lawn.moveForward(); 
        lawn.printLawn();
        lawn.moveForward(); 
        lawn.printLawn();
        lawn.moveForward(); 
        lawn.printLawn();
        lawn.moveForward(); 
        lawn.printLawn();
        lawn.moveForward(); 
        lawn.printLawn();
        lawn.moveForward(); 
        lawn.printLawn();
        // lawn.printLawn();
        // lawn.moveForward(); 
        // lawn.printLawn();
        // lawn.moveForward();
        // lawn.printLawn();
        // lawn.moveForward();
        // lawn.printLawn();
        // lawn.moveForward();
        // lawn.printLawn();
        // lawn.zombieAction();
        // lawn.printLawn();
        // lawn.moveForward();
        // lawn.moveForward();
        // lawn.moveForward();
        // lawn.zombieAction();
        // lawn.printLawn();
    }
}
