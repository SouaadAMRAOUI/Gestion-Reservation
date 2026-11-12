
package ma.ensa.dao;

import ma.ensa.model.Salle;
import ma.ensa.model.Batiment;
import ma.ensa.enums.TypeSalle;
import ma.ensa.conx.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalleDAO {

    // CREATE : Ajouter une salle
    public void ajouter(Salle salle) throws SQLException {

        String sql = "INSERT INTO salles (nom, capacite, type, batiment_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, salle.getNom());
            ps.setInt(2, salle.getCapacite());
            ps.setString(3, salle.getType().name());
            ps.setLong(4, salle.getBatiment().getId());

            ps.executeUpdate();
        }
    }

    // READ : Afficher toutes les salles
    public List<Salle> trouverTous() throws SQLException {

        List<Salle> salles = new ArrayList<>();

        String sql = "SELECT s.*, b.nom AS nom_batiment " +
                "FROM salles s JOIN batiments b ON s.batiment_id = b.id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Salle s = new Salle();
                s.setId(rs.getLong("id"));
                s.setNom(rs.getString("nom"));
                s.setCapacite(rs.getInt("capacite"));
                s.setType(TypeSalle.valueOf(rs.getString("type")));

                Batiment b = new Batiment();
                b.setId(rs.getLong("batiment_id"));
                b.setNom(rs.getString("nom_batiment"));

                s.setBatiment(b);

                salles.add(s);
            }
        }

        return salles;
    }

    // READ : Rechercher une salle par ID
    public Salle trouverParId(Long id) throws SQLException {

        String sql = "SELECT s.*, b.nom AS nom_batiment " +
                "FROM salles s JOIN batiments b ON s.batiment_id = b.id " +
                "WHERE s.id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Salle s = new Salle();
                    s.setId(rs.getLong("id"));
                    s.setNom(rs.getString("nom"));
                    s.setCapacite(rs.getInt("capacite"));
                    s.setType(TypeSalle.valueOf(rs.getString("type")));

                    Batiment b = new Batiment();
                    b.setId(rs.getLong("batiment_id"));
                    b.setNom(rs.getString("nom_batiment"));

                    s.setBatiment(b);

                    return s;
                }
            }
        }

        return null;
    }

    // UPDATE : Modifier une salle
    public void modifier(Salle salle) throws SQLException {

        String sql = "UPDATE salles SET nom = ?, capacite = ?, type = ?, batiment_id = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, salle.getNom());
            ps.setInt(2, salle.getCapacite());
            ps.setString(3, salle.getType().name());
            ps.setLong(4, salle.getBatiment().getId());
            ps.setLong(5, salle.getId());

            ps.executeUpdate();
        }
    }

    // DELETE : Supprimer une salle
    public void supprimer(Long id) throws SQLException {

        String sql = "DELETE FROM salles WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
}
