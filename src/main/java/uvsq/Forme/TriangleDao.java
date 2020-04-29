package uvsq.Forme;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TriangleDao extends Dao<Triangle>{

    @Override
    public Triangle create(Triangle obj) {
        this.connect();
        try (PreparedStatement insert =
                     this.connect.prepareStatement("INSERT INTO Triangle(nom, ax, ay, bx, by, cx, cy) values(?, ?, ?, ?, ?, ?, ?)"); ) {
            insert.setString(1, obj.nom);
            insert.setInt(2, obj.a.x);
            insert.setInt(3, obj.a.y);
            insert.setInt(4, obj.b.x);
            insert.setInt(5, obj.b.y);
            insert.setInt(6, obj.c.x);
            insert.setInt(7, obj.c.y);
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
                t =
                        new Triangle(res.getString("nom"), new Point(res.getInt("ax"), res.getInt("ay")), new Point(res.getInt("bx"), res.getInt("by")), new Point(res.getInt("cx"), res.getInt("cy")));
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
    }

    @Override
    public void close() throws Exception {
        super.connect.close();
    }

}
