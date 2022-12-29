public class SubstrParser {
    
    public static void parse(String line) {
        if (line.startsWith("\t<A")) {
            handleAddOrder(line);
        } else if (line.startsWith("\t<D")) {
            handleDeleteOrder(line);
        }
    }

    // <AddOrder book="stock-32" operation="SELL" price="76.53" volume="207" orderId="29" />
    private static void handleAddOrder(String line) {
        String[] values = new String[5];

        for (int i = 0, start = 0; i < values.length; i++) {
            start = line.indexOf('\"', start);
            start++;
            int end = line.indexOf('\"', start);
            values[i] = line.substring(start, end);
            start = end + 1;
        }

        String book = values[0];
        Order.Operation operation = Order.Operation.valueOf(values[1]);
        float price = Float.parseFloat(values[2]);
        int volume = Integer.parseInt(values[3]);
        int id = Integer.parseInt(values[4]);

        Storage.put(new Order(id, book, operation, price, volume));
    }

    // <DeleteOrder orderId="16" />
    private static void handleDeleteOrder(String line) {
        int start = line.indexOf('\"');
        int end = line.indexOf('\"', start + 1);
        Storage.delete(Integer.parseInt(line.substring(start + 1, end)));
    }
}
