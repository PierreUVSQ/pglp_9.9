package uvsq.commande;

import java.security.InvalidParameterException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import uvsq.forme.Dao;
import uvsq.forme.DaoFactory;
import uvsq.forme.Dessin;
import uvsq.forme.Element;
import uvsq.forme.Forme;
import uvsq.forme.Groupe;
import uvsq.forme.Point;

public class DrawingTui {

  private Scanner scanner;
  private Dessin dessin;
  private Dao dao;

  /**
   * Classe chargé de l'interface textuelle utilisateur.
   */
  public DrawingTui() {

    this.scanner = new Scanner(System.in, "UTF-8");
    this.dessin = new Dessin("DessinTest");
    dao = DaoFactory.getDessinDao();
  }

  /**
   * Vérifie si une chaine contient des caractères spéciaux.
   *
   * @param chaine Chaine à vérifier
   * @throws InvalidParameterException Si un argument est invalide
   */
  public void verifieChaine(String chaine) throws InvalidParameterException {

    Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(chaine);
    boolean b = m.find();

    if (b) {
      throw new InvalidParameterException();
    }
  }

  /**
   * Analyse l'input.
   *
   * @return La commande correspondante à l'input
   */
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
        verifieChaine(nomGroupe);
        String[] tableDeNom = in.substring(in.lastIndexOf("(") + 1, in.indexOf(")")).split(",");
        command = new CreationGroupeCommand(nomGroupe, tableDeNom, dessin);
      } else if (chaineDeCommande.matches("moveGroup")) {

        String[] info = in.split(",");
        String nomGroupe = in.substring(in.indexOf("(") + 1, in.indexOf(","));
        verifieChaine(nomGroupe);
        int x = Integer.parseInt(in.substring(in.lastIndexOf("(") + 1, in.lastIndexOf(",")));
        int y = Integer.parseInt(in.substring(in.lastIndexOf(",") + 1, in.indexOf(")")));
        for (Element elem : dessin.getListe()) {
          if (elem.getNom().matches(nomGroupe)) {
            command = new DeplacerGroupeCommand((Groupe) elem, x, y);
            break;
          }
        }
      } else if (chaineDeCommande.matches("remove")) {

        nomForme = in.substring(in.indexOf("(") + 1, in.indexOf(")"));
        verifieChaine(nomForme);
        command = new SupprimerElementCommand(dessin, nomForme);

      } else if (chaineDeCommande.matches("save")) {
        String nom = in.substring(in.indexOf("(") + 1, in.indexOf(")"));
        verifieChaine(nom);
        Dao.nom = nom;
        command = new SaveCommand(this.dessin);
      } else if (chaineDeCommande.matches("load")) {
        String nom = in.substring(in.indexOf("(") + 1, in.indexOf(")"));
        verifieChaine(nom);
        Dao.nom = nom;
        command = new LoadCommand(Dao.nom, this);

      } else if (chaineDeCommande.matches("delete")) {
        String nom = in.substring(in.indexOf("(") + 1, in.indexOf(")"));
        verifieChaine(nom);
        Dao.nom = nom;
        command = new SupprimerDessinCommand(Dao.nom);

      } else if (chaineDeCommande.matches("quit")) {
        command = new QuitterCommand();
      } else if (in.contains("=") /*creation[1].matches("=")*/) {
        String[] creation = in.split("=");
        nomForme = in.substring(0, in.indexOf("="));
        verifieChaine(nomForme);
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
    } catch (StringIndexOutOfBoundsException | InvalidParameterException ie) {
      System.out.println(
          "Veuillez entrer des informations valides.\n "
              + "Un argument ne peut contenir de caractères spéciaux");
    }
    return command;
  }

  /** Affiche le dessin complet. */
  public void afficherDessin() {

    for (Element elem : dessin.getListe()) {
      elem.afficher();
    }
  }

  /**
   * Définie le dessin sur lequel on travail.
   *
   * @param dessin Dessin à définir
   */
  public void setDessin(Dessin dessin) {
    this.dessin = dessin;
  }
}
