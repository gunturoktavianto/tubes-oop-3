package Game;

public class Game {
    private Lawn lawn;
    private Deck deck;
    private Inventory inventory;

    public Game() {
        this.lawn = new Lawn();
        this.deck = new Deck();
        this.inventory = new Inventory(); 
    }

    public void initializeGame() {

    }
}
