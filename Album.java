import java.io.*; //Utilização das classes de leitura de arquivos
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Album {

    Scanner sc = new Scanner(System.in);

    static int linhas; //declarando variáveis globais
    static int colunas;
    static String[] selecoes;
    static int[][] matriz;
    static int[][] matriz2;

    //==============================
    // Método de leitura do arquivo
    //==============================
    static void carregarAlbum(String caminhoArquivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {

            // 1ª linha: dimensões
            //Lê uma linha do arquivo com o método da classe bufferedReader br e divide strings diferentes utilizando o split
            String[] dimensoes = br.readLine().trim().split("\\s+");
            linhas  = Integer.parseInt(dimensoes[0]); //converte a string para inteiro
            colunas = Integer.parseInt(dimensoes[1]); //converte a string para inteiro

            selecoes = new String[linhas];
            matriz   = new int[linhas][colunas];

            // próximas M linhas: nomes das seleções
            for (int i = 0; i < linhas; i++) {
                selecoes[i] = br.readLine().trim(); //Lê os nomes de cada seleção.
                //o método trim serve para remover espaços em branco antes e depois da string lida
            }

            // próximas M linhas: valores da matriz que representam as figrinhas
            for (int i = 0; i < linhas; i++) {
                String[] valores = br.readLine().trim().split("\\s+");
                //Leitura similar à linha 14
                for (int j = 0; j < colunas; j++) {
                    matriz[i][j] = Integer.parseInt(valores[j]);//Converte valores para inteiro
                }
            }

            System.out.println("Álbum carregado com sucesso!");
            System.out.printf("Seleções: %d | Jogadores por seleção: %d%n", linhas, colunas);
        }
    }

    static void carregarAlbum2(String album2) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(album2))) {

            // 1ª linha: dimensões
            //Lê uma linha do arquivo com o método da classe bufferedReader br e divide strings diferentes utilizando o split
            String[] dimensoes = br.readLine().trim().split("\\s+");
            linhas  = Integer.parseInt(dimensoes[0]); //converte a string para inteiro
            colunas = Integer.parseInt(dimensoes[1]); //converte a string para inteiro

            selecoes = new String[linhas];
            matriz2   = new int[linhas][colunas];

            // próximas M linhas: nomes das seleções
            for (int i = 0; i < linhas; i++) {
                selecoes[i] = br.readLine().trim(); //Lê os nomes de cada seleção.
                //o método trim serve para remover espaços em branco antes e depois da string lida
            }

            // próximas M linhas: valores da matriz que representam as figrinhas
            for (int i = 0; i < linhas; i++) {
                String[] valores = br.readLine().trim().split("\\s+");
                //Leitura similar à linha 14
                for (int j = 0; j < colunas; j++) {
                    matriz2[i][j] = Integer.parseInt(valores[j]);//Converte valores para inteiro
                }
            }

            System.out.println("Segundo album carregado com sucesso!");
            System.out.printf("Seleções: %d | Jogadores por seleção: %d%n", linhas, colunas);
        }
    }

    //====================================
    // Método para imprimir o álbum atual
    //====================================
    static void exibirMatriz() {
        if (matriz == null) {
            System.out.println("Nenhum álbum carregado.");
            return;
        }
        // cabeçalho com números dos jogadores
        System.out.println("ALBUM ATUAL");
        System.out.printf("%-15s", "Seleção");
        //Imprime a palavra e reserva 15 espaços para impressão, alinhando à esquerda
        for (int j = 1; j <= colunas; j++) {
            System.out.printf(" J%-3d", j);
        }
        System.out.println();

        // linha separadora
        //Imprime 15 hifens e depois mais 5 hifens por coluna da matriz
        System.out.println("-".repeat(15 + colunas * 5));

        // linhas da matriz
        for (int i = 0; i < linhas; i++) {
            System.out.printf("%-15s", selecoes[i]);
            for (int j = 0; j < colunas; j++) {
                System.out.printf(" %-4d", matriz[i][j]);
            }
            System.out.println();
        }
    }

    static void registraFigurinha(Scanner sc) {
        String nomeSelecao;
        boolean achouSelecao = false;
        int posSelecao = 0, posJogador;
        // COLOCAR UMA PERGUNTA DE QUANTAS FIGU VAI REGISTRAR?
        if (selecoes == null) {
            System.out.println("Carregue o album primeiro!");
            return;
        }
        else {
            do {
                sc.nextLine();
                System.out.println("Informe o nome da seleção do jogador: ");
                nomeSelecao = sc.nextLine();
                nomeSelecao = nomeSelecao.trim();
                for (int i = 0; i < selecoes.length; i++) {
                    if (selecoes[i].equalsIgnoreCase(nomeSelecao)) {
                        achouSelecao = true;
                        break;
                    }
                }
                if (!achouSelecao) System.out.println("Nome da seleção inválida. ");
            }
            while (!achouSelecao);
            do {
                System.out.println("Informe o número do jogador: (1 à " + (colunas) + "): ");
                posJogador = sc.nextInt();
                if (posJogador < 1 || posJogador > colunas) System.out.println("Número inválido. Tente novamente");
            } while (posJogador < 1 || posJogador > colunas);
            matriz[posSelecao][posJogador - 1]++;
            System.out.println("Figurinha carregada com sucesso. \nVoltando ao menu inicial. \n\n");
        }
    }

    static void figurinhaFaltantes(){
        if (matriz != null) {
            int figurinhasFaltam = 0;
            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    if (matriz[i][j] == 0) {
                        System.out.println(selecoes[i] + " - Jogador " + (j + 1));
                        figurinhasFaltam++;
                    }
                }
            }
            System.out.println("------------------");
            System.out.printf("Total de %d figurinhas faltantes\n", figurinhasFaltam);
        }
        else {
            System.out.println("Carregue o album primeiro!");
            return;
        }
    }
    static void figurinhasRepetidas(){
        if (matriz != null) {
            int figurinhasRepetidas = 0;
            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    if (matriz[i][j] > 1) {
                        System.out.printf("%s - %d repetições do Jogador %d\n", selecoes[i], (matriz[i][j] - 1), (j + 1));
                        figurinhasRepetidas += (matriz[i][j] - 1);
                    }
                }
            }
            System.out.println("------------------");
            System.out.printf("Total de %d figurinhas repetidas\n", figurinhasRepetidas);
        }
        else {
            System.out.println("Carregue o album primeiro!");
            return;
        }
    }

    //=============================
    // Método para comparar albuns
    //=============================

    static void compararAlbum(Scanner sc){
        if (matriz != null) {
            String album2;
            boolean possivelTrocar = true;

            sc.nextLine();
            System.out.println("Digite o nome do arquivo de segundo album: ");
            album2 = sc.nextLine();

            try {
                carregarAlbum2(album2);
                //exibirMatriz();
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido no arquivo: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Erro de leitura: " + e.getMessage());
            }

            System.out.print("\nAlbum 1 para Album 2: ");
            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    if (matriz[i][j] > 1 && matriz2[i][j] == 0) {
                        System.out.print(selecoes[i] + " jogador " + (j + 1) + "; ");
                    }
                    if (i == linhas - 1 && j == colunas - 1 && (matriz[i][j] == 0 || matriz2[i][j] > 0)) {
                        System.out.print("\nNão há mais figurinhas para serem trocadas  ");
                        possivelTrocar = false;
                    }
                }
            }
            System.out.println("\b\b.");

            System.out.print("\nAlbum 2 para Album 1: ");
            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    if (matriz2[i][j] > 1 && matriz[i][j] == 0) {
                        System.out.print(selecoes[i] + " jogador " + (j + 1) + "; ");
                    }
                    if (i == linhas - 1 && j == colunas - 1 && (matriz2[i][j] == 0 || matriz[i][j] > 0)) {
                        System.out.print("\n Não há mais figurinhas para serem trocadas  ");
                        possivelTrocar = false;
                    }
                }
            }
            System.out.println("\b\b.\n");

            if (possivelTrocar) {
                System.out.println("Deseja efetuar as trocas?");
                if (sc.nextLine().equalsIgnoreCase("sim")) {
                    trocaFigurinhas();
                }
            }
        }
        else {
            System.out.println("Carregue o album primeiro!");
        }
    }

    static void trocaFigurinhas() {

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (matriz[i][j] > 1 && matriz2[i][j] == 0) {
                    matriz[i][j]--;
                    matriz2[i][j]++;
                }
                if (matriz2[i][j] > 1 && matriz[i][j] == 0) {
                    matriz[i][j]++;
                    matriz2[i][j]--;
                }
            }
        }
    }

    //=======================================
    // Método para ver estatisticas do album
    //=======================================

    static void mostrarEstatisticas(){
        if (matriz == null){
            System.out.println("Carregue o Album primeiro!");
            return;
        }
        else {
            String maisFaltam = "";
            int jogadorMaisRepetido = 0;
            int[] faltamPorSelecao = new int[linhas];
            double percentual = 100;

            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    if (matriz[i][j] == 0) {
                        percentual -= (double) (100) / (linhas * colunas);
                        faltamPorSelecao[i]++;
                    }
                    if (matriz[i][j] > jogadorMaisRepetido){
                        jogadorMaisRepetido = matriz[i][j];
                    }
                }
            }

            System.out.printf("O album está %.2f%% completo." +
                    "\n Faltam por selecao: ", percentual);

            for (int i = 0; i < linhas; i++) {
                System.out.println(selecoes[i]+": "+faltamPorSelecao[i]);
                if (i > 0 && faltamPorSelecao[i] > faltamPorSelecao[i-1]){
                    maisFaltam = selecoes[i];
                }
            }
            System.out.println("A seleção que mais faltam figurinhas é o(a) "+maisFaltam);

            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    if (matriz[i][j] == jogadorMaisRepetido){
                        System.out.println("O jogador mais repetido é o jogador "+(j+1)+" da seleção da(o) "+selecoes[i]);
                    }
                }
            }
        }
    }

    //=======================
    // Método salvar
    //=======================
    static void salvarAlbum(String enderecoAlbum, Scanner sc){
        if (matriz != null) {
            String album2;
            sc.nextLine();

            Path caminho = Path.of(enderecoAlbum);

            try {
                List<String> linhasAntigas = Files.readAllLines(caminho);

                List<String> linhasNovas = new ArrayList<>();

                for (int i = 0; i <= selecoes.length; i++) {
                    if (i < linhasAntigas.size()) {
                        linhasNovas.add(linhasAntigas.get(i));
                    }
                }

                for (int[] linha : matriz) {
                    StringBuilder linhaTexto = new StringBuilder();
                    for (int i = 0; i < linha.length; i++) {
                        linhaTexto.append(linha[i]);
                        if (i < linha.length - 1) {
                            linhaTexto.append(" ");
                        }
                    }
                    linhasNovas.add(linhaTexto.toString());
                }

                Files.write(caminho, linhasNovas);
                System.out.println("Album alterado com sucesso!");
            } catch (IOException e) {
                System.err.println("Erro ao processar o arquivo: " + e.getMessage());
            }
        }
        else {
            System.out.println("Carregue o album primeiro!");
            return;
        }
    }

    //=======================
    // Método main
    //=======================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String enderecoAlbum = " ";


        int opcao = 0;

        do {
            System.out.println("==========================================");
            System.out.println("        MENU PRINCIPAL");
            System.out.println("==========================================");
            System.out.println("1. CARREGAR ALBÚM");
            System.out.println("2. REGISTRAR NOVA FIGURINHA");
            System.out.println("3. LISTAR FIGURINHAS FALTANTES");
            System.out.println("4. LISTAR FIGURINHAS REPETIDAS");
            System.out.println("5. COMPARAR DOIS ÁLBUNS");
            System.out.println("6. VISUALIZAR ESTATÍSTICA DO ÁLBUM");
            System.out.println("7. SALVAR ALTERAÇÕES");
            System.out.println("8. SAIR");
            System.out.println("=========================================");
            System.out.print("Escolha uma opção (número): ");
            opcao = sc.nextInt();
            switch (opcao) {
                case 1: // carregar albúm
                    sc.nextLine();
                    System.out.println("Informe o endereço do arquivo do albúm. ");
                    System.out.println("Módelo de endereço: C:Users/name/Downloads/Álbum.txt");
                    System.out.print("Endereço: ");
                    enderecoAlbum = sc.nextLine();
                    enderecoAlbum=enderecoAlbum.trim();
                    System.out.println();
                    try {
                        carregarAlbum(enderecoAlbum);
                        //exibirMatriz();
                    } catch (FileNotFoundException e) {
                        System.out.println("Arquivo não encontrado: " + e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("Formato inválido no arquivo: " + e.getMessage());
                    } catch (IOException e) {
                        System.out.println("Erro de leitura: " + e.getMessage());
                    }
                    break;
                case 2: // registrar figu
                    registraFigurinha(sc);
                    break;
                case 3: // figu faltando
                    figurinhaFaltantes();
                    break;
                case 4: // figu a mais
                    figurinhasRepetidas();
                    break;
                case 5: // comparação
                    compararAlbum(sc);
                    break;
                case 6: // visualizar estatística
                    mostrarEstatisticas();
                    break;
                case 7: // salvar álbum
                    salvarAlbum(enderecoAlbum,sc);
                    break;
                case 8: //sair
                    System.out.println("ENCERRANDO SISTEMA...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 8 );

    }
}