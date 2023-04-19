import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Reader {
    public static void read(String path) throws IOException {
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                // RegexParser.parse(line);
                // SplitParser.parse(line);
                SubstrParser.parse(line);
            }
            System.out.println("Orders:" + Sorter.size());
            Sorter.sort();
            System.out.println("Books: " + Storage.size());
            Storage.info();
        }
    }
}
