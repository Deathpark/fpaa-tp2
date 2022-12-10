import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class App {
    static String NOME_ARQ = "./dados/caminhoes_compacto.txt";
    static String NOME_ARQ_TEMP = "./dados/temperaturas.txt";

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

        ProgDinamica p = new ProgDinamica();
        Greedy g = new Greedy();
        Backtracking b = new Backtracking();

        // Usando o caminhão com maior soma de rotas como diferença
        System.out.println("\nQUESTÃO 1");
        p.progDinamica(rotas, quant);
        List<Caminhao> caminhoes = g.algGuloso(rotas, quant);
        Helper.printListaCaminhoes(caminhoes);
        caminhoes = b.backtracking(rotas, quant);
        Helper.printListaCaminhoes(caminhoes);

        System.out.println("\nQUESTÃO 2");
        divisaoConquista();
    }

    private static void divisaoConquista() {
        ArquivoLeitura arquivo = new ArquivoLeitura(NOME_ARQ_TEMP);

        LinkedList<LinkedList<Integer>> temperaturas = new LinkedList<>();
        LinkedList<Integer> todasTemperaturas = new LinkedList<>();

        String linha = arquivo.lerLinha();
        while (linha != null) {
            LinkedList<Integer> temp = new LinkedList<>();
            String[] dados = linha.split(";");

            for (int i = 0; i < dados.length; i++) {
                temp.add(Integer.parseInt(dados[i]));
            }

            temperaturas.add(temp);

            linha = arquivo.lerLinha();
        }

        temperaturas.stream().forEach((temperaturaAnual) -> {

            // Criação de lista de diferenças
            LinkedList<Integer> listaDiferencas = new LinkedList<>();
            for (int i = 0; i < (temperaturaAnual.size() - 1); i++) {
                listaDiferencas.add(temperaturaAnual.get(i + 1) - temperaturaAnual.get(i));
            }

            DivisaoConquista divisaoConquista = new DivisaoConquista(listaDiferencas);

            int resultado = divisaoConquista.maiorSomaDeTemperaturas(listaDiferencas, 0, listaDiferencas.size() - 1);
            System.out.println("Período que somou: " + resultado + " graus.");
            todasTemperaturas.addAll(temperaturaAnual);
        });

        LinkedList<Integer> listaTodasDiferencas = new LinkedList<>();
        for(int i = 0; i < (todasTemperaturas.size()-1); i++) {
            listaTodasDiferencas.add(todasTemperaturas.get(i+1) - todasTemperaturas.get(i));
        }

        DivisaoConquista divisaoConquista = new DivisaoConquista(listaTodasDiferencas);

        int resultado = divisaoConquista.maiorSomaDeTemperaturas(listaTodasDiferencas, 0, listaTodasDiferencas.size()-1);
        System.out.println("Comparação dos 5 anos em conjunto:");
        System.out.println("Período que somou: " + resultado + " graus.");
    }
}