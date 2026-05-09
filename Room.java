import java.util.HashMap;
import java.util.Set;

/**
 * Room class - represents a single location in the "Henriette's Feast" game.
 * Each room has a text description and exits to neighboring rooms,
 * stored in a HashMap that maps directions (e.g. "north", "up") to rooms.
 *
 * @author Raphaël Quillaud
 * @version 2026.05.08
 */
public class Room {
    private final String aDescription;
    private final HashMap<String, Room> aExits;
    private final String aImageName;
    private HashMap<String, Item> aItems;

    /**
     * Creates a room with the given description and no exits yet.
     *
     * @param pDescription the textual description of this room
     */
    public Room(final String pDescription, final String pImageName) {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
        this.aImageName = pImageName;
        this.aItems = new HashMap<String, Item>();
    }


    /**
     * Returns the name of the image associated with this room.
     *
     * @return the image name
     */
    public String getImageName() {
        return this.aImageName;
    }

    /**
     * Returns the description of this room.
     *
     * @return the room description
     */
    public String getDescription() {
        return this.aDescription;
    }

    /**
     * Returns a long description of this room, including its exits,
     * in the form: "You are in the kitchen.\nExits : north east".
     *
     * @return a detailed description of this room
     */
    public String getLongDescription() {
        return "You are " + this.aDescription + ".\n"
                + this.getExitString() + ".\n"
                + this.getItemString();
    }

    /**
     * Defines an exit from this room in the given direction.
     *
     * @param pDirection the direction of the exit (e.g. "north", "east", "up")
     * @param pNeighbor  the neighboring room in that direction
     */
    public void setExit(final String pDirection, final Room pNeighbor) {
        this.aExits.put(pDirection, pNeighbor);
    }

    /**
     * Returns the neighboring room in the given direction, or null
     * if there is no exit in that direction.
     *
     * @param pDirection the direction of the desired exit
     * @return the neighboring room, or null if none
     */
    public Room getExit(final String pDirection) {
        return this.aExits.get(pDirection);
    }

    /**
     * Adds an item to this room.
     *
     * @param pItem the item to add
     */
    public void addItem(final Item pItem) {
        this.aItems.put(pItem.getName().toLowerCase(), pItem);
    }

    /**
     * Returns the item with the given name, or null if not found.
     * @param pName the name of the item to retrieve
     * @return the Item object, or null if not found
     */
    public Item getItem(final String pName) {
        return this.aItems.get(pName);
    }

    /**
     * Removes the item with the given name from this room.
     * @param pName the name of the item to remove
     */
    public void removeItem(final String pName) {
        this.aItems.remove(pName);
    }

    /**
     * Returns a string listing all items currently in the room.
     *
     * @return a description of the items in the room
     */
    public String getItemString() {
        if (this.aItems.isEmpty()) {
            return "No item here.";
        }
        StringBuilder vItems = new StringBuilder("Items : ");
        for (Item vItem : this.aItems.values()) {
            vItems.append("\n- ").append(vItem.getLongDescription());
        }
        return vItems.toString();
    }

    /**
     * Builds and returns a string listing all available exits
     * from this room, in the form "Exits : north east up".
     *
     * @return a string describing the exits of this room
     */
    public String getExitString() {
        StringBuilder vExitString = new StringBuilder("Exits :");
        Set<String> vKeys = this.aExits.keySet();

        for (String vDirection : vKeys) {
            vExitString.append(" ").append(vDirection);
        }
        return vExitString.toString();
    }
} // Room