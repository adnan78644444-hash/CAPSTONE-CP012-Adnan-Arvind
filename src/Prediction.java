import java.time.LocalDateTime;
import java.util.List;

public class Prediction {

    // Calculate average daily usage over the last 30 days
    public double calculateDailyUsage(int itemId, List<Movement> movements) {

        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);

        int totalUsage = 0;

        for (Movement movement : movements) {

            if (movement.getItemId() == itemId
                    && movement.getMovementType().equals("OUT")
                    && !movement.getDate().isBefore(thirtyDaysAgo)) {

                totalUsage += movement.getQuantity();
            }
        }

        return totalUsage / 30.0;
    }

    // Predict days until stock reaches reorder level
    public int predictDaysUntilReorder(Item item, double dailyUsage) {

        if (dailyUsage <= 0) {
            return -1;
        }

        int availableStock =
                item.getQuantity() - item.getReorderLevel();

        if (availableStock <= 0) {
            return 0;
        }

        return (int) Math.ceil(availableStock / dailyUsage);
    }

    // Calculate suggested reorder quantity
    public int calculateReorderQuantity(Item item) {

        int targetStock = item.getReorderLevel() * 2;

        return Math.max(0, targetStock - item.getQuantity());
    }

    // Generate inventory prediction report
    public void generatePrediction(Item item, List<Movement> movements) {

        double dailyUsage =
                calculateDailyUsage(item.getItemId(), movements);

        int days =
                predictDaysUntilReorder(item, dailyUsage);

        int reorderQuantity =
                calculateReorderQuantity(item);

        System.out.println("\n========== STOCK PREDICTION ==========");

        System.out.println("Product Name        : " + item.getItemName());

        System.out.println("Current Stock       : " + item.getQuantity());

        System.out.printf("Average Daily Usage : %.2f%n", dailyUsage);

        if (days == -1) {

            System.out.println(
                    "Prediction          : Insufficient usage data");

        } else if (days == 0) {

            System.out.println(
                    "Prediction          : Reorder immediately");

        } else {

            System.out.println(
                    "Days Until Reorder  : " + days + " days");
        }

        System.out.println(
                "Suggested Reorder   : " + reorderQuantity + " units");

        System.out.println("======================================");
    }
}