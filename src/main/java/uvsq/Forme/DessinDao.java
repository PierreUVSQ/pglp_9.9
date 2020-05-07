package uvsq.Forme;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DessinDao extends Dao<Dessin> {

  @Override
  public Dessin create(Dessin obj) {
    System.out.println("Enter create dessin");
    this.connect();
    try (PreparedStatement dessinInsert =
            this.connect.prepareStatement("INSERT INTO Dessin(dnom) values(?)");
        PreparedStatement carreInsert =
            this.connect.prepareStatement("INSERT INTO CarreDessin(dnom, nom) VALUES(?, ?)");
        PreparedStatement cercleInsert =
            this.connect.prepareStatement("INSERT INTO CercleDessin(dnom, nom) VALUES(?, ?)");
        PreparedStatement triangleInsert =
            this.connect.prepareStatement("INSERT INTO TriangleDessin(dnom, nom) VALUES(?, ?)");
        PreparedStatement groupeDessinInsert =
            this.connect.prepareStatement("INSERT INTO GroupeDessin(dnom, nom) VALUES(?, ?)");
        PreparedStatement rectangleInsert =
            this.connect.prepareStatement(
                "INSERT INTO RectangleDessin(dnom, nom) VALUES(?, ?)"); ) {
      System.out.println("Insert: " + obj.getNom());
      dessinInsert.setString(1, obj.getNom());
      dessinInsert.executeUpdate();
      List<Element> listElem = obj.getElementList();
      Dao tmp;
      for (Element elem : listElem) {
        System.out.println("Elem list");
        if (elem instanceof Groupe) {
          tmp = new GroupeDao();
          tmp.create((Groupe) elem);
          groupeDessinInsert.setString(1, obj.getNom());
          groupeDessinInsert.setString(2, Dao.nom + ":" + elem.nom);
          groupeDessinInsert.executeUpdate();
        } else if (elem instanceof Cercle) {
          tmp = new CercleDao();
          tmp.create((Cercle) elem);
          cercleInsert.setString(1, obj.getNom());
          cercleInsert.setString(2, Dao.nom + ":" + elem.nom);
          cercleInsert.executeUpdate();
        } else if (elem instanceof Rectangle) {
          System.out.println("Insertion rectangle: " + elem.getNom());
          tmp = new RectangleDao();
          tmp.create((Rectangle) elem);
          rectangleInsert.setString(1, obj.getNom());
          rectangleInsert.setString(2, Dao.nom + ":" + elem.nom);
          rectangleInsert.executeUpdate();
        } else if (elem instanceof Carre) {
          tmp = new CarreDao();
          tmp.create((Carre) elem);
          carreInsert.setString(1, obj.getNom());
          carreInsert.setString(2, Dao.nom + ":" + elem.nom);
          carreInsert.executeUpdate();
        } else if (elem instanceof Triangle) {
          tmp = new TriangleDao();
          tmp.create((Triangle) elem);
          triangleInsert.setString(1, obj.getNom());
          triangleInsert.setString(2, Dao.nom + ":" + elem.nom);
          triangleInsert.executeUpdate();
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    this.disconnect();

    return obj;
  }

  @Override
  public Dessin find(String id) {
    Dessin dessin = null;
    Dao dao;
    this.connect();
    try (PreparedStatement select =
            this.connect.prepareStatement("SELECT D.dnom FROM Dessin D WHERE D.dnom = ?");
        PreparedStatement selectRectangle =
            this.connect.prepareStatement("SELECT * FROM RectangleDessin RD WHERE RD.dnom = ?");
        PreparedStatement selectTriangle =
            this.connect.prepareStatement("SELECT * FROM TriangleDessin TD WHERE TD.dnom = ?");
        PreparedStatement selectCercle =
            this.connect.prepareStatement("SELECT * FROM CercleDessin CD WHERE CD.dnom = ?");
        PreparedStatement selectCarre =
            this.connect.prepareStatement("SELECT * FROM CarreDessin CD WHERE CD.dnom = ?");
        PreparedStatement selectGroupe =
            this.connect.prepareStatement("SELECT * FROM GroupeDessin GD WHERE GD.dnom = ?"); ) {
      select.setString(1, id);
      selectTriangle.setString(1, id);
      selectCarre.setString(1, id);
      selectRectangle.setString(1, id);
      selectCercle.setString(1, id);
      selectGroupe.setString(1, id);
      try (ResultSet res = select.executeQuery();
          ResultSet resTriangle = selectTriangle.executeQuery();
          ResultSet resCarre = selectCarre.executeQuery();
          ResultSet resRectangle = selectRectangle.executeQuery();
          ResultSet resCercle = selectCercle.executeQuery();
          ResultSet resGroupe = selectGroupe.executeQuery(); ) {
        if (res.next()) {
          String n = res.getString("dnom");
        }
        dessin = new Dessin(id);
        while (resCarre.next()) {
          dao = new CarreDao();
          dessin.ajoutElement((Carre) dao.find(resCarre.getString("nom")));
        }

        while (resRectangle.next()) {
          System.out.println("trouvé");
          dao = new RectangleDao();
          dessin.ajoutElement((Rectangle) dao.find(resRectangle.getString("nom")));
        }

        while (resTriangle.next()) {
          dao = new TriangleDao();
          dessin.ajoutElement((Triangle) dao.find(resTriangle.getString("nom")));
        }

        while (resCercle.next()) {
          dao = new CercleDao();
          dessin.ajoutElement((Cercle) dao.find(resCercle.getString("nom")));
        }

        while (resGroupe.next()) {
          dao = new GroupeDao();
          dessin.ajoutElement((Groupe) dao.find(resGroupe.getString("nom")));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    this.disconnect();

    return dessin;
  }

  @Override
  public void delete(String id) {
    this.connect();
    try (PreparedStatement delete =
        this.connect.prepareStatement("DELETE FROM Dessin D WHERE D.dnom = ?"); ) {
      delete.setString(1, id);
      delete.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    this.disconnect();
  }

  @Override
  public void close() throws Exception {
    super.connect.close();
  }
}
