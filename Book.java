import java.util.Objects;
import java.util.Map;
import java.util.HashMap;

public class Book {
    
    private static class Record {
        int sell;
        int buy;
    }

    private final String name;
    private Map<Float, Record> records = new HashMap<>();

    public Book(String name) {
        this.name = name;
    }

    public void put(Order order) {
        Record record = records.get(order.price);
        if (record == null) {
            record = new Record();
            records.put(order.price, record);
        }
        if (order.operation == Order.Operation.SELL) {
            record.sell += order.volume;
        } else {
            record.buy += order.volume;
        }
        
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Book[name=");
        builder.append(name);
        builder.append(' ');
        builder.append("size=");
        builder.append(records.size());
        // builder.append(' ');
        // for (Map.Entry<Float, Record> entry : records.entrySet()) {
        //     builder.append(entry.getKey());
        //     builder.append(':');
        //     builder.append(entry.getValue().sell);
        //     builder.append(',');
        //     builder.append(entry.getValue().buy);
        //     builder.append(' ');
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
        return name == otherBook.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
