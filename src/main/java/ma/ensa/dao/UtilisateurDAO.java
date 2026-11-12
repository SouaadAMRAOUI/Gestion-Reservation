
package ma.ensa.dao;

import ma.ensa.model.Utilisateur;
import ma.ensa.enums.Role;
import ma.ensa.conx.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {

    // CREATE : Ajouter un utilisateur
    public void ajouter(Utilisateur utilisateur) throws SQLException {

        String sql = "INSERT INTO utilisateurs (nom, prenom, email, mot_de_passe, role) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, utilisateur.getNom());
            ps.setString(2, utilisateur.getPrenom());
            ps.setString(3, utilisateur.getEmail());
            ps.setString(4, utilisateur.getMotDePasse());
            ps.setString(5, utilisateur.getRole().name());

            ps.executeUpdate();
        }
    }

    // READ : Afficher tous les utilisateurs
    public List<Utilisateur> trouverTous() throws SQLException {

        List<Utilisateur> utilisateurs = new ArrayList<>();

        String sql = "SELECT * FROM utilisateurs";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Utilisateur u = new Utilisateur();

                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setMotDePasse(rs.getString("mot_de_passe"));
                u.setRole(Role.valueOf(rs.getString("role")));

                utilisateurs.add(u);
            }
        }

        return utilisateurs;
    }

    // READ : Rechercher un utilisateur par ID
    public Utilisateur trouverParId(int id) throws SQLException {

        String sql = "SELECT * FROM utilisateurs WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Utilisateur u = new Utilisateur();

                    u.setId(rs.getInt("id"));
                    u.setNom(rs.getString("nom"));
                    u.setPrenom(rs.getString("prenom"));
                    u.setEmail(rs.getString("email"));
                    u.setMotDePasse(rs.getString("mot_de_passe"));
                    u.setRole(Role.valueOf(rs.getString("role")));

                    return u;
                }
            }
        }

        return null;
    }

    // UPDATE : Modifier un utilisateur
    public void modifier(Utilisateur utilisateur) throws SQLException {

        String sql = "UPDATE utilisateurs SET nom = ?, prenom = ?, email = ?, mot_de_passe = ?, role = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, utilisateur.getNom());
            ps.setString(2, utilisateur.getPrenom());
            ps.setString(3, utilisateur.getEmail());
            ps.setString(4, utilisateur.getMotDePasse());
            ps.setString(5, utilisateur.getRole().name());
            ps.setInt(6, utilisateur.getId());

            ps.executeUpdate();
        }
    }

    // DELETE : Supprimer un utilisateur
    public void supprimer(int id) throws SQLException {

        String sql = "DELETE FROM utilisateurs WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
