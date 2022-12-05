import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class App {

    // Usando o caminhão com maior soma de rotas como diferença
    public static void main(String[] args) throws Exception {
        LinkedList<Rota> rotas = new LinkedList<>();
        int quant = 3; // (int) (Math.random() * 20);
        for (int i = 0; i < 4; i++) {
            rotas.add(new Rota(i, (i + 1) * 10));
        }
        Backtracking b = new Backtracking();
        int[] r = rotas.stream().mapToInt(rota -> (int) rota.getComprimento()).toArray();
        System.out.println(b.distribuirRotas(r, quant));
        ProgDinamica p = new ProgDinamica();
        System.out.println(p.progDinamica(r, quant));

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
            min.rotas.add(rota);
        }
        Caminhao max = caminhoes.stream().max((c1, c2) -> c1.compareTo(c2)).get();
        System.out.println(max.getSoma());
        return max.getSoma();
    }

    public static double progDinamica(List<Rota> rotas, List<Caminhao> caminhoes) {
        rotas.sort((r1, r2) -> (int) ((r2.getComprimento() - r1.getComprimento()) * 100));

        Caminhao[][][] tabela = new Caminhao[caminhoes.size()][rotas.size()][caminhoes.size()];
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