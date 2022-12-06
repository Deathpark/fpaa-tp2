public class Backtracking {

    int res = Integer.MAX_VALUE;

    public int distribuirRotas(int[] rotas, int quantCaminhoes) {
        long inicio = System.currentTimeMillis();
        backtracking(rotas, 0, quantCaminhoes, new int[quantCaminhoes]);
        long diferenca = System.currentTimeMillis() - inicio;
        System.out.println(diferenca);
        return res;
    }

    private void backtracking(int[] rotas, int quant, int quantCaminhoes, int[] caminhoes) {
        if (quant == rotas.length) {
            int max = 0;
            for (int caminhao : caminhoes)
                max = Math.max(max, caminhao);
            res = Math.min(res, max);
            return;
        }
        for (int i = 0; i < quantCaminhoes; i++) {
            caminhoes[i] += rotas[quant];
            backtracking(rotas, quant + 1, quantCaminhoes, caminhoes);
            caminhoes[i] -= rotas[quant];
        }
    }

}
