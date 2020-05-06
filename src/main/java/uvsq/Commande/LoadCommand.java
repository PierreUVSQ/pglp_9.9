package uvsq.Commande;

import uvsq.DrawingApp;
import uvsq.Forme.DaoFactory;
import uvsq.Forme.Dessin;

public class LoadCommand implements Command{

    private String nom;
    private DrawingTUI draw;
   public LoadCommand(String nom, DrawingTUI draw) {
       this.nom = nom;
       this.draw = draw;
   }

    @Override
    public void execute() {
       System.out.println(this.nom);
       this.draw.setDessin(DaoFactory.getDessinDao().find(this.nom));
    }
}
