package uvsq.Commande;

import uvsq.Forme.Element;
import uvsq.Forme.Forme;
import uvsq.Forme.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DrawingTUI {

  private Scanner scanner;
  private Map<String, Element> elementMap;
  private List<Element> elementListe;

  public DrawingTUI() {

    this.scanner = new Scanner(System.in, "UTF-8");
    this.elementListe = new ArrayList<>();
  }

  public Command nextCommand() {

    String in;
    in = this.scanner.nextLine();
    Command command = null;
    String nomForme = null;
    try {
      String chaineDeCommande = in.substring(0, in.indexOf("("));
      String creation[] = in.split(" ");
      in = in.replaceAll("\\s+", "");
      if (chaineDeCommande.matches("move")) {
        nomForme = in.substring(in.indexOf("(") + 1, in.indexOf(","));
        int x = Integer.parseInt(in.substring(in.lastIndexOf("(") + 1, in.lastIndexOf(",")));
        int y = Integer.parseInt(in.substring(in.lastIndexOf(",") + 1, in.indexOf(")")));
        for (Element elem : elementListe) {
          if (elem.getNom().contentEquals(nomForme)) {
            command = new DeplacerCommand((Forme) elem, x, y);
            break;
          }
        }

        /*  if(this.elementMap.containsKey(nomForme)){
                    command = new DeplacerCommand((Forme) this.elementMap.get(nomForme), x, y);
                }
        */
      } else if (chaineDeCommande.matches("group")) {

        String nomGroupe = in.substring(in.indexOf("(") +1, in.indexOf(","));
        String[] tableDeNom =
            in.substring(in.lastIndexOf("(") + 1, in.indexOf(")")).split(",");
        command = new CreationGroupeCommand(nomGroupe, tableDeNom, elementListe);
      } else if (chaineDeCommande.matches("delete")) {

        nomForme = in.substring(in.indexOf("(") + 1, in.indexOf(")"));
        command = new SupprimerElementCommand(elementListe, nomForme);

      } else if (creation[1].matches("=")) {

        nomForme = in.substring(0, in.indexOf("="));
        String typeForme = null;
        if (creation[2].substring(0, creation[2].indexOf("(")).matches("Cercle")) {
          try {
            int x = Integer.parseInt(in.substring(in.lastIndexOf("(") + 1, in.indexOf(",")));
            int y = Integer.parseInt(in.substring(in.indexOf(",") + 1, in.indexOf(")")));
            int rayon =
                Integer.parseInt(in.substring(in.lastIndexOf(",") + 1, in.lastIndexOf(")")));
            command = new CreationCercleCommand(nomForme, new Point(x, y), rayon, elementListe);
          } catch (NumberFormatException ne) {
            System.out.println("Veuillez entrer des informations valides");
          }
        } else if (creation[2].substring(0, creation[2].indexOf("(")).matches("Carre")) {
          String[] carreInfo = in.split(",");
          int x = Integer.parseInt(carreInfo[0].substring(carreInfo[0].lastIndexOf("(") + 1));
          int y = Integer.parseInt(carreInfo[1].substring(0, carreInfo[1].indexOf(")")));
          int cote = Integer.parseInt(carreInfo[2].substring(0, carreInfo[2].length() - 1));
          command = new CreationCarreCommand(nomForme, new Point(x, y), cote, elementListe);
        } else if (creation[2].substring(0, creation[2].indexOf("(")).matches("Rectangle")) {
          String[] rectangleInfo = in.split(",");
          int x =
              Integer.parseInt(rectangleInfo[0].substring(rectangleInfo[0].lastIndexOf("(") + 1));
          int y = Integer.parseInt(rectangleInfo[1].substring(0, 2));
          int longueur = Integer.parseInt(rectangleInfo[2]);
          int hauteur =
              Integer.parseInt(rectangleInfo[3].substring(0, rectangleInfo[3].indexOf(")") - 1));
          command =
              new CreationRectangleCommand(
                  nomForme, new Point(x, y), longueur, hauteur, elementListe);
        } else if (creation[2].substring(0, creation[2].indexOf("(")).matches("Triangle")) {
          String[] triangleInfo = in.split(",");
          int ax =
              Integer.parseInt(triangleInfo[0].substring(triangleInfo[0].lastIndexOf("(") + 1));
          int ay = Integer.parseInt(triangleInfo[1].substring(0, triangleInfo[1].indexOf(")") - 1));
          int bx = Integer.parseInt(triangleInfo[2].substring(1));
          int by = Integer.parseInt(triangleInfo[3].substring(0, triangleInfo[3].indexOf(")") - 1));
          int cx = Integer.parseInt(triangleInfo[2].substring(1));
          int cy = Integer.parseInt(triangleInfo[3].substring(0, triangleInfo[3].indexOf(")") - 1));
          command =
              new CreationTriangleCommand(
                  nomForme, new Point(ax, ay), new Point(bx, by), new Point(cx, cy), elementListe);
        }

      } else if (chaineDeCommande.matches("moveGroup")) {

        String[] info = in.split(",");
        String nomGroupe = info[0].substring(10);
        int x = Integer.parseInt(info[1].substring(0));
        int y = Integer.parseInt(info[2].substring(0, info[2].length() - 1));
        for (Element elem : elementListe) {
          if (elem.getNom().contentEquals(nomGroupe)) {
            command = new DeplacerCommand((Forme) elem, x, y);
            break;
          }
        }
      }
    } catch (StringIndexOutOfBoundsException se) {
      se.printStackTrace();
      // System.out.println("Veuillez entrer des informations valides");
    }
    return command;
  }

  public void afficherDessin() {

    for (Element elem : elementListe) {
      elem.afficher();
    }
  }
}
