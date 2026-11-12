
package ma.ensa.model;

public class Batiment {

    private Long id;
    private String nom;

    // Constructeur vide
    public Batiment() {
    }

    // Constructeur avec paramètres
    public Batiment(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
