package uvsq.forme;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GroupeDao extends Dao<Groupe> {

  @Override
  public Groupe create(Groupe obj) {
    String prefix = Dao.nom + ":";
    this.connect();
    try (PreparedStatement groupeInsert =
            this.connect.prepareStatement("INSERT INTO Groupe(gnom) values(?)");
        PreparedStatement carreInsert =
            this.connect.prepareStatement("INSERT INTO CarreGroupe(gnom, nom) VALUES(?, ?)");
        PreparedStatement cercleInsert =
            this.connect.prepareStatement("INSERT INTO CercleGroupe(gnom, nom) VALUES(?, ?)");
        PreparedStatement triangleInsert =
            this.connect.prepareStatement("INSERT INTO TriangleGroupe(gnom, nom) VALUES(?, ?)");
        PreparedStatement groupeGroupeInsert =
            this.connect.prepareStatement("INSERT INTO GroupeGroupe(gnom, nom) VALUES(?, ?)");
        PreparedStatement rectangleInsert =
            this.connect.prepareStatement("INSERT INTO RectangleGroupe(gnom, nom) VALUES(?, ?)")) {
      groupeInsert.setString(1, prefix + obj.nom);
      groupeInsert.executeUpdate();
      List<Element> listElem = obj.getListeNonModifiable();
      Dao tmp;
      for (Element elem : listElem) {

        if (elem instanceof Groupe) {
          tmp = new GroupeDao();
          tmp.create((Groupe) elem);
          groupeGroupeInsert.setString(1, prefix + obj.nom);
          groupeGroupeInsert.setString(2, prefix + elem.nom);
          groupeGroupeInsert.executeUpdate();
        } else if (elem instanceof Cercle) {
          tmp = new CercleDao();
          tmp.create((Cercle) elem);
          cercleInsert.setString(1, prefix + obj.nom);
          cercleInsert.setString(2, prefix + elem.nom);
          cercleInsert.executeUpdate();
        } else if (elem instanceof Rectangle) {
          tmp = new RectangleDao();
          tmp.create((Rectangle) elem);
          rectangleInsert.setString(1, prefix + obj.nom);
          rectangleInsert.setString(2, prefix + elem.nom);
          rectangleInsert.executeUpdate();
        } else if (elem instanceof Carre) {
          tmp = new CarreDao();
          tmp.create((Carre) elem);
          carreInsert.setString(1, prefix + obj.nom);
          carreInsert.setString(2, prefix + elem.nom);
          carreInsert.executeUpdate();
        } else if (elem instanceof Triangle) {
          tmp = new TriangleDao();
          tmp.create((Triangle) elem);
          triangleInsert.setString(1, prefix + obj.nom);
          triangleInsert.setString(2, prefix + elem.nom);
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
  public Groupe find(String id) {
    Groupe g = null;
    Dao dao;
    this.connect();
    try (PreparedStatement select =
            this.connect.prepareStatement("SELECT * FROM Groupe G WHERE G.gnom = ?");
        PreparedStatement selectRectangle =
            this.connect.prepareStatement("SELECT * FROM RectangleGroupe RG WHERE RG.gnom = ?");
        PreparedStatement selectTriangle =
            this.connect.prepareStatement("SELECT * FROM TriangleGroupe TG WHERE TG.gnom = ?");
        PreparedStatement selectCercle =
            this.connect.prepareStatement("SELECT * FROM CercleGroupe CG WHERE CG.gnom = ?");
        PreparedStatement selectCarre =
            this.connect.prepareStatement("SELECT * FROM CarreGroupe CG WHERE CG.gnom = ?");
        PreparedStatement selectGroupe =
            this.connect.prepareStatement("SELECT * FROM GroupeGroupe GG WHERE GG.gnom = ?"); ) {
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
          String n = res.getString("gnom").split(":")[1];
          g = new Groupe(n);
        }

        while (resCarre.next()) {
          dao = new CarreDao();
          g.ajoutForme((Carre) dao.find(resCarre.getString("nom")));
        }

        while (resRectangle.next()) {
          dao = new RectangleDao();
          g.ajoutForme((Rectangle) dao.find(resRectangle.getString("nom")));
        }

        while (resTriangle.next()) {
          dao = new TriangleDao();
          g.ajoutForme((Triangle) dao.find(resTriangle.getString("nom")));
        }

        while (resCercle.next()) {
          dao = new CercleDao();
          g.ajoutForme((Cercle) dao.find(resCercle.getString("nom")));
        }

        while (resGroupe.next()) {
          dao = new GroupeDao();
          g.ajoutForme((Groupe) dao.find(resGroupe.getString("nom")));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    this.disconnect();

    return g;
  }

  @Override
  public void delete(String id) {
    this.connect();
    try (PreparedStatement delete =
        this.connect.prepareStatement("DELETE FROM Groupe G WHERE G.gnom = ?"); ) {
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
