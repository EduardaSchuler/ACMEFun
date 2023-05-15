public class Game extends AudioVisual{
    private Categoria categoria;

    public Game(String titulo, double precoBase, Categoria categoria) {
        super(titulo, precoBase);
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public double calculaPrecoVenda() {
        if(getCategoria().equals(Categoria.ACAO)){
            return getPrecoBase() * 0.2;
        } else if (getCategoria().equals(Categoria.ESPORTE)){
             return getPrecoBase() * 0.3;
        } else if (getCategoria().equals(Categoria.ESTRATEGIA)){
            return getPrecoBase() * 0.4;
        } else if (getCategoria().equals(Categoria.SIMULACAO)){
            return getPrecoBase() * 0.5;
        } else if(getCategoria().equals(Categoria.RPG)){
            return getPrecoBase() * 0.7;
        } else {
            return 0;
        }
    }

    @Override
    public double calculaImposto() {
        return calculaPrecoVenda() * 0.5;
    }
}
