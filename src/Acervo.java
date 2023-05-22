import java.util.ArrayList;
import java.util.Arrays;

public class Acervo {
    private ArrayList<Game> listaGames;
    private ArrayList<BluRay> listaBlueRays;

    public Acervo() {
        listaGames = new ArrayList<>();
        listaBlueRays = new ArrayList<>();
    }

    public ArrayList<Game> getListaGames() {
        return listaGames;
    }

    public ArrayList<BluRay> getListaBlueRays() {
        return listaBlueRays;
    }

    public double[] listaValorImposto(){
        double[] bluRays = new double[10];
        for (int i = 0; i < getListaBlueRays().size(); i++) {
            bluRays = new double[]{ getListaBlueRays().get(i).calculaImposto()};
        }
        return bluRays;
    }
}
