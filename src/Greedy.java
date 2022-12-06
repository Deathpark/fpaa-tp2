import java.util.LinkedList;
import java.util.List;

public class Greedy {
    private static LinkedList<Caminhao> novaListaCaminhoes(int quant) {
        LinkedList<Caminhao> caminhoes = new LinkedList<>();
        for (int i = 0; i < quant; i++) {
            caminhoes.add(new Caminhao());
        }
        return caminhoes;
    }

    public double algGuloso(List<Rota> rotas, int quant) {
        LinkedList<Caminhao> caminhoes = novaListaCaminhoes(quant);
        long inicio = System.currentTimeMillis();
        for (Rota rota : rotas) {
            Caminhao min = caminhoes.stream().min((c1, c2) -> c1.compareTo(c2)).get();
            min.rotas.add(rota);
        }
        Caminhao max = caminhoes.stream().max((c1, c2) -> c1.compareTo(c2)).get();
        long diferenca = System.currentTimeMillis() - inicio;
        System.out.println("Tempo guloso: " + diferenca);
        return max.getSoma();
    }
}
