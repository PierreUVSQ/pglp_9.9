package uvsq.commande;

import uvsq.forme.DaoFactory;

public class SupprimerDessinCommand implements Command {

  private String nom;

  /**
   * Commande qui supprime un dessin dans la base de données.
   *
   * @param nom Nom du dessin à supprimer
   */
  public SupprimerDessinCommand(String nom) {
    this.nom = nom;
  }

  /** Supprime le dessin dans la base de données. */
  @Override
  public void execute() {
    DaoFactory.getDessinDao().delete(nom);
  }
}
