import java.util.Objects;
import java.util.Map;
import java.util.TreeMap;

public class Book {
    
    private static class Pair {
        int sell;
        int buy;
    }

    private final String name;
    private Map<Float, Pair> pairs = new TreeMap<>();

    public Book(String name) {
        this.name = name;
    }

    public void put(Order order) {
        Pair pair = pairs.get(order.price);
        if (pair == null) {
            pair = new Pair();
            pairs.put(order.price, pair);
        }

        if (order.operation == Order.Operation.SELL) {
            pair.sell += order.volume;
        } else {
            pair.buy += order.volume;
        }
        
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Book[name=");
        builder.append(name);
        builder.append(' ');
        builder.append("size=");
        builder.append(pairs.size());
        // builder.append('\n');
        // for (Map.Entry<Float, Pair> entry : pairs.entrySet()) {
        //     builder.append(
        //         String.format("%6d %7.1f %6d\n", 
        //         entry.getValue().buy, entry.getKey(), entry.getValue().sell)
        //     );
        // }
        builder.append(']');
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;

        Book otherBook = (Book)other;
        return Objects.equals(name, otherBook.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
