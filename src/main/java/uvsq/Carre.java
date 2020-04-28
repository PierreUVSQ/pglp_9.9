package uvsq;

public class Carre extends Forme {

    private int x;
    private int y;

    @Override
    public void afficher() {
        System.out.println();
    }

    @Override
    public void deplacer(int x, int y) {
        this.x = x;
        this.y = y;

    }
}
