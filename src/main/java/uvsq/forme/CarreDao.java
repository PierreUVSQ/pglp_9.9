package uvsq.forme;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarreDao extends Dao<Carre> {

  @Override
  public Carre create(Carre obj) {
    this.connect();
    try (PreparedStatement carreInsert =
        this.connect.prepareStatement("INSERT INTO Carre(nom, x, y, cote) values(?, ?, ?, ?)"); ) {
      carreInsert.setString(1, Dao.nom + ":" + obj.nom);
      carreInsert.setInt(2, obj.point.abscisse);
      carreInsert.setInt(3, obj.point.ordonne);
      carreInsert.setInt(4, obj.cote);
      carreInsert.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    this.disconnect();
    return null;
  }

  @Override
  public Carre find(String id) {
    Carre c = null;
    this.connect();
    try (PreparedStatement select =
        this.connect.prepareStatement("SELECT * FROM Carre C WHERE C.nom = ?")) {
      select.setString(1, id);
      try (ResultSet res = select.executeQuery()) {
        if (res.next()) {
          c =
              new Carre(
                  res.getString("nom").split(":")[1],
                  new Point(
                      Integer.parseInt(res.getString("x")), Integer.parseInt(res.getString("y"))),
                  Integer.parseInt(res.getString("cote")));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    this.disconnect();

    return c;
  }

  @Override
  public void delete(String id) {
    this.connect();
    try (PreparedStatement delete =
        this.connect.prepareStatement("DELETE FROM Carre C WHERE C.nom = ?"); ) {
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
