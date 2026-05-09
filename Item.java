
/**
 * Item class - represents an item in the game "Henriette's Feast".
 * Each item has a name, a description and a weight.
 *
 * @author Raphaël Quillaud
 * @version 2026.05.09
 */
public class Item {
    private String aDescription;
    private int aWeight;
    private String aName;

    /**
     * Constructor for Item.
     *
     * @param pName        the name of the item
     * @param pDescription the description of the item
     * @param pWeight      the weight of the item
     */
    public Item(final String pName,final String pDescription, final int pWeight) {
        this.aName = pName;
        this.aDescription = pDescription;
        this.aWeight = pWeight;
    }

    /**
     * Returns the name of the item.
     *
     * @return the item's name
     */
    public String getName() {
        return this.aName;
    }

    /**
     * Returns the description of the item.
     *
     * @return the item's description
     */
    public String getDescription() {
        return this.aDescription;
    }

    /**
     * Returns the weight of the item.
     *
     * @return the item's weight
     */
    public int getWeight() {
        return this.aWeight;
    }

    /**
     * Returns a long description of the item, including its name, 
     * description and weight.
     *
     * @return a detailed description of the item
     */
    public String getLongDescription() {
        return this.aName + " : " + this.aDescription + " (" + this.aWeight + " kg)";
    }
} // Item