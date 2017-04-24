package quebracabeca;

public class Peca {

    private int valor;
    private String texto;
    private int posicao;

    public Peca(int valor) {
        // seta o valor da peca criada
        this.valor = valor;

        /*A impressão das peças são feitas com string 
        Seta os espaços antes e depois das string 
        para uma melhor aparencia visual  */
        if (this.valor == 16) {
            this.texto = "    ";
        } else if (this.valor > 9 && this.valor < 16) {
            this.texto = " " + Integer.toString(this.valor) + " ";
        } else {
            this.texto = "  " + Integer.toString(this.valor) + " ";
        }
    }

    public int getValor() {
        return valor;
    }

    public String getTexto() {
        return texto;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    @Override
    public String toString() {
        return "Valor:" + this.valor + "Texto: " + this.texto;
    }
}
