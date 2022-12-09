import java.util.LinkedList;
import java.util.List;

public class Greedy {

    public LinkedList<Caminhao> algGuloso(List<Rota> rotas, int quant) {
        System.out.println("\nGULOSO");
        LinkedList<Caminhao> caminhoes = Helper.novaListaCaminhoes(quant);
        long inicio = System.currentTimeMillis();
        for (Rota rota : rotas) {
            Caminhao min = caminhoes.stream().min((c1, c2) -> c1.compareTo(c2)).get();
            min.rotas.add(rota);
        }
        Caminhao max = caminhoes.stream().max((c1, c2) -> c1.compareTo(c2)).get();
        long diferenca = System.currentTimeMillis() - inicio;
        System.out.println("Tempo guloso: " + diferenca);
        System.out.println("Caminhão máximo:");
        System.out.println(max.toString());
        System.out.println("Resultado encontrado: " + max.getSoma());
        return caminhoes;
    }
}
