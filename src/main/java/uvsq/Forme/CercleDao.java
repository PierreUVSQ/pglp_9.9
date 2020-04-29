package uvsq.Forme;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CercleDao extends Dao<Cercle> {
    @Override
    public Cercle create(Cercle obj) {
        this.connect();
        try (PreparedStatement insert =
                     this.connect.prepareStatement("INSERT INTO Cercle(nom, x, y, rayon) values(?, ?, ?, ?)"); ) {
            insert.setString(1, obj.nom);
            insert.setInt(2, obj.centre.x);
            insert.setInt(3, obj.centre.y);
            insert.setInt(4, obj.rayon);
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.disconnect();
        return null;
    }

    @Override
    public Cercle find(String id) {
        Cercle c = null;
        this.connect();
        try (PreparedStatement select =
                     this.connect.prepareStatement("SELECT * FROM Cercle C WHERE C.nom = ?")) {
            select.setString(1, id);
            try (ResultSet res = select.executeQuery()) {
                c =
                        new Cercle(
                                res.getString("nom"),
                                new Point(
                                        Integer.parseInt(res.getString("x")), Integer.parseInt(res.getString("y"))),
                                Integer.parseInt(res.getString("rayon")));
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
                     this.connect.prepareStatement("DELETE FROM Cercle C WHERE C.nom = ?"); ) {
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
