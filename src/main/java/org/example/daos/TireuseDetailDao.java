package org.example.daos;

import org.example.core.Database;
import org.example.models.Tireuse;
import org.example.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TireuseDetailDao {

    /**
     * On reçoit l'id récuperé précedemment
     * 1. On execute une requête SQL dans la BDD pour chercher les informations de la biere choisie
     * 2. On revoi le résultat dans une variable "Tireuse" qui se nomme biere
     */

    public Tireuse detailTireuse(int id) {
        Tireuse biere = null;
        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM tireuses WHERE id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                biere = mapToDetail(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return biere;
    }

    public static Tireuse mapToDetail(ResultSet rs) throws SQLException {
        int i = 1;
        return new Tireuse(
                rs.getInt(i++), //id
                rs.getString(i++), //nom
                rs.getString(i++), //origine
                rs.getFloat(i++), // prix_litre
                rs.getDouble(i++), // latitude
                rs.getDouble(i++), // longitude
                rs.getString(i++) // description);
        );
    }

}
