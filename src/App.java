import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        LinkedList<Rota> rotas = new LinkedList<>();
        LinkedList<Caminhao> caminhoes = new LinkedList<>();
        int quant = (int) (Math.random() * 20);
        for (int i = 0; i < 1000; i++) {
            rotas.add(new Rota(i, (Math.random() * 100)));
        }
        for (int i = 0; i < quant; i++) {
            caminhoes.add(new Caminhao());
        }
        algGuloso((LinkedList<Rota>) rotas.clone(), (LinkedList<Caminhao>) caminhoes.clone());
    }

    public static void algGuloso(List<Rota> rotas, List<Caminhao> caminhoes) {
        Collections.sort(rotas);
        for (Rota rota : rotas) {
            Caminhao min = caminhoes.stream().min((c1, c2) -> c1.compareTo(c2)).get();
            min.rotas.add(rota);
        }
        desvioPadrao(caminhoes);
    }

    /**
     * Calcula o desvio padrão entre os caminhões
     * 
     * @param caminhoes
     * @return
     */
    public static double desvioPadrao(List<Caminhao> caminhoes) {
        double media = 0;
        for (Caminhao caminhao : caminhoes) {
            media += caminhao.getSoma();
        }
        media /= caminhoes.size();
        double diferenca = 0;
        for (Caminhao caminhao : caminhoes) {
            diferenca += Math.pow(caminhao.getSoma() - media, 2);
        }
        diferenca = Math.sqrt(diferenca / caminhoes.size());
        System.out.println("DIFERENCA");
        System.out.println(diferenca);
        return diferenca;
    }
}
