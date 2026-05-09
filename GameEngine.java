import java.util.HashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * GameEngine class - handles the game logic of the text adventure "Henriette's Feast".
 * It processes commands, manages the current room and movement history,
 * and interacts with the user interface.
 *
 * @author Michael Kolling and David J. Barnes + D. Bureau + Raphaël Quillaud
 * @version 2026.05.09
 */
public class GameEngine {
    private Player aPlayer;
    private Parser aParser;
    private HashMap<String, Room> aRooms;
    private UserInterface aGui;

    /**
     * Constructor for GameEngine.
     * Initializes the rooms, parser, and movement history.
     */
    public GameEngine() {
        this.aRooms = new HashMap<String, Room>();
        this.aParser = new Parser();
        this.createRooms();
    }

    /**
     * Creates all rooms of the village and connects them with exits.
     * Also sets the starting room (Henriette's kitchen).
     */
    private void createRooms() {
        // Rooms
        Room vKitchen       = new Room("in Henriette's warm, cosy kitchen","Images/kitchen.jpg");
        Room vLivingRoom    = new Room("in the living room, next to the Christmas tree","Images/livingRoom.jpg");
        Room vGarden        = new Room("in the snowy front garden of the house","Images/garden.jpg");
        Room vUnderTreeHouse = new Room("under the tree house, in the snow","Images/underTreeHouse.jpg");
        Room vTreeHouse     = new Room("in the children's tree house","Images/treeHouse.jpg");
        Room vFrontHouse    = new Room("in the street, right in front of Henriette's house", "Images/frontHouse.jpg");
        Room vThereseHouse  = new Room("in Thérèse's house, the neighbour. Look ! She left a cookie on the table with a note that says : 'You'll thank me later ;)'", "Images/thereseHouse.jpg");
        Room vHouseStreet   = new Room("in the quiet street in front of the houses", "Images/houseStreet.jpg");
        Room vBakery        = new Room("in the bakery that smells of fresh bread", "Images/bakery.jpg");
        Room vMainStreet    = new Room("on the main street decorated with garlands", "Images/mainStreet.jpg");
        Room vButcherShop   = new Room("in the butcher's shop, where a savoury smell of roasts fills the air", "Images/butcherShop.jpg");
        Room vMainSquare    = new Room("on the village square, under the huge sparkling Christmas tree", "Images/mainSquare.jpg");
        Room vGrocery       = new Room("in the small village grocery store", "Images/grocery.jpg");

        // Store Rooms in the map
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
        vMainSquare.setExit("west", vKitchen);

            // Grocery
        vGrocery.setExit("west", vMainSquare);

        // Items
        Item vRecipeBook = new Item("book", "Henriette's Christmas recipe book", 2);
        Item vFlour = new Item("flour","a bag of flour", 1);
        Item vButter = new Item("butter","a block of butter", 1);
        Item vSugar = new Item("sugar", "a packet of sugar", 1);
        Item vChocolate = new Item("chocolate", "a bar of dark chocolate", 1);
        Item vTurkey = new Item("turkey","a beautiful Christmas turkey", 5);
        Item vSpices = new Item("spices","a small box of Christmas spices", 1);
        Item vWoodLog = new Item("log","a dry log for the fireplace", 6);
        Item vPlate = new Item("plate", "a large serving plate, perfect for the Christmas dinner", 3);
        Item vCookie = new Item("cookie", "a delicious cookie made by love by Thérèse. Why is it so heavy though ? ...", 5);

        Beamer vBeamer = new Beamer("beamer", "a mysterious teleportation device", 3);

        // Place items in rooms
        vKitchen.addItem(vRecipeBook);
        vBakery.addItem(vFlour);
        vGrocery.addItem(vButter);
        vGrocery.addItem(vSpices);
        vGrocery.addItem(vSugar);
        vGrocery.addItem(vChocolate);
        vButcherShop.addItem(vTurkey);
        vUnderTreeHouse.addItem(vWoodLog);
        vThereseHouse.addItem(vCookie);
        vThereseHouse.addItem(vPlate);

        vTreeHouse.addItem(vBeamer);

        this.aPlayer = new Player("Henriette", vKitchen, 5);
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
            this.aGui.println("Go where ?");
            return;
        }

        String vDirection = pCommand.getSecondWord();
        Room vNextRoom = this.aPlayer.getCurrentRoom().getExit(vDirection);

        if (vNextRoom == null) {
            this.aGui.println("There is no door !");
            return;
        }

