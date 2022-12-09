import java.util.LinkedList;
import java.util.List;

public class Caminhao implements Comparable<Caminhao> {
    public List<Rota> rotas;

    public Caminhao() {
        rotas = new LinkedList<Rota>();
    }

    public double getSoma() {
        return this.rotas.stream().map((rota) -> rota.getComprimento()).reduce(0.0, (a, b) -> a + b);
    }

    @Override
    public int compareTo(Caminhao c) {
        return (int) ((this.getSoma() - c.getSoma()) * 100);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Rota rota : rotas) {
            str.append(rota.getId() + " - " + rota.getComprimento() + "\n");
        }
        str.append("Soma: " + getSoma());
        return str.toString();
    }
}
