package org.example.daos;

import org.example.models.User;

import java.sql.*;

import org.example.core.Database;

public class UserDao {

    /**------------------------------------------------------------------------------------------
     * Cette fonction va récupérer l'utilisateur qui correspond aux identifiants passés en paramètres.
     * Elle fait 2 opérations principales :
     * 1. Exécuter une requête SQL a partir des paramètres
     * 2. Convertir le résultat (s'il existe) en une instance de User
     */
    public User getUserByCredentials(String username, String password) {
        User user = null;

        // Il faut toujours récupérer la connection vers la BDD qui est partagée dans toute l'application
        Connection connection = Database.get().getConnection();
        try {
            // D'abord, il faut préparer la requête pour éviter les injections SQL
            // A ce stade, la requête n'est pas exécutée, car il faut remplacer les ?
            // par les vraies valeurs qui doivent être utilisée
            PreparedStatement st = connection.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");

            // On associe chaque ? à une vraie valeur.
            // ATTENTION, les index commencent à 1 !
            st.setString(1, username);
            st.setString(2, password);

            // La requête préparée est maintenant effectivement exécutée
            // et on récupère un résultat
            ResultSet rs = st.executeQuery();

            // Ce résultat est un pointeur qui va permettre de récupérer 1 ou plusieurs
            // lignes qui ont été renvoyées.
            // si rs.next() renvoie vrai, alors il y a une ligne suivante qui nous attend
            if (rs.next()) {
                // Pour éviter de manipuler dans le code un ResultSet avec des index,
                // on va convertir ce résultat en une instance de User
                // cette fonction map est à faire pour chaque table / class
                user = mapToUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // L'utilisateur n'est initialisé que si on a réussi a lire une ligne dans le resultset
        // cela est conditionné par le if (rs.next()) { ... }
        //
        // Si on est pas entré dans cette branche, user == null, on renvoie donc un null
        // Autrement dit, l'utilisateur n'a pas été trouvé
        return user;
    }

    public static User mapToUser(ResultSet rs) throws SQLException {
        int i = 1;
        // L'index ici correspond aux colonnes renvoyées par la requête
        // puisqu'on a fait un SELECT *, on récupère toutes les colonnes
        return new User(
                rs.getInt(i++), // id
                rs.getString(i++), // username,
                rs.getString(i++) // password
        );
    }









    /**
     * Crée un utilisateur en BDD et renvoie l'ID de ce nouvel utilisateur
     * @param username
     * @param password
     * @param mail
     * @param name
     * @param surname
     */
    public int createUser(String username, String password, String mail, String name, String surname) {
        Connection connection = Database.get().getConnection();
        int newId = 0;
        try {
            // On prépare la requête
            // puisque l'ID est auto généré, on met NULL pour cette colonne,
            // MySQL se chargera de mettre la bonne valeur
            PreparedStatement st = connection.prepareStatement("INSERT INTO users VALUES (Null, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, mail);
            st.setString(4, name);
            st.setString(5, surname);

            st.executeUpdate();

            // On essaye de récupérer l'id de la ligne qui vient d'être créée en base
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                newId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            }
        return newId;
    }
}
