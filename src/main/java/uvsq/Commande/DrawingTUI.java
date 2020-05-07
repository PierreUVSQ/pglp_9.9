package uvsq.Commande;

import uvsq.Forme.*;

import java.util.Scanner;

public class DrawingTUI {

  private Scanner scanner;
  private Dessin dessin;
  private Dao dao;

  public DrawingTUI() {

    this.scanner = new Scanner(System.in, "UTF-8");
    this.dessin = new Dessin("DessinTest");
    dao = DaoFactory.getDessinDao();
  }

  public Command nextCommand() {

    String in;
    in = this.scanner.nextLine();
    Command command = null;
    String nomForme = null;
    try {
      in = in.replaceAll("\\s+", "");
      String chaineDeCommande = in.substring(0, in.indexOf("("));
      if (chaineDeCommande.matches("move")) {
        nomForme = in.substring(in.indexOf("(") + 1, in.indexOf(","));
        int x = Integer.parseInt(in.substring(in.lastIndexOf("(") + 1, in.lastIndexOf(",")));
        int y = Integer.parseInt(in.substring(in.lastIndexOf(",") + 1, in.indexOf(")")));
        for (Element elem : dessin.getListe()) {
          if (elem.getNom().contentEquals(nomForme)) {
            command = new DeplacerCommand((Forme) elem, x, y);
            break;
          }
        }

      } else if (chaineDeCommande.matches("group")) {

        String nomGroupe = in.substring(in.indexOf("(") + 1, in.indexOf(","));
        String[] tableDeNom = in.substring(in.lastIndexOf("(") + 1, in.indexOf(")")).split(",");
        command = new CreationGroupeCommand(nomGroupe, tableDeNom, dessin);
      } else if (chaineDeCommande.matches("moveGroup")) {

        String[] info = in.split(",");
        String nomGroupe = in.substring(in.indexOf("(") + 1, in.indexOf(","));
        int x = Integer.parseInt(in.substring(in.lastIndexOf("(") + 1, in.lastIndexOf(",")));
        int y = Integer.parseInt(in.substring(in.lastIndexOf(",") + 1, in.indexOf(")")));
        for (Element elem : dessin.getListe()) {
          if (elem.getNom().matches(nomGroupe)) {
            command = new DeplacerGroupeCommand((Groupe) elem, x, y);
            break;
          }
        }
      } else if (chaineDeCommande.matches("delete")) {

        nomForme = in.substring(in.indexOf("(") + 1, in.indexOf(")"));
        command = new SupprimerElementCommand(dessin, nomForme);

      } else if (chaineDeCommande.matches("save")) {
        String nom = in.substring(in.indexOf("(") + 1, in.indexOf(")"));
        Dao.nom = nom;
        command = new SaveCommand(this.dessin);
      } else if (chaineDeCommande.matches("load")) {
        System.out.println("Load" + this.dessin.getNom());
        String nom = in.substring(in.indexOf("(") + 1, in.indexOf(")"));
        Dao.nom = nom;
        command = new LoadCommand(Dao.nom, this);

      } else if (chaineDeCommande.matches("quit")) {
        command = new QuitterCommand();
      } else if (in.contains("=") /*creation[1].matches("=")*/) {
        String creation[] = in.split("=");
        nomForme = in.substring(0, in.indexOf("="));
        String typeForme = creation[1].substring(0, creation[1].indexOf("("));
        if (typeForme.matches("Cercle")) {
          try {
            int x = Integer.parseInt(in.substring(in.lastIndexOf("(") + 1, in.indexOf(",")));
            int y = Integer.parseInt(in.substring(in.indexOf(",") + 1, in.indexOf(")")));
            int rayon =
                Integer.parseInt(in.substring(in.lastIndexOf(",") + 1, in.lastIndexOf(")")));
            command = new CreationCercleCommand(nomForme, new Point(x, y), rayon, dessin);
          } catch (NumberFormatException ne) {
            System.out.println("Veuillez entrer des informations valides");
          }
        } else if (typeForme.matches("Carre")) {
          String[] carreInfo = in.split(",");
          int x = Integer.parseInt(carreInfo[0].substring(carreInfo[0].lastIndexOf("(") + 1));
          int y = Integer.parseInt(carreInfo[1].substring(0, carreInfo[1].indexOf(")")));
          int cote = Integer.parseInt(carreInfo[2].substring(0, carreInfo[2].length() - 1));
          command = new CreationCarreCommand(nomForme, new Point(x, y), cote, dessin);
        } else if (typeForme.matches("Rectangle")) {
          String[] rectangleInfo = in.split(",");
          int x =
              Integer.parseInt(rectangleInfo[0].substring(rectangleInfo[0].lastIndexOf("(") + 1));
          int y = Integer.parseInt(rectangleInfo[1].substring(0, 2));
          int longueur = Integer.parseInt(rectangleInfo[2]);
          int hauteur =
              Integer.parseInt(rectangleInfo[3].substring(0, rectangleInfo[3].indexOf(")") - 1));
          command =
              new CreationRectangleCommand(nomForme, new Point(x, y), longueur, hauteur, dessin);
        } else if (typeForme.matches("Triangle")) {
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
                  nomForme, new Point(ax, ay), new Point(bx, by), new Point(cx, cy), dessin);
        }
      }
    } catch (StringIndexOutOfBoundsException se) {
      System.out.println("Veuillez entrer des informations valides");
    }
    return command;
  }

  public void afficherDessin() {

    for (Element elem : dessin.getListe()) {
      elem.afficher();
    }
  }

  public void setDessin(Dessin dessin) {
    this.dessin = dessin;
  }
}
