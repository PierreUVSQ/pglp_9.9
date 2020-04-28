package uvsq;

import java.util.ArrayList;
import java.util.List;

public class Groupe implements Element {

  private List<Forme> liste;

  public Groupe() {
    liste = new ArrayList<>();
  }

  public void ajoutForme(Forme forme){

      this.liste.add(forme);
  }

  public void deplacer(int x, int y){
      for(Forme fo : this.liste){
          fo.deplacer(x, y);
      }
  }

  


}
