import java.util.HashMap;
import java.util.Set;

/**
 * Room class - represents a single location in the "Henriette's Feast" game.
 * Each room has a text description and exits to neighboring rooms,
 * stored in a HashMap that maps directions (e.g. "north", "up") to rooms.
 *
 * @author Raphaël Quillaud
 */
public class Room {
    private final String aDescription;
    private final HashMap<String, Room> aExits;

    /**
     * Creates a room with the given description and no exits yet.
     *
     * @param pDescription the textual description of this room
     */
    public Room(final String pDescription) {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
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
        return "You are " + this.aDescription + ".\n" + this.getExitString();
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