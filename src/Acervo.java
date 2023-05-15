import java.util.ArrayList;

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
}
