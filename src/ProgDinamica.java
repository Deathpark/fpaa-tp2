import java.util.*;

public class ProgDinamica {
    static int INF = Integer.MAX_VALUE;

    public int progDinamica(int[] rotas, int quantCaminhoes) {
        long inicio = System.currentTimeMillis();
        int n = rotas.length;
        int[][] tabela = new int[n][quantCaminhoes];
        int[][] D = new int[n - 1][quantCaminhoes - 1];

        tabela[0][0] = rotas[0];

        for (int i = 1; i < n; i++) {
            tabela[i][0] = rotas[i] + tabela[i - 1][0];
        }

        for (int i = 1; i < quantCaminhoes; i++) {
            tabela[0][i] = rotas[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < quantCaminhoes; j++) {
                int max = -1;
                int minx = INF;
                for (int x = 0; x < i; x++) {
                    int s = Math.max(tabela[x][j - 1], tabela[i][0] - tabela[x][0]);
                    if (max < 0 || s < max) {
                        max = s;
                        minx = x;
                    }
                }
                tabela[i][j] = max;
                
                D[i - 1][j - 1] = minx;
            }
        }

        
        System.out.println("Tabela Prog Dinamica");
        for (int i = 0; i < tabela.length; i++) {
            for (int j = 0; j < tabela[0].length; j++) {
                System.out.print(tabela[i][j] + "\t");
            }
            System.out.println();
        }
        long diferenca = System.currentTimeMillis() - inicio;
        System.out.println("Tempo prog dinamica: " + diferenca);
        return tabela[tabela.length - 1][tabela[0].length - 1];
    }

    static List<List<Integer>> reconstructPartition(int[] values, int[][] D, int k) {
        List<List<Integer>> result = new ArrayList();
        int n = D.length;
        k = k - 2;
        while (k >= 0) {
            List<Integer> inner = new ArrayList();
            for (int i = D[n - 1][k] + 1; i < n + 1; i++) {
                inner.add(values[i]);
            }
            result.add(inner);
            n = D[n - 1][k];
            k--;
        }

        List<Integer> inner = new ArrayList();
        for (int i = 0; i < n + 1; i++) {
            inner.add(values[i]);
        }
        result.add(inner);
        Collections.reverse(result);
        return result;
    }
}