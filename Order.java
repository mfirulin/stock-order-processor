import java.util.Objects;

public class Order implements Comparable<Order> {
    public enum Operation { BUY, SELL }

    public final int id;
    public final String book;
    public final Operation operation;
    public final float price;
    public final int volume;

    public Order(int id) {
        this(id, "", Operation.BUY, 0.0f, 0);
    }

    public Order(int id, String book, Operation operation, float price, int volume) {
        this.id = id;
        this.book = book;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
    }

    @Override
    public int compareTo(Order other) {
        return (int)id - other.id;
    }

    @Override
    public String toString() {
        return String.format("Order[id=%d book=%s operation=%s price=%.2f volume=%d]",
            id, book, operation, price, volume);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;

        Order otherOrder = (Order)other;
        return id == otherOrder.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}