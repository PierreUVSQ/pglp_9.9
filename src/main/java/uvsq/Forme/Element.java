package uvsq.Forme;

public abstract class Element {

    private String nom;

    public Element(String nom){
        this.nom = nom;
    }
    public abstract void afficher();
    public abstract void deplacer(int x, int y);



}
