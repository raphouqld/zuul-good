
/**
 * Décrivez votre classe Item ici.
 *
 * @author Raphaël Quillaud
 * @version 18/04/26
 */
public class Item {
    private String aDescription;
    private int aWeight;
    private String aName;

    public Item(final String pName,final String pDescription, final int pWeight) {
        this.aName = pName;
        this.aDescription = pDescription;
        this.aWeight = pWeight;
    }

    public String getName() {
        return this.aName;
    }

    public String getDescription() {
        return this.aDescription;
    }

    public int getWeight() {
        return this.aWeight;
    }

    public String getLongDescription() {
        return this.aName + " : " + this.aDescription + " (" + this.aWeight + " kg)";
    }
} // Item