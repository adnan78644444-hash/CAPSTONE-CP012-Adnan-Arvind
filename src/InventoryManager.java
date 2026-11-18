import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private List<Item> items = new ArrayList<>();
    private List<Movement> movements = new ArrayList<>();

    // Add item
    public void addItem(Item item) {

        if (findItem(item.getItemId()) != null) {
            System.out.println("Item ID already exists!");
            return;
        }

        items.add(item);
        System.out.println("Item added successfully!");
    }

    // Find item
    public Item findItem(int itemId) {

        for (Item item : items) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }

        return null;
    }

    // Display all items
    public void displayAllItems() {

        if (items.isEmpty()) {
            System.out.println("No items available.");
            return;
        }

        for (Item item : items) {
            item.displayItem();
        }
    }

    // Add stock
    public void addStock(int itemId, int quantity) {

        Item item = findItem(itemId);

        if (item != null && quantity > 0) {

            item.addStock(quantity);

            movements.add(new Movement(itemId, "IN", quantity));

            System.out.println("Stock added successfully!");

        } else {
            System.out.println("Invalid item ID or quantity!");
        }
    }

    // Remove stock
    public void removeStock(int itemId, int quantity) {

        Item item = findItem(itemId);

        if (item != null && item.removeStock(quantity)) {

            movements.add(new Movement(itemId, "OUT", quantity));

            System.out.println("Stock removed successfully!");

        } else {
            System.out.println("Invalid item or insufficient stock!");
        }
    }

    // Display low stock
    public void displayLowStock() {

        boolean found = false;

        System.out.println("\n========== LOW STOCK ITEMS ==========");

        for (Item item : items) {

            if (item.isLowStock()) {
                item.displayItem();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No low-stock items found.");
        }
    }

    // Return item list
    public List<Item> getItems() {
        return items;
    }

    // Return movement list
    public List<Movement> getMovements() {
        return movements;
    }

    // Display movement history
    public void displayMovements() {

        if (movements.isEmpty()) {
            System.out.println("No stock movements recorded.");
            return;
        }

        System.out.println("\n========== STOCK MOVEMENTS ==========");

        for (Movement movement : movements) {
            movement.displayMovement();
        }
    }
}