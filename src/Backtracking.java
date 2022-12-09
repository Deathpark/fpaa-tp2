import java.util.LinkedList;
import java.util.List;

public class Backtracking {

    double res = Integer.MAX_VALUE;
    Caminhao maxCaminhao = new Caminhao();
    LinkedList<Caminhao> caminhoesResposta;

    public List<Caminhao> backtracking(List<Rota> rotas, int quantCaminhoes) {
        System.out.println("\nBACKTRACKING");
        long inicio = System.currentTimeMillis();
        LinkedList<Caminhao> caminhoes = Helper.novaListaCaminhoes(quantCaminhoes);
        caminhoesResposta = Helper.novaListaCaminhoes(quantCaminhoes);
        backtracking(rotas, 0, quantCaminhoes, caminhoes);
        long diferenca = System.currentTimeMillis() - inicio;
        System.out.println("Tempo backtracking: " + diferenca);
        System.out.println("Caminhão máximo:");
        System.out.println(maxCaminhao.toString());
        System.out.println("Resultado encontrado: " + maxCaminhao.getSoma());
        return caminhoesResposta;
    }

    private void backtracking(List<Rota> rotas, int quant, int totalCaminhoes, LinkedList<Caminhao> caminhoes) {
        if (quant == rotas.size()) {
            double max = 0;
            for (Caminhao caminhao : caminhoes) {
                max = Math.max(max, caminhao.getSoma());
                if (max >= res)
                    break;
            }
            if (max < res) {
                caminhoesResposta = Helper.novaListaCaminhoes(totalCaminhoes);
                for (int i = 0; i < totalCaminhoes; i++) {
                    for (Rota rota : caminhoes.get(i).rotas) {
                        caminhoesResposta.get(i).rotas.add(rota);
                    }
                }
                maxCaminhao = caminhoesResposta.stream().max((c1, c2) -> c1.compareTo(c2)).get();
                res = max;
            }
            return;
        }
        for (int i = 0; i < totalCaminhoes; i++) {
            caminhoes.get(i).rotas.add(rotas.get(quant));
            backtracking(rotas, quant + 1, totalCaminhoes, caminhoes);
            caminhoes.get(i).rotas.remove(rotas.get(quant));
        }
    }

}
