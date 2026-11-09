package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/lessence_java";

    private static final String USUARIO =
            System.getenv("LESSENCE_DB_USER");

    private static final String CLAVE =
            System.getenv("LESSENCE_DB_PASSWORD");

    public static Connection conectar() throws SQLException {

        if (USUARIO == null || CLAVE == null) {
            throw new SQLException(
                "No se han configurado las credenciales de MySQL."
            );
        }

        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}