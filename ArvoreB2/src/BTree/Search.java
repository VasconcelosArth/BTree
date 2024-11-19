package BTree;

class Search {
    private NodeB endereco;
    private int posicao;

    Search(NodeB endereco, int posicao) {
        this.endereco = endereco;
        this.posicao = posicao;
    }

    public NodeB getEndereco() {
        return endereco;
    }
    public void setEndereco(NodeB endereco) {
        this.endereco = endereco;
    }
    public int getPosicao() {
        return posicao;
    }
    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
}
