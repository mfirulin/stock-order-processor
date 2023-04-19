public class Main {
    public static void main(String[] args) {
        final String PATH = "stock_orders.xml";
        
        try {
            Reader.read(PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}