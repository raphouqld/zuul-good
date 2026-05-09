import java.util.HashMap;

/**
 * Manages a collection of items, accessible by name.
 * Used by both Room and Player to avoid code duplication.
 *
 * @author Raphaël Quillaud
 * @version 2026.05.09
 */
public class ItemList {
    private HashMap<String, Item> aItems;

    /**
     * Creates an empty item list.
     */
    public ItemList() {
        this.aItems = new HashMap<String, Item>();
    }

    /**
     * Adds an item to the list, using its lowercase name as key.
     *
     * @param pItem the item to add
     */
    public void addItem(final Item pItem) {
        this.aItems.put(pItem.getName().toLowerCase(), pItem);
    }

    /**
     * Returns the item with the given name, or null if not found.
     *
     * @param pName the name of the item (case-insensitive)
     * @return the requested item, or null if not found
     */
    public Item getItem(final String pName) {
        return this.aItems.get(pName.toLowerCase());
    }

    /**
     * Removes and returns the item with the given name.
     * Returns null if not found.
     *
     * @param pName the name of the item to remove
     * @return the removed item, or null if not found
     */
    public Item removeItem(final String pName) {
        return this.aItems.remove(pName.toLowerCase());
    }

    /**
     * Returns true if the list contains an item with the given name.
     *
     * @param pName the name to check
     * @return true if the item is present, false otherwise
     */
    public boolean contains(final String pName) {
        return this.aItems.containsKey(pName.toLowerCase());
    }

    /**
     * Returns true if the list is empty.
     *
     * @return true if the list has no items, false otherwise
     */
    public boolean isEmpty() {
        return this.aItems.isEmpty();
    }

    /**
     * Returns a string listing all items currently in the list.
     *
     * @return a description of the items
     */
    public String getItemString() {
        if (this.aItems.isEmpty()) return "No item here.";
        StringBuilder vResult = new StringBuilder("Items :");
        for (Item vItem : this.aItems.values()) {
            vResult.append("\n- ").append(vItem.getLongDescription());
        }
        return vResult.toString();
    }

    /**
     * Returns a string listing all items in the inventory.
     *
     * @return a description of the inventory
     */
    public String getInventoryString() {
        if (this.aItems.isEmpty()) return "You are not carrying anything.";
        StringBuilder vResult = new StringBuilder("Inventory :");
        for (Item vItem : this.aItems.values()) {
            vResult.append("\n- ").append(vItem.getLongDescription());
        }
        return vResult.toString();
    }
}