import java.util.Map;
import java.util.HashMap;

public class Storage {
    private static Map<String, Map<Integer, Order>> books = new HashMap<>();

    public static void put(Order order) {
        Map<Integer, Order> orders = books.get(order.book);
        if (orders == null) {
            orders = new HashMap<Integer, Order>();
            books.put(order.book, orders);
        }
        orders.put(order.id, order);
    }

    public static void delete(String book, int id) {
        books.get(book).remove(id);
    }

    public static int size() {
        return books.size();
    }
}
