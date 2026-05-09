/**
 * Game class - the main engine of the text adventure "Henriette's Feast".
 * It sets up the snowy village, creates all the rooms and their exits,
 * then runs the game loop where Grandma Henriette moves around the village
 * to gather everything she needs for the Christmas dinner.
 *
 * @author Raphaël Quillaud
 * @version 2026.05.09
 */

public class Game {
    private UserInterface aGui;
    private GameEngine aEngine;

    /**
     * Creates the game, initializes the rooms and the parser,
     * then starts the game loop.
     */
    public Game() {
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface(this.aEngine);
        this.aEngine.setGUI(this.aGui);
    }

    /**
     * Main method to start the game.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new Game();
    }
} // Game