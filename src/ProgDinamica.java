import java.util.*;

public class ProgDinamica {
    static int INF = Integer.MAX_VALUE;

    int progDinamica(int[] rotas, int quantCaminhoes) {
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

        System.out.println("INITIAL");
        for (int i = 0; i < tabela.length; i++) {
            for (int j = 0; j < tabela[0].length; j++) {
                System.out.print(tabela[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\n\n");

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
                System.out.println("\n\nM");
                for (int m = 0; m < tabela[0].length; m++) {
                    for (int l = 0; l < tabela.length; l++) {
                        System.out.print(tabela[l][m] + "\t");
                    }
                    System.out.println();
                }
                D[i - 1][j - 1] = minx;
            }
        }

        System.out.println("D");
        for (int i = 0; i < D.length; i++) {
            for (int j = 0; j < D[0].length; j++) {
                System.out.print(D[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("M");
        for (int i = 0; i < tabela.length; i++) {
            for (int j = 0; j < tabela[0].length; j++) {
                System.out.print(tabela[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println(tabela[tabela.length - 1][tabela[0].length - 1]);
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