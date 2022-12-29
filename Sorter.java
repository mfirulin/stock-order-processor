import java.util.Set;
import java.util.TreeSet;

public class Sorter {
    private static Set<Order> orders = new TreeSet<>();

    public static void put(Order order) {
        orders.add(order);
    }

    public static void delete(Order order) {
        orders.remove(order);
    }

    public static void sort() {
        orders.forEach(Storage::put);
    }
    
    public static int size() {
        return orders.size();
    }
}
