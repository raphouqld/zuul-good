/**
 * Classe Game - le moteur du jeu d'aventure Zuul.
 *
 * @author Raphaël Quillaud
 */
public class Game
{
    private Room aCurrentRoom;
    private Parser aParser;

    public Game() {
        this.createRooms();
        this.aParser = new Parser();
        play();
    }

    private void play() {
        this.printWelcome();
        boolean vFinished = false;

        while (!vFinished) {
            Command command = this.aParser.getCommand();
            vFinished = this.processCommand(command);
        }

        System.out.println("Thank you for playing. Goodbye.");
    }

    private void createRooms() {
        // Rooms
        Room vKitchen      = new Room("In Henriette's warm, cosy kitchen");
        Room vLivingRoom   = new Room("In the living room, next to the Christmas tree");
        Room vGarden       = new Room("In the snowy front garden of the house");
        Room vKidsHut      = new Room("Inside the grandchildren's wooden hut");
        Room vFrontHouse   = new Room("In the street, right in front of Henriette's house");
        Room vThereseHouse = new Room("In Thérèse's house, the neighbour");
        Room vHouseStreet  = new Room("In the quiet street in front of the houses");
        Room vBakery       = new Room("In the bakery that smells of fresh bread");
        Room vMainStreet   = new Room("On the main street decorated with garlands");
        Room vButcherShop  = new Room("In the butcher's shop, where a savoury smell of roasts fills the air");
        Room vMainSquare   = new Room("On the village square, under the huge sparkling Christmas tree");
        Room vGrocery      = new Room("In the small village grocery store");


        // Exits
            // Kitchen
        vKitchen.setExit("east", vLivingRoom);

            // Living room
        vLivingRoom.setExit("east", vGarden);
        vLivingRoom.setExit("west", vKitchen);

            // Garden
        vGarden.setExit("north", vKidsHut);
        vGarden.setExit("east", vFrontHouse);
        vGarden.setExit("south", vThereseHouse);
        vGarden.setExit("west", vLivingRoom);

            // Kids' hut
        vKidsHut.setExit("south", vGarden);

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

    private void printLocationInfo() {
        System.out.println("\n" + this.aCurrentRoom.getDescription());
        System.out.print(this.aCurrentRoom.getExitString());
        System.out.println("\n");
    }

    private void printHelp() {
        System.out.println("""
                You are Grandma Henriette in a small snowy village.
                You must find everything you need for the Christmas dinner.
                
                Your command words are:
                  go quit help""");
    }

    private boolean quit(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            System.out.println("Quit what ?");
            return false;
        }
        else {
            return true;
        }
    }

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
            case "quit" :
                return this.quit(pCommand);
            default :
                System.out.println("Erreur du programmeur : commande non reconnue !");
                return true;
        }
    }
} // Game
