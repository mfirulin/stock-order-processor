import java.util.Map;
import java.util.HashMap;

public class Storage {
    private static Map<String, Map<Integer, Order>> books = new HashMap<>();

    public static void put(Order order) {
        Map<Integer, Order> book = books.get(order.book);
        if (book == null) {
            book = new HashMap<Integer, Order>();
            books.put(order.book, book);
        }
        book.put(order.id, order);
    }

    public static void delete(String book, int id) {
        books.get(book).remove(id);
    }

    public static int size() {
        return books.size();
    }

    public static int max() {
        int max = 0;
        for (Map<Integer, Order> book : books.values()) {
            if (book.size() > max) {
                max = book.size();
            }
        }
        return max;
    }

    public static int min() {
        int min = Integer.MAX_VALUE;
        for (Map<Integer, Order> book : books.values()) {
            if (book.size() < min) {
                min = book.size();
            }
        }
        return min;
    }
}
