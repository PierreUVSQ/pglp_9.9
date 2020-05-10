package uvsq.forme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Groupe extends Element {

  private List<Element> liste;

  /**
   * Ensemble d'éléments(groupes et formes).
   * @param nom Nom du groupe
   */
  public Groupe(String nom) {

    super(nom);
    liste = new ArrayList<>();
  }

  public void ajoutForme(Element elem) {
    this.liste.add(elem);
  }

  /**
   * Affiche l'ensemble des éléments du groupe.
   */
  public void afficher() {
    System.out.println("Groupe " + this.nom + ": ");
    for (Element elem : this.liste) {
      elem.afficher();
    }
    System.out.println(";");
  }

  /**
   * Déplace l'ensemble des éléments dans une certaine direction.
   * @param x Abscisse
   * @param y Ordonnée
   */
  public void deplacerDirection(int x, int y) {
    for (Element elem : this.liste) {
      elem.deplacerDirection(x, y);
    }
  }

  public List<Element> getListeNonModifiable() {

    return Collections.unmodifiableList(this.liste);
  }
}
