import java.util.LinkedList;
import java.util.List;

public class Helper {
    public static LinkedList<Caminhao> novaListaCaminhoes(int quant) {
        LinkedList<Caminhao> caminhoes = new LinkedList<>();
        for (int i = 0; i < quant; i++) {
            caminhoes.add(new Caminhao());
        }
        return caminhoes;
    }

    public static void printListaCaminhoes(List<Caminhao> caminhoes) {

        for (int i = 0; i < caminhoes.size(); i++) {
            System.out.println("\nCaminhão " + (i + 1));
            System.out.println(caminhoes.get(i));
        }
    }
}
