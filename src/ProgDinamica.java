import java.util.*;

public class ProgDinamica {
    public void progDinamica(List<Rota> rotas, int quantCaminhoes) {
        System.out.println("PROG DINAMICA");
        long inicio = System.currentTimeMillis();
        int n = rotas.size();
        double[][] tabela = new double[n][quantCaminhoes];
        Caminhao[][] tabelaCaminhao = new Caminhao[n][quantCaminhoes];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < quantCaminhoes; j++) {
                tabelaCaminhao[i][j] = new Caminhao();
            }
        }

        // Inicializando máximo para todas as rotas com apenas um caminhão
        tabela[0][0] = rotas.get(0).getComprimento();
        tabelaCaminhao[0][0].rotas.add(rotas.get(0));
        for (int i = 1; i < n; i++) {
            tabela[i][0] = rotas.get(i).getComprimento() + tabela[i - 1][0];
            tabelaCaminhao[i][0].rotas.add(rotas.get(i));
        }
        // Inicializando máximo para uma rota em qualquer quantidade de caminhoes
        for (int i = 1; i < quantCaminhoes; i++) {
            tabela[0][i] = rotas.get(0).getComprimento();
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < quantCaminhoes; j++) {
                double max = Double.MAX_VALUE;
                Caminhao maxCaminhao = new Caminhao();
                for (int k = 0; k < i; k++) {
                    Caminhao maxAtual;
                    double atual;
                    if (tabela[k][j - 1] >= (tabela[i][0] - tabela[k][0])) {
                        atual = tabela[k][j - 1];
                        maxAtual = tabelaCaminhao[k][j - 1];
                    } else {
                        atual = tabela[i][0] - tabela[k][0];
                        maxAtual = new Caminhao();
                        for (int l = k + 1; l <= i; l++) {
                            maxAtual.rotas.add(rotas.get(l));
                        }
                    }
                    if (atual < max) {
                        max = atual;
                        maxCaminhao = maxAtual;
                    }
                }
                tabela[i][j] = max;
                tabelaCaminhao[i][j] = maxCaminhao;
            }
        }

        System.out.println("Tabela Prog Dinamica");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < quantCaminhoes; j++) {
                System.out.print(tabela[i][j] + "\t");
            }
            System.out.println();
        }
        long diferenca = System.currentTimeMillis() - inicio;
        System.out.println("Tempo prog dinamica: " + diferenca);
        System.out.println("Caminhão máximo:");
        System.out.println(tabelaCaminhao[n - 1][quantCaminhoes - 1].toString());
        System.out.println("Resultado encontrado: " + tabela[n - 1][quantCaminhoes - 1]);
    }
}