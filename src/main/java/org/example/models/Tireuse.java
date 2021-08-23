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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public float getPrix_litre() {
        return prix_litre;
    }

    public void setPrix_litre(float prix_litre) {
        this.prix_litre = prix_litre;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
