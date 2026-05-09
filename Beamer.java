/**
 * A beamer is a special item that can be charged and fired.
 * When charged, it memorizes the current room.
 * When fired, it teleports the player back to that room.
 *
 * @author Raphaël Quillaud
 * @version 2026.05.09
 */
public class Beamer extends Item {
    private Room aChargedRoom;

    /**
     * Constructor for Beamer.
     * @param pName the name of the beamer
     * @param pDescription the description of the beamer
     * @param pWeight the weight of the beamer
     */
    public Beamer(final String pName, final String pDescription, final int pWeight) {
        super(pName, pDescription, pWeight);
        this.aChargedRoom = null;
    }

    /**
     * Charges the beamer with the given room.
     * @param pRoom the room to memorize
     */
    public void charge(final Room pRoom) {
        this.aChargedRoom = pRoom;
    }

    /**
     * Returns true if the beamer is charged.
     */
    public boolean isCharged() {
        return this.aChargedRoom != null;
    }

    /**
     * Fires the beamer and returns the memorized room.
     * The beamer is reset after firing.
     * Returns null if not charged.
     */
    public Room fire() {
        Room vRoom = this.aChargedRoom;
        this.aChargedRoom = null;
        return vRoom;
    }
} // Beamer