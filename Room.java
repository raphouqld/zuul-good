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
    public Room aNorthExit;
    public Room aEastExit;
    public Room aSouthExit;
    public Room aWestExit;
//    private HashMap<String, Room> aExits;

    public Room(final String pDescription) {
        this.aDescription = pDescription;
//        this.aExits = new HashMap<String, Room>();
    }
    
    public String getDescription() {
        return this.aDescription;
    }

    public void setExits(final Room pNorth, final Room pEast, final Room pSouth, final Room pWest) {
        this.aNorthExit = pNorth;
        this.aEastExit = pEast;
        this.aSouthExit = pSouth;
        this.aWestExit = pWest;
    }
//    public void setExit(final String pDirection, final Room pNeighbor) {
//        this.aExits.put(pDirection, pNeighbor);
//    }

    public Room getExit(String pdirection) {
        return switch (pdirection) {
            case "north" -> this.aNorthExit;
            case "east" -> this.aEastExit;
            case "south" -> this.aSouthExit;
            case "west" -> this.aWestExit;
            default -> null;
        };
    }
    public String getExitString() {
        String vExitString = "Exits :";
        if (this.aNorthExit != null) {
            vExitString += " north";
        }
        if (this.aEastExit != null) {
            vExitString += " east";
        }
        if (this.aSouthExit != null) {
            vExitString += " south";
        }
        if (this.aWestExit != null) {
            vExitString += " west";
        }
        return vExitString;
    }
} // Room
