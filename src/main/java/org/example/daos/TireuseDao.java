package org.example.daos;

import org.example.core.Database;
import org.example.models.Tireuse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TireuseDao {

    public List<Tireuse> getTireuses(){
        List<Tireuse> tir = new ArrayList<>();
        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM tireuses");
            ResultSet rs = st.executeQuery();

            while (rs.next() != false) {
                Tireuse t = mapToTireuse(rs);
                tir.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tir;
    }

    public static Tireuse mapToTireuse(ResultSet rs) throws SQLException {
        int i = 1;

        return new Tireuse(
                rs.getInt(i++), //id
                rs.getString(i++), //nom
                rs.getString(i++), //origine
                rs.getFloat(i++), // prix_litre
                rs.getDouble(i++), // latitude
                rs.getDouble(i++), // longitude
                rs.getString(i++) // description
        );
    }
}
