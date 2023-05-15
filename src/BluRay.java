public class BluRay extends AudioVisual {
    private int duracao;

    public BluRay(int duracao, double precoBase, String titulo) {
        super(titulo, precoBase);
        this.duracao = duracao;
    }

    public int getDuracao() {
        return duracao;
    }

    @Override
    public double calculaPrecoVenda() {
        return (getPrecoBase() * getDuracao())/100;
    }

    @Override
    public double calculaImposto() {
        return calculaPrecoVenda() * 0.4;
    }
}
