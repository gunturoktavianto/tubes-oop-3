package Game;

public class Game {
    private Lawn lawn;
    private Deck deck;
    private Inventory inventory;
    private static int sun;

    public Game() { 
        this.lawn = new Lawn();
        this.deck = new Deck();
        this.inventory = new Inventory(); 
    }

    public void initializeGame() {

    }

    public static int getSun()
    {
        return sun;
    }

    public static void setSun(int a)
    {
        sun = a;
    }
}
