import java.util.Map;
import java.util.HashMap;

public class Storage {
    private static Map<String, Book> books = new HashMap<>();

    public static void put(Order order) {
        Book book = books.get(order.book);
        if (book == null) {
            book = new Book(order.book);
            books.put(order.book, book);
        }
        book.put(order);
    }

    public static Book get(String book) {
        return books.get(book);
    }

    public static int size() {
        return books.size();
    }

    public static void info() {
        books.values().forEach(System.out::println);
    }
}
