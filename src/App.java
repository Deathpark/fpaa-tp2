import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class App {
    static String NOME_ARQ = "./dados/caminhoes_compacto.txt";

    // Usando o caminhão com maior soma de rotas como diferença
    public static void main(String[] args) throws Exception {
        ArquivoLeitura arquivo = new ArquivoLeitura(NOME_ARQ);

        LinkedList<Rota> rotas = new LinkedList<>();
        int quant = Integer.parseInt(arquivo.lerLinha());
        System.out.println("Caminhões: " + quant);
        String linha = arquivo.lerLinha();
        while (linha != null) {

            String[] dados = linha.split(";");
            rotas.add(new Rota(Integer.parseInt(dados[0]), Integer.parseInt(dados[1])));
            linha = arquivo.lerLinha();
        }

        Collections.sort(rotas, Collections.reverseOrder());
        int[] r = rotas.stream().mapToInt(rota -> (int) rota.getComprimento()).toArray();
        Backtracking b = new Backtracking();
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

}