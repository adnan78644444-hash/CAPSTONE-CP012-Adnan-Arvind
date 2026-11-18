import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        InventoryManager manager = new InventoryManager();
        Prediction prediction = new Prediction();

        // Sample inventory
        manager.addItem(new Item(101, "Laptop", 20, 5));
        manager.addItem(new Item(102, "Keyboard", 10, 4));
        manager.addItem(new Item(103, "Mouse", 3, 5));

        int choice = 0;

        do {

            System.out.println("\n======================================");
            System.out.println("              STOCKSENSE");
            System.out.println("     Predictive Inventory Reorder Engine");
            System.out.println("======================================");

            System.out.println("1. Add New Item");
            System.out.println("2. View All Items");
            System.out.println("3. Add Stock");
            System.out.println("4. Remove Stock");
            System.out.println("5. View Low Stock Items");
            System.out.println("6. Stock Movement History");
            System.out.println("7. Predict Inventory Requirements");
            System.out.println("8. Exit");

            System.out.print("\nEnter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Item ID: ");
                    int id = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter Item Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Quantity: ");
                    int quantity = sc.nextInt();

                    System.out.print("Enter Reorder Level: ");
                    int level = sc.nextInt();

                    if (quantity < 0 || level < 0 || name.trim().isEmpty()) {
                        System.out.println("Invalid item details!");
                        break;
                    }

                    manager.addItem(
                            new Item(id, name, quantity, level)
                    );

                    break;

                case 2:

                    manager.displayAllItems();

                    break;

                case 3:

                    System.out.print("Enter Item ID: ");
                    int addId = sc.nextInt();

                    System.out.print("Enter Quantity to Add: ");
                    int addQty = sc.nextInt();

                    manager.addStock(addId, addQty);

                    break;

                case 4:

                    System.out.print("Enter Item ID: ");
                    int removeId = sc.nextInt();

                    System.out.print("Enter Quantity to Remove: ");
                    int removeQty = sc.nextInt();

                    manager.removeStock(removeId, removeQty);

                    break;

                case 5:

                    manager.displayLowStock();

                    break;

                case 6:

                    manager.displayMovements();

                    break;

                case 7:

                    if (manager.getItems().isEmpty()) {
                        System.out.println("No items available!");
                        break;
                    }

                    System.out.println("\nAvailable Items:");

                    for (Item item : manager.getItems()) {

                        System.out.println(
                                item.getItemId() + " - " +
                                        item.getItemName()
                        );
                    }

                    System.out.print("Enter Item ID for Prediction: ");
                    int predictId = sc.nextInt();

                    Item selectedItem = manager.findItem(predictId);

                    if (selectedItem != null) {

                        prediction.generatePrediction(
                                selectedItem,
                                manager.getMovements()
                        );

                    } else {

                        System.out.println("Item not found!");
                    }

                    break;

                case 8:

                    System.out.println(
                            "Thank you for using StockSense!"
                    );

                    break;

                default:

                    System.out.println(
                            "Invalid choice! Please try again."
                    );
            }

        } while (choice != 8);

        sc.close();
    }
}