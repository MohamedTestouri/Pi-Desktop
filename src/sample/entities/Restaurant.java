package sample.entities;

public class Restaurant {
    private int id;
    private String nom;
    private String specialite;
    private int places;

    public Restaurant() {
    }

    public Restaurant(String nom, String specialite, int places) {
        this.nom = nom;
        this.specialite = specialite;
        this.places = places;
    }

    public Restaurant(int id, String nom, String specialite, int places) {
        this.id = id;
        this.nom = nom;
        this.specialite = specialite;
        this.places = places;
    }

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

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }
}
