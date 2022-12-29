public class SplitParser {

    public static void parse(String line) {
        if (line.startsWith("\t<A")) {
            handleAddOrder(line);
        } else if (line.startsWith("\t<D")) {
            handleDeleteOrder(line);
        }
    }

    // <AddOrder book="stock-32" operation="SELL" price="76.53" volume="207" orderId="29" />
    private static void handleAddOrder(String line) {
        String[] substr = getSubstrings(line); // Get 7 strings
        String book = getValueStringFromPair(substr[1]);
        Order.Operation operation = Order.Operation.valueOf(getValueStringFromPair(substr[2]));
        float price = Float.parseFloat(getValueStringFromPair(substr[3]));
        int volume = Integer.parseInt(getValueStringFromPair(substr[4]));
        int id = getId(substr[5]);
        Sorter.put(new Order(id, book, operation, price, volume));
    }

    // <DeleteOrder orderId="16" />
    private static void handleDeleteOrder(String line) {
        String[] substr = getSubstrings(line); // Get 3 strings
        int id = getId(substr[1]);
        Sorter.delete(id);
    }

    private static int getId(String id) {
        return Integer.parseInt(getValueStringFromPair(id));
    }

    private static String[] getSubstrings(String line) {
        return line.split(" ");
    }

    private static String getValueStringFromPair(String str) {
        String[] pair = str.split("=");
        return pair[1].substring(1, pair[1].length() - 1);
    }
}
