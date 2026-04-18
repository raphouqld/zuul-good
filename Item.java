
/**
 * Décrivez votre classe Item ici.
 *
 * @author Raphaël Quillaud
 * @version 18/04/26
 */
public class Item {
    private String aDescription;
    private int aWeight;

    public Item(final String pDescription, final int pWeight) {
        this.aDescription = pDescription;
        this.aWeight = pWeight;
    }

    public String getDescription() {
        return this.aDescription;
    }

    public int getWeight() {
        return this.aWeight;
    }

    public String getLongDescription() {
        return this.aDescription + " (" + this.aWeight + " kg)";
    }
}