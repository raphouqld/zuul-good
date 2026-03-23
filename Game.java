import java.util.HashMap;

/**
 * Game class - the main engine of the text adventure "Henriette's Feast".
 * It sets up the snowy village, creates all the rooms and their exits,
 * then runs the game loop where Grandma Henriette moves around the village
 * to gather everything she needs for the Christmas dinner.
 *
 * @author Raphaël Quillaud
 */

public class Game {
    private Room aCurrentRoom;
    private Parser aParser;
    private HashMap<String, Room> aRooms;

    /**
     * Creates the game, initializes the rooms and the parser,
     * then starts the game loop.
     */
    public Game() {
        this.aRooms = new HashMap<String, Room>();
        this.createRooms();
        this.aParser = new Parser();}

    /**
     * Main game loop: prints the welcome message, then repeatedly
     * reads and processes player commands until the game ends.
     */
    public void play() {
        this.printWelcome();
        boolean vFinished = false;

        while (!vFinished) {
            Command command = this.aParser.getCommand();
            vFinished = this.processCommand(command);
        }

        System.out.println("Thank you for playing. Goodbye.");
    }

    /**
     * Creates all rooms of the village and connects them with exits.
     * Also sets the starting room (Henriette's kitchen).
     */
    private void createRooms() {
        // Rooms
        Room vKitchen       = new Room("in Henriette's warm, cosy kitchen");
        Room vLivingRoom    = new Room("in the living room, next to the Christmas tree");
        Room vGarden        = new Room("in the snowy front garden of the house");
        Room vUnderTreeHouse = new Room("under the tree house, in the snow");
        Room vTreeHouse     = new Room("in the children's tree house, overlooking the snowy garden");
        Room vFrontHouse    = new Room("in the street, right in front of Henriette's house");
        Room vThereseHouse  = new Room("in Thérèse's house, the neighbour");
        Room vHouseStreet   = new Room("in the quiet street in front of the houses");
        Room vBakery        = new Room("in the bakery that smells of fresh bread");
        Room vMainStreet    = new Room("on the main street decorated with garlands");
        Room vButcherShop   = new Room("in the butcher's shop, where a savoury smell of roasts fills the air");
        Room vMainSquare    = new Room("on the village square, under the huge sparkling Christmas tree");
        Room vGrocery       = new Room("in the small village grocery store");

        // Store Rooms in map
        this.aRooms.put("kitchen", vKitchen);
        this.aRooms.put("livingRoom", vLivingRoom);
        this.aRooms.put("garden", vGarden);
        this.aRooms.put("underTreeHouse", vUnderTreeHouse);
        this.aRooms.put("treeHouse", vTreeHouse);
        this.aRooms.put("frontHouse", vFrontHouse);
        this.aRooms.put("thereseHouse", vThereseHouse);
        this.aRooms.put("houseStreet", vHouseStreet);
        this.aRooms.put("bakery", vBakery);
        this.aRooms.put("mainStreet", vMainStreet);
        this.aRooms.put("butcherShop", vButcherShop);
        this.aRooms.put("mainSquare", vMainSquare);
        this.aRooms.put("grocery", vGrocery);

        // Exits
            // Kitchen
        vKitchen.setExit("east", vLivingRoom);

            // Living room
        vLivingRoom.setExit("east", vGarden);
        vLivingRoom.setExit("west", vKitchen);

            // Garden
        vGarden.setExit("north", vUnderTreeHouse);
        vGarden.setExit("east", vFrontHouse);
        vGarden.setExit("west", vLivingRoom);

            // Under Treehouse
        vUnderTreeHouse.setExit("south", vGarden);
        vUnderTreeHouse.setExit("up", vTreeHouse);

            // Treehouse
        vTreeHouse.setExit("down", vUnderTreeHouse);

            // Front of the house
        vFrontHouse.setExit("south", vHouseStreet);
        vFrontHouse.setExit("west", vGarden);

            // Therese's house
        vThereseHouse.setExit("north", vGarden);
        vThereseHouse.setExit("east", vHouseStreet);

            // House street
        vHouseStreet.setExit("north", vFrontHouse);
        vHouseStreet.setExit("south", vMainStreet);
        vHouseStreet.setExit("west", vThereseHouse);

            // Bakery
        vBakery.setExit("east", vMainStreet);

            // Main street
        vMainStreet.setExit("north", vHouseStreet);
        vMainStreet.setExit("east", vButcherShop);
        vMainStreet.setExit("south", vMainSquare);
        vMainStreet.setExit("west", vBakery);

            // Butcher's shop
        vButcherShop.setExit("west", vMainStreet);

            // Main square
        vMainSquare.setExit("north", vMainStreet);
        vMainSquare.setExit("east", vGrocery);

            // Grocery
        vGrocery.setExit("west", vMainSquare);

        this.aCurrentRoom = vKitchen;
    }

