import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class App {

    // Usando o caminhão com maior soma de rotas como diferença
    public static void main(String[] args) throws Exception {
        LinkedList<Rota> rotas = new LinkedList<>();
        int quant = (int) (Math.random() * 20);
        for (int i = 0; i < 1000; i++) {
            rotas.add(new Rota(i, (Math.random() * 100)));
        }
        algGuloso((LinkedList<Rota>) rotas.clone(), novaListaCaminhoes(quant));
    }

    public static LinkedList<Caminhao> novaListaCaminhoes(int quant) {
        LinkedList<Caminhao> caminhoes = new LinkedList<>();
        for (int i = 0; i < quant; i++) {
            caminhoes.add(new Caminhao());
        }
        return caminhoes;
    }

    public static double algGuloso(List<Rota> rotas, List<Caminhao> caminhoes) {
        Collections.sort(rotas);
        for (Rota rota : rotas) {
            Caminhao min = caminhoes.stream().min((c1, c2) -> c1.compareTo(c2)).get();
            System.out.println(min.getSoma());
            min.rotas.add(rota);
        }
        Caminhao max = caminhoes.stream().max((c1, c2) -> c1.compareTo(c2)).get();
        System.out.println(max.getSoma());
        return max.getSoma();
    }

}
