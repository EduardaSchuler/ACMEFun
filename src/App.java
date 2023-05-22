import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private Scanner in;
    private Acervo acervo;
    private String linha;

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
                try {
                    String[] valores = linha.split(";");
                    titulo = valores[0];
                    precoBase = Double.parseDouble(valores[1]);
                    tipo = Integer.parseInt(valores[2]);
                    if (tipo == 1) {
                        duracao = Integer.parseInt(valores[3]);
                        BluRay bluRay = new BluRay(duracao, precoBase, titulo);
                        acervo.getListaBlueRays().add(bluRay);
                    } else if (tipo == 2) {
                        try {
                            categoria = Categoria.valueOf(valores[3]);
                            Game game = new Game(titulo, precoBase, categoria);
                            acervo.getListaGames().add(game);
                        } catch (IllegalArgumentException e) {
                            System.out.println("categoria inválida!");
                        }
                    } else {
                        System.out.println("Campo 'tipo' invalido!");
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Uma das linhas é inválida!");
                }
            }
            PrintStream streamSaida = new PrintStream(new File("src\\resultado.csv"), Charset.forName("UTF-8"));
            System.setOut(streamSaida);
        } catch (Exception e ){
            e.printStackTrace();
        }
    }

    public void executar(){
        lerAudioVisuais();
        escreveInfoItens();
        qtdGamesRPG();
        mediaBluRayImposto();
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

    public void mediaBluRayImposto(){
        ArrayList<BluRay> bluRays = acervo.getListaBlueRays();
        double somaImpostos = bluRays.stream().mapToDouble(BluRay::calculaImposto).sum();
        double mediaImpostos = somaImpostos / bluRays.size();

        BluRay bluRayMaisProximo = bluRays.stream()
                .min((a, b) -> Double.compare(Math.abs(a.calculaImposto() - mediaImpostos),
                        Math.abs(b.calculaImposto() - mediaImpostos)))
                .orElse(null);

        if (bluRayMaisProximo != null) {
            System.out.println("4;" + mediaImpostos + ";" + bluRayMaisProximo.getTitulo());
        } else {
            System.out.println("4:Nenhum BluRay");
        }
    }
}
