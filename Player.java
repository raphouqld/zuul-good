import java.util.HashMap;
import java.util.Stack;

/**
 * Represents the player in the game.
 * Stores the player's current room and navigation history.
 *
 * @author Raphaël Quillaud
 * @version 2026.05.09
 */
public class Player {
    private String aName;
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms;
    private ItemList aItems;
    private int aMaxWeight;
    private int aCurrentWeight;

    /**
     * Constructor for Player.
     * @param pName the player's name
     * @param pStartRoom the room where the player starts
     */
    public Player(final String pName, final Room pStartRoom, final int pMaxWeight) {
        this.aName = pName;
        this.aCurrentRoom = pStartRoom;
        this.aPreviousRooms = new Stack<Room>();
        this.aItems = new ItemList();
        this.aMaxWeight = pMaxWeight;
        this.aCurrentWeight = 0;
    }

    /**
     * Returns the player's name.
     * @return the name of the player
     */
    public String getName() {
        return this.aName;
    }

    /**
     * Returns the current room of the player.
     * @return the current Room object
     */
    public Room getCurrentRoom() {
        return this.aCurrentRoom;
    }

    /**
     * Moves the player to the given room and saves the previous one.
     * Does nothing if pRoom is null.
     *
     * @param pRoom the room to move to
     */
    public void goTo(final Room pRoom) {
        if (pRoom == null) return;
        this.aPreviousRooms.push(this.aCurrentRoom);
        this.aCurrentRoom = pRoom;
    }

    /**
     * Moves the player back to the previous room.
     * Returns false if there is no previous room.
     *
     * @return true if the player moved back, false otherwise
     */
    public boolean goBack() {
        if (this.aPreviousRooms.empty()) return false;
        this.aCurrentRoom = this.aPreviousRooms.pop();
        return true;
    }

    /**
     * Returns true if the player is carrying the item with the given name.
     * @param pName the name of the item to check
     * @return true if the item is in the inventory, false otherwise
     */
    public boolean hasItem(final String pName) {
        return this.aItems.contains(pName);
    }

    /**
     * Returns true if the player is carrying at least one item.
     * @return true if inventory is not empty, false otherwise
     */
    public boolean hasItems() {
        return !this.aItems.isEmpty();
    }

    /**
     * Makes the player pick up the given item.
     * @param pItem the Item to pick up
     */
    public boolean pickUp(final Item pItem) {
        if (this.aCurrentWeight + pItem.getWeight() > this.aMaxWeight) {
            return false;
        }

        this.aItems.addItem(pItem);
        this.aCurrentWeight += pItem.getWeight();
        return true;
    }

    /**
     * Removes and returns the item with the given name from the inventory.
     * Returns null if the player is not carrying that item.
     * @param pName the name of the item to drop
     * @return the dropped Item, or null if not found
     */
    public Item drop(final String pName) {
        Item vDropped = this.aItems.removeItem(pName);
        if (vDropped != null) {
            this.aCurrentWeight -= vDropped.getWeight();
        }
        return vDropped;
    }

    /**
     * Returns a string listing all items carried by the player.
     * @return a description of the player's inventory
     */
    public String getInventoryString() {
        return this.aItems.getInventoryString();
    }

    public String getWeightString() {
        return "Weight carried : " + this.aCurrentWeight + "/" + this.aMaxWeight;
    }
}