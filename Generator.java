import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.Locale;

public class Generator {
    private static final int MAX_ORDERS = 2300000;
    private static final int MAX_STOCKS = 100;
    private static final int MAX_PRICE = 100;
    private static final int DEL_FACTOR = 17;
    private static final String PATH = "stock_orders.xml";
    
    private static final Random gen = new Random();

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(PATH), StandardCharsets.UTF_8)) {
            PrintWriter pw = new PrintWriter(bw);
            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            pw.println("<orders>");

            Set<Integer> deleteOrders = new HashSet<>();

            for (int id = 1; id <= MAX_ORDERS; id++) {
                pw.print('\t');
                pw.println(getAddOrder(id));
                if (getInt(0, DEL_FACTOR) == 0) {
                    int delId = getInt(1, id);
                    if (!deleteOrders.contains(delId)) {
                        deleteOrders.add(delId);
                        pw.print('\t');
                        pw.println(getDeleteOrder(delId));
                    }
                }
            }

            pw.println("</orders>");
        } 
    }

    // Generates whether ADD or DEL order
    private static String getOrder(int id) {
        return (gen.nextBoolean()) ? getAddOrder(id) : getDeleteOrder(id);
    }

    // Generates ADD Order:
    // <AddOrder book="stock-1" operation="SELL" price="100.50" volume="81" orderId="1" />
    private static String getAddOrder(int id) {
        final String template = 
            "<AddOrder book=\"stock-%d\" operation=\"%s\" price=\"%.2f\" volume=\"%d\" orderId=\"%d\" />";
        int stock = getInt(1, MAX_STOCKS);
        String operation = getOperation();
        float price = getPrice();
        int volume = getInt(10, 1000);
        return String.format(Locale.US, template, stock, operation, price, volume, id);
    }

    // Generates DEL Order:
    // <DeleteOrder orderId="1" />
    private static String getDeleteOrder(int id) {
        return String.format("<DeleteOrder orderId=\"%d\" />", id);
    }

    private static int getInt(int min, int max) {
        return gen.nextInt(max) + min;
    }
    private static String getOperation() {
        return (gen.nextBoolean()) ? "BUY" : "SELL";
    }

    private static float getPrice() {
        return gen.nextFloat() * MAX_PRICE;
    }
}