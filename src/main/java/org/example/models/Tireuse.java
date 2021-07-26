package org.example.models;
/**
 * Cette classe doit être le reflet de la table correspondante dans ta BDD.
 * Chaque colonne = un attribut de la classe avec le bon type
 *
 * L'objectif c'est que partout dans ton code Java, tu manipules des instances
 * de Tireuse, et c'est au dernier moment, quand tu dois discuter avec la BDD,
 * que tu vas convertir ce Tireuse en requête SQL.
 */
public class Tireuse {

    public int id;
    public String nom;
    public String origine;
    public float prix_litre;
    public double latitude;
    public double longitude;
    public String description;

    public Tireuse(int id, String nom, String origine, float prix_litre, double latitude, double longitude, String description) {
        this.id = id;
        this.nom = nom;
        this.origine = origine;
        this.prix_litre = prix_litre;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }
}
