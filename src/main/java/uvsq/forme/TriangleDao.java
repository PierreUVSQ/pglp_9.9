package uvsq.forme;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TriangleDao extends Dao<Triangle> {

  @Override
  public Triangle create(Triangle obj) {
    this.connect();
    try (PreparedStatement insert =
        this.connect.prepareStatement(
            "INSERT INTO Triangle(nom, ax, ay, bx, bay, cx, cy) values(?, ?, ?, ?, ?, ?, ?)"); ) {
      insert.setString(1, Dao.nom + ":" + obj.nom);
      insert.setInt(2, obj.pointa.abscisse);
      insert.setInt(3, obj.pointa.ordonne);
      insert.setInt(4, obj.pointb.abscisse);
      insert.setInt(5, obj.pointb.ordonne);
      insert.setInt(6, obj.pointc.abscisse);
      insert.setInt(7, obj.pointc.ordonne);
      insert.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    this.disconnect();
    return null;
  }

  @Override
  public Triangle find(String id) {
    Triangle t = null;
    this.connect();
    try (PreparedStatement select =
        this.connect.prepareStatement("SELECT * FROM Triangle T WHERE T.nom = ?")) {
      select.setString(1, id);
      try (ResultSet res = select.executeQuery()) {
        if (res.next()) {
          t =
              new Triangle(
                  res.getString("nom").split(":")[1],
                  new Point(res.getInt("ax"), res.getInt("ay")),
                  new Point(res.getInt("bx"), res.getInt("bay")),
                  new Point(res.getInt("cx"), res.getInt("cy")));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    this.disconnect();

    return t;
  }

  @Override
  public void delete(String id) {
    this.connect();
    try (PreparedStatement delete =
        this.connect.prepareStatement("DELETE FROM Triangle T WHERE T.nom = ?"); ) {
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
