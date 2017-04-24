package quebracabeca;

//Importes
import java.util.Random;
import java.util.Scanner;

public class Tabuleiro {

    private Peca[][] tabuleiro = new Peca[4][4];
    private boolean fim = false;

    Scanner scan = new Scanner(System.in);

    //Metodo Construtor
    public Tabuleiro() {
        this.novoJogo();
        //this.testeFim();
        this.jogar();
    }

    //Cria um novo jogo embaralhado
    public void novoJogo() {
        Random random = new Random();
//cria a matriz
        int count = 1;
        for (int linha = 0; linha < 4; linha++) {
            for (int coluna = 0; coluna < 4; coluna++) {
                this.tabuleiro[linha][coluna] = new Peca(count);
                count++;
            }
        }//fim cria matriz

        //embaralha a matriz 
        for (int l = 0; l < 4; l++) {
            for (int c = 0; c < 4; c++) {
                //sorteia um índice
                int numRandom = random.nextInt(4);
                //troca o conteúdo dos índices c e numRandom do vetor
                Peca aux = this.tabuleiro[l][c];
                this.tabuleiro[l][c] = this.tabuleiro[l][numRandom];
                this.tabuleiro[l][numRandom] = aux;
            }
        }//fim embaralha...

        // Coloca o 16 que será vazio sempre na ultima posição  
        for (int linha = 0; linha < 4; linha++) {
            for (int coluna = 0; coluna < 4; coluna++) {
                if (this.tabuleiro[linha][coluna].getValor() == 16) {
                    Peca aux = this.tabuleiro[linha][coluna];
                    this.tabuleiro[linha][coluna] = this.tabuleiro[3][3];
                    this.tabuleiro[3][3] = aux;
                }
            }
        } //Fim coloca o zero... 
    }

//Verifica a posição de cada peça dentro do tabuleiro
    public int posicaoPeca(int peca) {
        int posicao = 0;
        // Procura a posição da peça passada
        int[] array = new int[2];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.tabuleiro[i][j].getValor() == peca) {
                    array[0] = i;
                    array[1] = j;
                }
            }
        }

        //define a posiçao
        //de 1-4
        if (array[0] == 0 && array[1] == 0) {
            posicao = 1;
        } else if (array[0] == 0 && array[1] == 1) {
            posicao = 2;
        } else if (array[0] == 0 && array[1] == 2) {
            posicao = 3;

        } else if (array[0] == 0 && array[1] == 3) {
            posicao = 4;

        } // de 5 - 8   
        else if (array[0] == 1 && array[1] == 0) {
            posicao = 5;
        } else if (array[0] == 1 && array[1] == 1) {
            posicao = 6;
        } else if (array[0] == 1 && array[1] == 2) {
            posicao = 7;
        } else if (array[0] == 1 && array[1] == 3) {
            posicao = 8;

        } // de 9 - 12
        else if (array[0] == 2 && array[1] == 0) {
            posicao = 9;
        } else if (array[0] == 2 && array[1] == 1) {
            posicao = 10;
        } else if (array[0] == 2 && array[1] == 2) {
            posicao = 11;
        } else if (array[0] == 2 && array[1] == 3) {
            posicao = 12;

        } // de 13 - 16
        else if (array[0] == 3 && array[1] == 0) {
            posicao = 13;
        } else if (array[0] == 3 && array[1] == 1) {
            posicao = 14;
        } else if (array[0] == 3 && array[1] == 2) {
            posicao = 15;
        } else if (array[0] == 3 && array[1] == 3) {
            posicao = 16;
        }
        return posicao;
    }

//Realiza a jogada
    public void jogar() {
        boolean jogando = true;
        int peca = 0;
        while (this.fim == false) {
            this.imprimeJogo();
            System.out.println("Qual peça deseja mover");
            peca = this.scan.nextInt();

            //Repete até que se jogue um peça valida
            if (this.isJogada(peca) == false) {
                System.out.println("O movimento da peça " + peca + " não é válido.");
                System.out.println("Escolha outra ! ");

                //Se a jogada for  válida, realiza a jogada
            } else if (this.isJogada(peca) == true) {

                //Encontra onde está a posição vazia
                int[] pos = new int[2];
                for (int f = 0; f < 4; f++) {
                    for (int i = 0; i < 4; i++) {
                        if (this.tabuleiro[f][i].getValor() == 16) {
                            pos[0] = f;
                            pos[1] = i;
                        }
                    }
                }
                //faz  a troca com a posição vazia
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (this.tabuleiro[i][j].getValor() == peca) {
                            Peca aux = this.tabuleiro[i][j];
                            this.tabuleiro[i][j] = this.tabuleiro[pos[0]][pos[1]];
                            this.tabuleiro[pos[0]][pos[1]] = aux;
                        }
                    }
                }// fim faz a troca..
                //verifica se o jogo chegou ao fim
                this.setFim();
            }// fim realiza a jogada valida
        }//fim while

    }

