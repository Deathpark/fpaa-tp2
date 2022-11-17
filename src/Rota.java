public class Rota implements Comparable<Rota> {
    private int id;
    private double comprimento;

    public Rota(int id, double comprimento) {
        this.id = id;
        this.comprimento = comprimento;
    }

    public double getComprimento() {
        return comprimento;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Rota r) {
        return (int) ((this.comprimento - r.getComprimento())*100);
    }
}
