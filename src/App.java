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
        ProgDinamica p = new ProgDinamica();
        Greedy g = new Greedy();
        Backtracking b = new Backtracking();
        System.out.println("Resultado encontrado: " + p.progDinamica(r, quant));
        System.out.println("Resultado encontrado: " + g.algGuloso(rotas, quant));
        System.out.println("Resultado encontrado: " + b.distribuirRotas(r, quant));

    }
}