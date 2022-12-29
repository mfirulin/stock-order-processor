import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Reader {
    private static final String PATH = "stock_orders.xml";
    
    public static void main(String[] args) throws IOException {
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get(PATH), StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                // RegexParser.parse(line);
                // PlainPairParser.parse(line);
                SubstrParser.parse(line);
            }
            System.out.println(Storage.size());
       }
    }
}