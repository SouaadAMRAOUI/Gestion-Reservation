
package ma.ensa.dao;

import ma.ensa.model.Reservation;
import ma.ensa.model.Utilisateur;
import ma.ensa.model.Salle;
import ma.ensa.enums.Role;
import ma.ensa.enums.StatutReservation;
import ma.ensa.conx.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    // CREATE : Ajouter une réservation
    public void ajouter(Reservation reservation) throws SQLException {

        String sql = "INSERT INTO reservations " +
                "(date, heure_debut, heure_fin, motif, statut, " +
                "motif_refus, utilisateur_id, administrateur_id, salle_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(reservation.getDate()));
            ps.setTime(2, Time.valueOf(reservation.getHeureDebut()));
            ps.setTime(3, Time.valueOf(reservation.getHeureFin()));
            ps.setString(4, reservation.getMotif());
            ps.setString(5, reservation.getStatut().name());
            ps.setString(6, reservation.getMotifRefus());
            ps.setLong(7, reservation.getUtilisateur().getId());

            if (reservation.getAdministrateur() != null) {
                ps.setLong(8, reservation.getAdministrateur().getId());
            } else {
                ps.setNull(8, Types.BIGINT);
            }

            ps.setLong(9, reservation.getSalle().getId());

            ps.executeUpdate();
        }
    }

    // READ : Afficher toutes les réservations
    public List<Reservation> trouverTous() throws SQLException {

        List<Reservation> reservations = new ArrayList<>();

        String sql = "SELECT * FROM reservations";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                reservations.add(mapperReservation(rs));
            }
        }

        return reservations;
    }

    // READ : Rechercher une réservation par ID
    public Reservation trouverParId(Long id) throws SQLException {

        String sql = "SELECT * FROM reservations WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapperReservation(rs);
                }
            }
        }

        return null;
    }

    // UPDATE : Modifier une réservation
    public void modifier(Reservation reservation) throws SQLException {

        String sql = "UPDATE reservations SET date = ?, heure_debut = ?, " +
                "heure_fin = ?, motif = ?, statut = ?, motif_refus = ?, " +
                "utilisateur_id = ?, administrateur_id = ?, salle_id = ? " +
                "WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(reservation.getDate()));
            ps.setTime(2, Time.valueOf(reservation.getHeureDebut()));
            ps.setTime(3, Time.valueOf(reservation.getHeureFin()));
            ps.setString(4, reservation.getMotif());
            ps.setString(5, reservation.getStatut().name());
            ps.setString(6, reservation.getMotifRefus());
            ps.setLong(7, reservation.getUtilisateur().getId());

            if (reservation.getAdministrateur() != null) {
                ps.setLong(8, reservation.getAdministrateur().getId());
            } else {
                ps.setNull(8, Types.BIGINT);
            }

            ps.setLong(9, reservation.getSalle().getId());
            ps.setLong(10, reservation.getId());

            ps.executeUpdate();
        }
    }

    // DELETE : Supprimer une réservation
    public void supprimer(Long id) throws SQLException {

        String sql = "DELETE FROM reservations WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    // Convertir une ligne SQL en objet Reservation
    private Reservation mapperReservation(ResultSet rs) throws SQLException {

        Reservation r = new Reservation();

        r.setId(rs.getLong("id"));
        r.setDate(rs.getDate("date").toLocalDate());
        r.setHeureDebut(rs.getTime("heure_debut").toLocalTime());
        r.setHeureFin(rs.getTime("heure_fin").toLocalTime());
        r.setMotif(rs.getString("motif"));
        r.setStatut(StatutReservation.valueOf(rs.getString("statut")));
        r.setMotifRefus(rs.getString("motif_refus"));

        Timestamp timestamp = rs.getTimestamp("date_creation");
        if (timestamp != null) {
            r.setDateCreation(timestamp.toLocalDateTime());
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(rs.getInt("utilisateur_id"));
        r.setUtilisateur(utilisateur);

        long adminId = rs.getLong("administrateur_id");
        if (!rs.wasNull()) {
            Utilisateur admin = new Utilisateur();
            admin.setId((int) adminId);
            r.setAdministrateur(admin);
        }

        Salle salle = new Salle();
        salle.setId(rs.getLong("salle_id"));
        r.setSalle(salle);

        return r;
    }
}