        this.aPlayer.goTo(vNextRoom);
        printLocationInfo();
    }

    /**
     * Executes the 'look' command. Prints the current room description
     * and the items present in the room.
     */
    private void look() {
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
    }

    /**
     * Executes the 'eat' command. If the player eats a cookie, their maximum 
     * weight capacity is doubled. Other items cannot be eaten.
     *
     * @param pCommand the command containing the name of the item to eat
     */
    private void eat(final Command pCommand) {
        String vItemName = pCommand.getSecondWord().toLowerCase();

        if (!this.aPlayer.hasItem(vItemName)) {
            this.aGui.println("You are not carrying any '" + vItemName + "'.");
            return;
        }

        if (vItemName.equals("cookie")) {
            this.aPlayer.drop(vItemName);
            this.aPlayer.setMaxWeight(this.aPlayer.getMaxWeight() * 2);
            this.aGui.println("You eat the cookie that Thérèse left you. You ask yourself why it is so heavy...");
            this.aGui.println("Your carrying capacity has doubled ! " + this.aPlayer.getWeightString());
        }
        else {
            this.aGui.println("You can't eat that ! Keep everything for the dinner...");
        }
    }

    /**
     * Moves the player back to the previous room, if available.
     * If there are no previous rooms in the navigation history, 
     * a message is displayed indicating that the action is not possible.
     * Otherwise, the last visited room is retrieved from the stack 
     * and set as the current room, and the location information is updated.
     */
    private void back() {
        if (!this.aPlayer.goBack()) {
            this.aGui.println("You can't go back !");
            return;
        }

        printLocationInfo();
    }

    /**
     * Picks up an item from the current room and adds it to the player's inventory.
     * @param pCommand the command containing the name of the item to take
     */
    private void take(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Take what ?");
            return;
        }

        String vItemName = pCommand.getSecondWord().toLowerCase();
        Item vItem = this.aPlayer.getCurrentRoom().getItem(vItemName);

        if (vItem == null) {
            this.aGui.println("There is no item called '" + vItemName + "' here.");
            return;
        }

        if (!this.aPlayer.pickUp(vItem)) {
            this.aGui.println("Too heavy ! " + this.aPlayer.getWeightString());
            return;
        }

        this.aPlayer.getCurrentRoom().removeItem(vItemName);
        this.aGui.println("You picked up : " + vItem.getName());
    }

    /**
     * Drops the item currently carried by the player into the current room.
     * @param pCommand the command containing the name of the item to drop
     */
    private void drop(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Drop what ? " + this.aPlayer.getInventoryString());
            return;
        }

        String vItemName = pCommand.getSecondWord().toLowerCase();
        Item vDropped = this.aPlayer.drop(vItemName);

        if (vDropped == null) {
            this.aGui.println("You are not carrying '" + vItemName + "'.");
            return;
        }

        this.aPlayer.getCurrentRoom().addItem(vDropped);
        this.aGui.println("You dropped : " + vDropped.getName());
    }

    /**
     * Charges the beamer with the current room.
     * @param pCommand the command containing the beamer name
     */
    private void charge(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Charge what ?");
            return;
        }

        String vItemName = pCommand.getSecondWord().toLowerCase();
        Item vItem = this.aPlayer.getItem(vItemName);

        if (vItem == null) {
            this.aGui.println("You are not carrying '" + vItemName + "'.");
            return;
        }

        if (!(vItem instanceof Beamer)) {
            this.aGui.println("You can't charge that !");
            return;
        }

        ((Beamer) vItem).charge(this.aPlayer.getCurrentRoom());
        this.aGui.println("Beamer charged ! It will bring you back " + this.aPlayer.getCurrentRoom().getDescription());
    }

    /**
     * Fires the beamer, teleporting the player to the charged room.
     * @param pCommand the command containing the beamer name
     */
    private void fire(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Fire what ?");
            return;
        }

        String vItemName = pCommand.getSecondWord().toLowerCase();
        Item vItem = this.aPlayer.getItem(vItemName);

        if (vItem == null) {
            this.aGui.println("You are not carrying '" + vItemName + "'.");
            return;
        }

        if (!(vItem instanceof Beamer)) {
            this.aGui.println("You can't fire that !");
            return;
        }

        Beamer vBeamer = (Beamer) vItem;

        if (!vBeamer.isCharged()) {
            this.aGui.println("The beamer is not charged !");
            return;
        }

        this.aPlayer.goTo(vBeamer.fire());
        this.aGui.println("*WHOOSH* You have been teleported !");
        printLocationInfo();
    }

    /**
     * Tests the commands provided in a specified file by interpreting and executing them sequentially.
     * The file should contain one command per line. Commands are ignored if the file is not found,
     * or if the command cannot be interpreted.
     * If the command does not have a second word specifying the file name, a usage message is displayed.
     * If the specified file cannot be located, an error message is displayed.
     *
     * @param pCommand the command containing the file name as the second word
     */
    private void test(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Test which file ? Usage : test <filename>");
            return;
        }

        String vFileName = pCommand.getSecondWord() + ".txt";

        try {
            Scanner vScanner = new Scanner(new File(vFileName));
            while (vScanner.hasNextLine()) {
                String vLine = vScanner.nextLine().trim();
                if (!vLine.isEmpty()) {
                    this.interpretCommand(vLine);
                }
            }
            vScanner.close();
        } catch (FileNotFoundException e) {
            this.aGui.println("Test file not found : " + vFileName);
        }
    }

    /**
     * Prints the welcome text and the initial location information.
     */
    private void printWelcome() {
        this.aGui.println("""
                
                
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
        this.aGui.println("\n" + this.aPlayer.getCurrentRoom().getLongDescription() + "\n");
        this.aGui.println("[ " + this.aPlayer.getRemainingMoves() + " moves remaining ]");

        if (this.aPlayer.getCurrentRoom().getImageName() != null) {
            this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
        }
    }

    /**
     * Prints a help message describing the game objective
     * and the available commands.
     */
    private void printHelp() {
        this.aGui.println("""
                You are Grandma Henriette in a small snowy village.
                You must find everything you need for the Christmas dinner.
                
                Your command words are:""");
        this.aGui.println(this.aParser.getCommandList());
    }

    /**
     * Interprets and executes the given command line.
     *
     * @param pCommandLine the command line entered by the user
     */
    public void interpretCommand(final String pCommandLine) {
        this.aGui.println("> " + pCommandLine);
        Command vCommand = this.aParser.getCommand(pCommandLine);

        if (vCommand.isUnknown()) {
            this.aGui.println("I don't know what you mean...");
            return;
        }

        String vCommandWord = vCommand.getCommandWord();

        if (!vCommandWord.equals("help") && !vCommandWord.equals("quit") && !vCommandWord.equals("test")) {
            this.aPlayer.incrementMoves();
            if (this.aPlayer.isOutOfTime()) {
                this.aGui.println("\nIt's midnight ! The family has arrived but there is nothing ready...");
                this.aGui.println("Henriette has failed. Game over !");
                this.endGame();
                return;
            }
            // Avertissement à 10 coups restants
            if (this.aPlayer.getRemainingMoves() == 10) {
                this.aGui.println("⚠ Hurry up ! Only 10 moves left before midnight !");
            }
        }

        switch (vCommandWord) {
            case "go" :
                this.goRoom(vCommand);
                break;

            case "help" :
                this.printHelp();
                break;

            case "look" :
                if (vCommand.hasSecondWord()) {
                    this.aGui.println("You can't look at something in particular.");
                }
                else {
                    this.look();
                }
                break;

            case "eat" :
                if (!vCommand.hasSecondWord()) {
                    this.aGui.println("Eat what ?");
                }
                else {
                    this.eat(vCommand);
                }
                break;

            case "quit" :
                if (vCommand.hasSecondWord()) {
                    this.aGui.println("Quit what ?");
                }
                else {
                    this.endGame();
                }
                break;

            case "back" :
                if (vCommand.hasSecondWord()) {
                    this.aGui.println("Back what ?");
                }
                else {
                    this.back();
                }
                break;

            case "test" :
                this.test(vCommand);
                break;

            case "take" :
                this.take(vCommand);
                break;

            case "drop" :
                this.drop(vCommand);
                break;

            case "items" :
                this.aGui.println(this.aPlayer.getInventoryString());
                this.aGui.println(this.aPlayer.getWeightString());
                break;

            case "charge" :
                this.charge(vCommand);
                break;

            case "fire" :
                this.fire(vCommand);
                break;

            default :
                this.aGui.println("Programmer error: command not recognised!");
                break;
        }
    }

    /**
     * Stores the graphical user interface and prints the welcome message.
     *
     * @param pUserInterface the graphical user interface of the game
     */
    public void setGUI(final UserInterface pUserInterface) {
        this.aGui = pUserInterface;
        this.printWelcome();
    }

    /**
     * Ends the game by printing a goodbye message and disabling input.
     */
    private void endGame() {
        this.aGui.println("Thank you for playing. Goodbye.");
        this.aGui.enable(false);
    }

} // GameEngine