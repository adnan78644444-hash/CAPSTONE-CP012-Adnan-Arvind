public class Item {

    private int itemId;
    private String itemName;
    private int quantity;
    private int reorderLevel;

    public Item(int itemId, String itemName, int quantity, int reorderLevel) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void addStock(int amount) {
        if (amount > 0) {
            quantity += amount;
        }
    }

    public boolean removeStock(int amount) {
        if (amount > 0 && amount <= quantity) {
            quantity -= amount;
            return true;
        }
        return false;
    }

    public boolean isLowStock() {
        return quantity <= reorderLevel;
    }

    public void displayItem() {
        System.out.println("--------------------------------");
        System.out.println("Item ID       : " + itemId);
        System.out.println("Item Name     : " + itemName);
        System.out.println("Quantity      : " + quantity);
        System.out.println("Reorder Level : " + reorderLevel);
        System.out.println("Status        : " +
                (isLowStock() ? "LOW STOCK" : "Sufficient Stock"));
        System.out.println("--------------------------------");
    }
}