package org.example.models;

/**
 * Cette classe doit être le reflet de la table correspondante dans ta BDD.
 * Chaque colonne = un attribut de la classe avec le bon type
 *
 * L'objectif c'est que partout dans ton code Java, tu manipules des instances
 * de User, et c'est au dernier moment, quand tu dois discuter avec la BDD,
 * que tu vas convertir ce User en requête SQL.
 */
public class User {
    public int id;
    public String username;
    public String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
