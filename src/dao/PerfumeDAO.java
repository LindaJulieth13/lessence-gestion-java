package dao;

import conexion.ConexionBD;
import modelo.Perfume;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerfumeDAO {

    // REGISTRAR PERFUME
    public boolean insertar(Perfume perfume) throws SQLException {

        String sql = "INSERT INTO perfumes (nombre, marca, categoria, precio, stock) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setString(1, perfume.getNombre());
            consulta.setString(2, perfume.getMarca());
            consulta.setString(3, perfume.getCategoria());
            consulta.setDouble(4, perfume.getPrecio());
            consulta.setInt(5, perfume.getStock());

            return consulta.executeUpdate() > 0;
        }
    }

    // CONSULTAR TODOS LOS PERFUMES
    public List<Perfume> listar() throws SQLException {

        List<Perfume> perfumes = new ArrayList<>();
        String sql = "SELECT * FROM perfumes";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql);
             ResultSet resultado = consulta.executeQuery()) {

            while (resultado.next()) {

                Perfume perfume = new Perfume(
                    resultado.getString("nombre"),
                    resultado.getString("marca"),
                    resultado.getString("categoria"),
                    resultado.getDouble("precio"),
                    resultado.getInt("stock")
                );

                perfume.setId(resultado.getInt("id"));
                perfumes.add(perfume);
            }
        }

        return perfumes;
    }

    // ACTUALIZAR PERFUME
    public boolean actualizar(int id, String nombre, String marca,
                              String categoria, double precio, int stock)
                              throws SQLException {

        String sql = "UPDATE perfumes SET nombre=?, marca=?, categoria=?, "
                   + "precio=?, stock=? WHERE id=?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setString(1, nombre);
            consulta.setString(2, marca);
            consulta.setString(3, categoria);
            consulta.setDouble(4, precio);
            consulta.setInt(5, stock);
            consulta.setInt(6, id);

            return consulta.executeUpdate() > 0;
        }
    }

    // ELIMINAR PERFUME
    public boolean eliminar(int id) throws SQLException {

        String sql = "DELETE FROM perfumes WHERE id=?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setInt(1, id);

            return consulta.executeUpdate() > 0;
        }
    }
}