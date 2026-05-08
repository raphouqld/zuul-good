import java.util.Stack;

/**
 * Represents the player in the game.
 * Stores the player's current room and navigation history.
 *
 * @author Raphaël Quillaud
 * @version 2026.05.08
 */
public class Player {
    private String aName;
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms;
    private Item aItem; // l'item porté par le joueur (null si rien)

    public Player(final String pName, final Room pStartRoom) {
        this.aName = pName;
        this.aCurrentRoom = pStartRoom;
        this.aPreviousRooms = new Stack<Room>();
    }

    /**
     * Returns the player's name.
     */
    public String getName() {
        return this.aName;
    }

    /**
     * Returns the current room of the player.
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
     * Returns the item currently carried by the player, or null if none.
     */
    public Item getItem() {
        return this.aItem;
    }

    /**
     * Returns true if the player is already carrying an item.
     */
    public boolean hasItem() {
        return this.aItem != null;
    }

    /**
     * Makes the player pick up the given item.
     * @param pItem the item to pick up
     */
    public void pickUp(final Item pItem) {
        this.aItem = pItem;
    }

    /**
     * Makes the player drop the item they are carrying.
     * Returns the dropped item, or null if the player had nothing.
     */
    public Item drop() {
        Item vDropped = this.aItem;
        this.aItem = null;
        return vDropped;
    }
}