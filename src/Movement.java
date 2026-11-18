import java.time.LocalDateTime;

public class Movement {

    private int itemId;
    private String movementType;
    private int quantity;
    private LocalDateTime date;

    public Movement(int itemId, String movementType, int quantity) {
        this.itemId = itemId;
        this.movementType = movementType;
        this.quantity = quantity;
        this.date = LocalDateTime.now();
    }

    public int getItemId() {
        return itemId;
    }

    public String getMovementType() {
        return movementType;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void displayMovement() {
        System.out.println("--------------------------------");
        System.out.println("Item ID       : " + itemId);
        System.out.println("Movement Type : " + movementType);
        System.out.println("Quantity      : " + quantity);
        System.out.println("Date          : " + date);
        System.out.println("--------------------------------");
    }
}