import java.util.Map;
import java.util.HashMap;

public class Storage {
    private static Map<Integer, Order> orders = new HashMap<>();

    public static void put(Order order) {
        orders.put(order.id, order);
    }

    public static void delete(int id) {
        orders.remove(id);
    }

    public static int size() {
        return orders.size();
    }
}
