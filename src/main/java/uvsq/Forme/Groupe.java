package uvsq.Forme;

import uvsq.Forme.Element;
import uvsq.Forme.Forme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Groupe extends Element {

  private List<Element> liste;

  public Groupe(String nom) {

    super(nom);
    liste = new ArrayList<>();
  }

  public void ajoutForme(Element elem) {
    this.liste.add(elem);
  }

  public void afficher() {
    for (Element elem : this.liste) {
      elem.afficher();
    }
  }

  public void deplacerDirection(int x, int y) {
    for (Element elem : this.liste) {
      elem.deplacerDirection(x, y);
    }
  }

  public List<Element> getListeNonModifiable(){

    return Collections.unmodifiableList(this.liste);

  }

}
