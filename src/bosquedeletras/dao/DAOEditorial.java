package bosquedeletras.dao;

import bosquedeletras.model.Editorial;
import bosquedeletras.utils.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOEditorial {

    public boolean insertar(Editorial e) {
        String sql = "INSERT INTO editorial (id, nombre, activo) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getId());
            ps.setString(2, e.getNombre());
            ps.setInt(3, e.isActivo() ? 1 : 0);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Editorial buscarPorId(String id) {
        String sql = "SELECT * FROM editorial WHERE id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Editorial(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getInt("activo") == 1
                    );
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Editorial buscarPorIdActivo(String id) {
        String sql = "SELECT * FROM editorial WHERE id = ? AND activo = 1";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Editorial(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getInt("activo") == 1
                    );
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean tieneLibrosAsociados(String id) {
        String sql = "SELECT COUNT(*) FROM libro WHERE editorial_id = ? AND activo = 1";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean bajaLogica(String id) {
        String sql = "UPDATE editorial SET activo = 0 WHERE id = ? AND activo = 1";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean reactivar(String id) {
        String sql = "UPDATE editorial SET activo = 1 WHERE id = ? AND activo = 0";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Actualizar editorial
    public boolean actualizar(Editorial editorial) {
        String sql = "UPDATE editorial SET nombre = ? WHERE id = ? AND activo = 1";

        try (Connection conn = ConexionBD.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, editorial.getNombre());
            ps.setString(2, editorial.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Editorial> listar() {
        List<Editorial> editoriales = new ArrayList<>();
        String sql = "SELECT * FROM editorial WHERE activo = 1";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                editoriales.add(new Editorial(
                    rs.getString("id"),
                    rs.getString("nombre"),
                    rs.getInt("activo") == 1
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return editoriales;
    }
}