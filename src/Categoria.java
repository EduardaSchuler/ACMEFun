public enum Categoria {
    ACAO("ACAO"),
    ESPORTE("ESPORTE"),
    ESTRATEGIA("ESTRATEGIA"),
    SIMULACAO("SIMULACAO"),
    RPG("RPG");

    private String nome;

    Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
