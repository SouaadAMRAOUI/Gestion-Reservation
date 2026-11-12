
package ma.ensa.model;

import java.time.LocalDateTime;

public class Indisponibilite {

    private Long id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String motif;

    private Salle salle;

    // Constructeur vide
    public Indisponibilite() {
    }

    // Constructeur avec paramètres
    public Indisponibilite(Long id, LocalDateTime dateDebut,
                           LocalDateTime dateFin, String motif,
                           Salle salle) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.motif = motif;
        this.salle = salle;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }
}
