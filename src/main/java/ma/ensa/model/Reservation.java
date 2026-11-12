
package ma.ensa.model;

import ma.ensa.enums.StatutReservation;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class Reservation {

    private Long id;
    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String motif;
    private StatutReservation statut;
    private String motifRefus;
    private LocalDateTime dateCreation;

    private Utilisateur utilisateur;
    private Utilisateur administrateur;
    private Salle salle;

    // Constructeur vide
    public Reservation() {
    }

    // Constructeur avec paramètres
    public Reservation(Long id, LocalDate date, LocalTime heureDebut,
                       LocalTime heureFin, String motif,
                       StatutReservation statut, String motifRefus,
                       LocalDateTime dateCreation, Utilisateur utilisateur,
                       Utilisateur administrateur, Salle salle) {

        this.id = id;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.motif = motif;
        this.statut = statut;
        this.motifRefus = motifRefus;
        this.dateCreation = dateCreation;
        this.utilisateur = utilisateur;
        this.administrateur = administrateur;
        this.salle = salle;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public StatutReservation getStatut() {
        return statut;
    }

    public void setStatut(StatutReservation statut) {
        this.statut = statut;
    }

    public String getMotifRefus() {
        return motifRefus;
    }

    public void setMotifRefus(String motifRefus) {
        this.motifRefus = motifRefus;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Utilisateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Utilisateur administrateur) {
        this.administrateur = administrateur;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }
}
