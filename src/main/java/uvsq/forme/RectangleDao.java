package uvsq.forme;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RectangleDao extends Dao<Rectangle> {

  @Override
  public Rectangle create(Rectangle obj) {
    this.connect();
    try (PreparedStatement insert =
        this.connect.prepareStatement(
            "INSERT INTO Rectangle(nom, x, y, longueur, hauteur) values(?, ?, ?, ?, ?)"); ) {
      System.out.println("Dans RectangleDao: " + Dao.nom + ":" + obj.nom);
      insert.setString(1, Dao.nom + ":" + obj.nom);
      insert.setInt(2, obj.bg.abscisse);
      insert.setInt(3, obj.bg.ordonne);
      insert.setInt(4, obj.longueur);
      insert.setInt(5, obj.hauteur);
      insert.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    this.disconnect();
    return null;
  }

  @Override
  public Rectangle find(String id) {
    Rectangle r = null;
    this.connect();
    try (PreparedStatement select =
        this.connect.prepareStatement("SELECT * FROM Rectangle R WHERE R.nom = ?")) {
      select.setString(1, id);
      try (ResultSet res = select.executeQuery()) {
        if (res.next()) {
          r =
              new Rectangle(
                  res.getString("nom").split(":")[1],
                  new Point(
                      Integer.parseInt(res.getString("x")), Integer.parseInt(res.getString("y"))),
                  Integer.parseInt(res.getString("longueur")),
                  Integer.parseInt(res.getString("hauteur")));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    this.disconnect();

    return r;
  }

  @Override
  public void delete(String id) {
    this.connect();
    try (PreparedStatement delete =
        this.connect.prepareStatement("DELETE FROM Rectangle R WHERE R.nom = ?"); ) {
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
