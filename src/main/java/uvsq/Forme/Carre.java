package uvsq.Forme;

public class Carre extends Forme {

    private int x;
    private int y;

    public Carre(String nom){
        super(nom);

    }

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