// Verifica se a jogada é válida
    public boolean isJogada(int peca) {
        boolean valida = false;
        int vazia = this.posicaoPeca(16);
        int posicao = this.posicaoPeca(peca);

        //Verifica todas as posibilidades de movimento  
        if ((vazia == 1 && posicao == 2) || (vazia == 1 && posicao == 5)) {
            valida = true;
        } else if ((vazia == 2 && posicao == 1) || (vazia == 2 && posicao == 3) || (vazia == 2 && posicao == 6)) {
            valida = true;
        } else if ((vazia == 3 && posicao == 2) || (vazia == 3 && posicao == 4) || (vazia == 3 && posicao == 7)) {
            valida = true;
        } else if ((vazia == 4 && posicao == 3) || (vazia == 4 && posicao == 8)) {
            valida = true;
        } else if ((vazia == 5 && posicao == 1) || (vazia == 5 && posicao == 6) || (vazia == 5 && posicao == 9)) {
            valida = true;
        } else if ((vazia == 6 && posicao == 2) || (vazia == 6 && posicao == 5) || (vazia == 6 && posicao == 7) || (vazia == 6 && posicao == 10)) {
            valida = true;
        } else if ((vazia == 7 && posicao == 3) || (vazia == 7 && posicao == 6) || (vazia == 7 && posicao == 8) || (vazia == 7 && posicao == 11)) {
            valida = true;
        } else if ((vazia == 8 && posicao == 4) || (vazia == 8 && posicao == 7) || (vazia == 8 && posicao == 12)) {
            valida = true;
        } else if ((vazia == 9 && posicao == 5) || (vazia == 9 && posicao == 10) || (vazia == 9 && posicao == 13)) {
            valida = true;
        } else if ((vazia == 10 && posicao == 6) || (vazia == 10 && posicao == 9) || (vazia == 10 && posicao == 11) || (vazia == 10 && posicao == 14)) {
            valida = true;
        } else if ((vazia == 11 && posicao == 7) || (vazia == 11 && posicao == 10) || (vazia == 11 && posicao == 12) || (vazia == 11 && posicao == 15)) {
            valida = true;
        } else if ((vazia == 12 && posicao == 8) || (vazia == 12 && posicao == 11) || (vazia == 12 && posicao == 16)) {
            valida = true;
        } else if ((vazia == 13 && posicao == 9) || (vazia == 13 && posicao == 14)) {
            valida = true;
        } else if ((vazia == 14 && posicao == 10) || (vazia == 14 && posicao == 13) || (vazia == 14 && posicao == 15)) {
            valida = true;
        } else if ((vazia == 15 && posicao == 11) || (vazia == 15 && posicao == 14) || (vazia == 15 && posicao == 16)) {
            valida = true;
        } else if ((vazia == 16 && posicao == 12) || (vazia == 16 && posicao == 15)) {
            valida = true;
        }
// Retorna se a jogada requisitada é válidada ou não
        return valida;
    }

//(não está pronta )Verifica se o jogo chegou ao fim 
    public void setFim() {
        int valor = 1, count = 0;

        for (int f = 0; f < 4; f++) {
            for (int i = 0; i < 4; i++) {
                valor++;
                if (this.tabuleiro[f][i].getValor() > valor) {
                    count++;
                }
            }
        }
        if (count == 0) {
            this.fim = true;
            this.zeramento();
        }
    }

//Imprime o Tabuleiro de jogo
    public void imprimeJogo() {
        System.out.println("------------------");   
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                System.out.print(this.tabuleiro[i][j].getTexto());
            }
            System.out.println();//Quebra linha
        }
        System.out.println("------------------");
    }

// Cria uma matriz faltando apenas um movimento para finalizer
    //serve para testar o fim do jogo
    public void testeFim() {
        int count = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.tabuleiro[i][j] = new Peca(count);
                count++;
            }
        }
        this.tabuleiro[3][2] = new Peca(16);
        this.tabuleiro[3][3] = new Peca(15);
    }

    public void zeramento() {

        System.out.println("           ╰╮╰ ╰ ╰╮");
        System.out.println("...............╭╯╭╯╰╮╰╮");
        System.out.println("................╰╮╰╮ ╭ .╭ ╯╭╯╭╯");
        System.out.println(". ·. ·. ·. ▓██████████████▓");
        System.out.println(". ·. ·. ·. █★.   Parabéns!    .★ ▓█ ");
        System.out.println(". ·. ·. ·. █▓▓▓▓▓▓▓▓▓▓▓▓▓▓████ ");
        System.out.println(". ·. ·. ·. █▓▓▓▓▓▓▓▓▓▓▓▓▓▓█. ·. ██");
        System.out.println(". ·. ·. ·. █▓▓▓▓▓▓▓▓▓▓▓▓▓▓████");
        System.out.println(". ·. ·. ·. ·█▓▓▓▓▓▓▓▓▓▓▓▓▓█");
        System.out.println(". ·. ·. ·. ·. █▓▓▓▓▓▓▓▓▓▓▓█");
        System.out.println(". ·. ·. ·.. ·. ·█▓▓▓▓▓▓▓▓▓█");
        System.out.println(". ·. · ▓███████████████████▓");
        System.out.println(". ·. ·. ·. ·▓██████████████▓");
        System.out.println("Faculdade Pitágoras ");
        System.out.println("Ciência da Computação ");
        System.out.println("Alunos: J. Claudio & Rodrigo Nunes");
    }
}
