import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImportCharacter {

    public static Scanner scanner;
    private static List<String> lines = new ArrayList<>();


    public static List<String> getCharacterDataFromCSV() throws FileNotFoundException {
         scanner = new Scanner(new File("characters.csv"));
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }

    public static void clean() {
        lines = new ArrayList<>();
    }
}
