package uvsq.Commande;

import uvsq.Forme.Element;
import uvsq.Forme.Forme;
import uvsq.Forme.Point;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DrawingTUI {

  private Scanner scanner;
  private Map<String, Element> elementMap;
  private List<Element> elementListe;

  public DrawingTUI(Map<String, Element> liste, List<Element> elementListe ) {

    this.scanner = new Scanner(System.in, "UTF-8");
    this.elementMap = liste;
    this.elementListe = elementListe;

  }

  public Command nextCommand() {

    Command res = null;
    String in;
    in = this.scanner.next();
    Command command = null;
    String nomForme = null;
    String chaineDeCommande = in.substring(0, in.indexOf("("));
    String creation[] = in.split(" ");
    if (chaineDeCommande.matches("move")) {
        nomForme = in.substring(in.indexOf("(") + 1, in.indexOf(","));
        int x = Integer.parseInt(in.substring(in.lastIndexOf("(") + 1, in.indexOf(",")));
        int y = Integer.parseInt(in.substring(in.lastIndexOf(",") + 1, in.indexOf(")")));
        if(this.elementMap.containsKey(nomForme)){
            command = new DeplacerCommand((Forme) this.elementMap.get(nomForme), x, y);
        }

    } else if (chaineDeCommande.matches("delete")) {

        nomForme = in.substring(in.indexOf("(") + 1, in.indexOf(")"));
        command = new SupprimerElementCommand(elementListe, nomForme);

    } else if (creation[1].matches("=")) {

        nomForme = in.substring(0, in.indexOf("="));
        String typeForme = null;
        if(creation[3].substring(0, creation[3].indexOf("(")).matches("Cercle")) {
            int x = Integer.parseInt(in.substring(in.lastIndexOf("(") + 1, in.indexOf(",")));
            int y = Integer.parseInt(in.substring(in.lastIndexOf(",") + 1, in.indexOf(")")));
            int rayon = Integer.parseInt(in.substring(in.lastIndexOf(",") + 1, in.lastIndexOf(")")));
            command = new CreationCercleCommand(nomForme, new Point(x, y), rayon, elementListe);
        }

        if(creation[3].substring(0, creation[3].indexOf("(")).matches("Carre")) {
            int x = Integer.parseInt(in.substring(in.lastIndexOf("(") + 1, in.indexOf(",")));
            int y = Integer.parseInt(in.substring(in.lastIndexOf(",") + 1, in.indexOf(")")));
            int rayon = Integer.parseInt(in.substring(in.lastIndexOf(",") + 1, in.lastIndexOf(")")));
            command = new CreationCercleCommand(nomForme, new Point(x, y), rayon, elementListe);
        }

    } else if (chaineDeCommande.matches("createRectangle")) {

    } else if (chaineDeCommande.matches("createSquare")) {

    } else if (chaineDeCommande.matches("createTriangle")) {

    } else if (chaineDeCommande.matches("group")) {

    }
    return res;
  }

  public void afficherDessin() {}
}
