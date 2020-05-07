package uvsq.Commande;

import uvsq.Forme.DaoFactory;
import uvsq.Forme.Dessin;

public class SaveCommand implements Command {

  private Dessin dessin;

  public SaveCommand(Dessin dessin) {
    this.dessin = dessin;
  }

  public void execute() {
    System.out.println(this.dessin.getNom());
    DaoFactory.getDessinDao().create(this.dessin);
  }
}
