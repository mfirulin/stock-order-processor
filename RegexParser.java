import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexParser {
    private static final String ADD_REGEX = 
        "\\s+<AddOrder.+\"(?<book>.+)\".+\"(?<operation>.+)\".+\"(?<price>.+)\".+\"(?<volume>.+)\".+\"(?<id>.+)\".+";
    private static final Pattern ADD_PATTERN = Pattern.compile(ADD_REGEX);
    private static final String DEL_REGEX = 
        "\\s+<DeleteOrder.+\"(?<id>.+)\".+";
    private static final Pattern DEL_PATTERN = Pattern.compile(DEL_REGEX);

    public static void parse(String line) {
        Matcher matcher = ADD_PATTERN.matcher(line);
        if (matcher.matches()) {
            int id = Integer.parseInt(matcher.group("id"));
            String book = matcher.group("book");
            Order.Operation operation = Order.Operation.valueOf(matcher.group("operation"));
            float price = Float.parseFloat(matcher.group("price"));
            int volume = Integer.parseInt(matcher.group("volume"));
            Storage.put(new Order(id, book, operation, price, volume));
        } else {
            matcher = DEL_PATTERN.matcher(line);
            if (matcher.matches()) {
                int id = Integer.parseInt(matcher.group("id"));
                Storage.delete(id);
            }
        }
    }
    
}
