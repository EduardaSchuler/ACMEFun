import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {
    private Scanner in;
    private Acervo acervo = new Acervo();

    public App() {
    in = new Scanner(System.in);
    try {
        Reader reader = Files.newBufferedReader(Paths.get("dados.csv"));
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
    } catch (Exception e ){

    }
    }
}