    /**
     * Tries to go to the room in the direction given by the command.
     * If there is no second word or no exit in that direction,
     * prints an error message.
     *
     * @param pCommand the command containing the direction
     */
    private void goRoom(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            System.out.println("Go where ?");
            return;
        }

        String vDirection = pCommand.getSecondWord();
        Room vNextRoom = this.aCurrentRoom.getExit(vDirection);

        if (vNextRoom == null) {
            System.out.println("There is no door !");
            return;
        }

        this.aCurrentRoom = vNextRoom;
        printLocationInfo();
    }

    /**
     * Prints a detailed description of the current room,
     * including its exits.
     */
    private void look() {
        System.out.println(this.aCurrentRoom.getLongDescription());
    }

    /**
     * Permet au joueur de "manger".
     * Cette commande affiche simplement un message indiquant que le joueur a
     * mangé et n'a plus faim. Pour l'instant, elle n'a aucun effet sur l'état
     * interne du jeu (pas de gestion réelle de la faim).
     */
    private void eat() {
        System.out.println("You have eaten now and you are not hungry any more.");
    }

    /**
     * Prints the welcome text and the initial location information.
     */
    private void printWelcome() {
        System.out.println("""
                
                
                *****************************
                Welcome to "Henriette's Feast" !
                It is Christmas Eve in a small, snowy village.
                Grandma Henriette must prepare the big Christmas dinner
                for the whole family, but she suddenly realizes she forgot
                to do all the Christmas shopping.
                
                Explore the village, gather everything she needs
                for the dinner, and come back to the kitchen in time !
                
                Type 'help' if you need help.
                """);

        printLocationInfo();
    }

    /**
     * Prints the current room description and its available exits.
     */
    private void printLocationInfo() {
        System.out.println("\n" + this.aCurrentRoom.getDescription());
        System.out.print(this.aCurrentRoom.getExitString());
        System.out.println("\n");
    }

    /**
     * Prints a help message describing the game objective
     * and the available commands.
     */
    private void printHelp() {
        System.out.println("""
                You are Grandma Henriette in a small snowy village.
                You must find everything you need for the Christmas dinner.
                
                Your command words are:""");
        System.out.println(this.aParser.getCommandList());
    }

    /**
     * Tries to quit the game. If the command has a second word,
     * quitting is refused.
     *
     * @param pCommand the quit command
     * @return true if the game should end, false otherwise
     */
    private boolean quit(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            System.out.println("Quit what ?");
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Interprets and executes the given command.
     *
     * @param pCommand the command to process
     * @return true if the game should end, false otherwise
     */
    private boolean processCommand(final Command pCommand) {
        if (pCommand.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        String commandWord = pCommand.getCommandWord();
        switch (commandWord) {
            case "go" :
                this.goRoom(pCommand);
                return false;
            case "help" :
                this.printHelp();
                return false;
            case "look" :
                if (pCommand.hasSecondWord()) {
                    System.out.println("You can't look at something in particular.");
                }
                else {
                    this.look();
                }
                return false;
            case "eat" :
                if (pCommand.hasSecondWord()) {
                    System.out.println("You can't eat something in particular.");
                }
                else {
                    eat();
                }
                return false;
            case "quit" :
                return this.quit(pCommand);
            default :
                System.out.println("Erreur du programmeur : commande non reconnue !");
                return true;
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
} // Game