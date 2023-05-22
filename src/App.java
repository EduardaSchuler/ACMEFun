import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.Scanner;

public class App {
    private Scanner in;
    private Acervo acervo;
    private String linha;
    private ArrayList<String> file;

    public App() {
        String cabecalho;
        String titulo;
        double precoBase;
        Categoria categoria;
        int duracao;
        int tipo;
        in = new Scanner(System.in);
        acervo = new Acervo();
        try {
            BufferedReader streamEntrada  = new BufferedReader(new FileReader("src\\dados.csv"));
            cabecalho = streamEntrada.readLine();
            while((linha = streamEntrada.readLine()) != null){
                String[] valores = linha.split(";");
                titulo = valores[0];
                precoBase = Double.parseDouble(valores[1]);
                tipo = Integer.parseInt(valores[2]);
                if (tipo == 1){
                    duracao = Integer.parseInt(valores[3]);
                    BluRay bluRay = new BluRay(duracao, precoBase, titulo);
                    acervo.getListaBlueRays().add(bluRay);
                } else {
                    categoria = Categoria.valueOf(valores[3]);
                    Game game = new Game(titulo, precoBase, categoria);
                    acervo.getListaGames().add(game);
                }
            }
            PrintStream streamSaida = new PrintStream(new File("resultado.csv"), Charset.forName("UTF-8"));
            System.setOut(streamSaida);
        } catch (Exception e ){
            e.printStackTrace();
        }
    }

    public void executar(){
        lerAudioVisuais();
        escreveInfoItens();
        qtdGamesRPG();
    }

    public void lerAudioVisuais(){
        int calcula = acervo.getListaBlueRays().size() + acervo.getListaGames().size();
        System.out.println("1;" + calcula);
    }

    public void escreveInfoItens(){
        for (int i = 0; i < acervo.getListaGames().size(); i++) {
            Game game = acervo.getListaGames().get(i);
            System.out.println("2;" + game.getTitulo()+ ";" + game.calculaPrecoVenda() + ";" + game.calculaImposto());
        }
        for (int i = 0; i < acervo.getListaBlueRays().size(); i++) {
            BluRay bluRay = acervo.getListaBlueRays().get(i);
            System.out.println("2;" + bluRay.getTitulo()+ ";" + bluRay.calculaPrecoVenda() + ";" + bluRay.calculaImposto());
        }
    }

    public void qtdGamesRPG(){
        int count = 0;
        for (int i = 0; i < acervo.getListaGames().size(); i++) {
            Game game = acervo.getListaGames().get(i);
            if(game.getCategoria().equals(Categoria.RPG)){
                count++;
            }
        }
        System.out.println("3;"+count);
    }
    public void mediaImpostoBluRay(){
        double[] bluRays = new double[10];
        for (int i = 0; i < acervo.getListaBlueRays().size(); i++) {
            bluRays = new double[]{acervo.getListaBlueRays().get(i).calculaImposto()};
        }
        var media = Arrays.stream(bluRays).average();

    }
}
