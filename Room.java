/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *
 * @author Raphaël Quillaud
 */

import java.util.HashMap;
import java.util.Set;

public class Room
{
    private final String aDescription;
    private final HashMap<String, Room> aExits;

    public Room(final String pDescription) {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
    }
    
    public String getDescription() {
        return this.aDescription;
    }

    public void setExit(final String pDirection, final Room pNeighbor) {
        this.aExits.put(pDirection, pNeighbor);
    }

    public Room getExit(final String pDirection) {
        return this.aExits.get(pDirection);
    }
    public String getExitString() {
        String vExitString = "Exits :";
        Set<String> vKeys = this.aExits.keySet();

        for (String vDirection : vKeys) {
            vExitString += " " + vDirection;
        }
        return vExitString;
    }
} // Room
