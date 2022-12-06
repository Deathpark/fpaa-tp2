import java.util.LinkedList;
import java.util.List;

public class DivisaoConquista {
    public List temperaturas;

    public DivisaoConquista(LinkedList<Integer> temperaturas) {
        this.temperaturas = temperaturas;
    }

    int periodoComMaiorSoma(LinkedList<Integer> temperaturas, int esquerda, int meio, int direita) {
        // Elementos à esquerda
        int sum = 0;
        int somatorioEsquerda = Integer.MIN_VALUE;
        for (int i = meio; i >= esquerda; i--) {
            sum = sum + temperaturas.get(i);
            if (sum > somatorioEsquerda) {
                somatorioEsquerda = sum;
            }
        }
 
        // Elementos à direita
        sum = 0;
        int somatorioDireita = Integer.MIN_VALUE;
        for (int i = meio; i <= direita; i++) {
            sum = sum + temperaturas.get(i);
            if (sum > somatorioDireita) {
                somatorioDireita = sum;
            }
        }

        return Math.max(somatorioEsquerda + somatorioDireita - temperaturas.get(meio), Math.max(somatorioEsquerda, somatorioDireita));
    }
 
    int maiorSomaDeTemperaturas(LinkedList<Integer> temperaturas, int esquerda, int direita) {
        if (esquerda > direita) {
            return Integer.MIN_VALUE;
        }

        if (esquerda == direita) {
            return temperaturas.get(esquerda);
        }
 
        int meio = (esquerda + direita) / 2;
        
        int somatorioEsquerda = maiorSomaDeTemperaturas(temperaturas, esquerda, meio-1);
        int somatorioDireita = maiorSomaDeTemperaturas(temperaturas, meio+1, direita);
        int somatorioMeio = periodoComMaiorSoma(temperaturas, esquerda, meio, direita);

        if(somatorioMeio > somatorioEsquerda + somatorioDireita) {
            System.out.println("A metade do ano teve uma elevação maior de temperatura com somatória de: " + somatorioMeio);
            return somatorioMeio;
        } else if(somatorioEsquerda > somatorioDireita) {
            System.out.println("A primeira metade do ano teve uma elevação maior de temperatura com somatória de: " + somatorioEsquerda);
            return somatorioEsquerda;
        } else {
            System.out.println("A segunda metade do ano teve uma elevação maior de temperatura com somatória de: " + somatorioDireita);
            return somatorioDireita;
        }
    }
}
