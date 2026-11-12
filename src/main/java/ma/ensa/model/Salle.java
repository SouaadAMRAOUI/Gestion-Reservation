
package ma.ensa.model;

import ma.ensa.enums.TypeSalle;

import java.util.List;

public class Salle {

    private Long id;
    private String nom;
    private int capacite;
    private TypeSalle type;
    private Batiment batiment;
    private List<Equipement> equipements;

    // Constructeur vide
    public Salle() {
    }

    // Constructeur avec paramètres
    public Salle(Long id, String nom, int capacite, TypeSalle type) {
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
        this.type = type;
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

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public TypeSalle getType() {
        return type;
    }

    public void setType(TypeSalle type) {
        this.type = type;
    }
    public  Batiment getBatiment(){
        return this.batiment;
    }
    public void setBatiment(Batiment b){
        this.batiment=b;
    }
}
