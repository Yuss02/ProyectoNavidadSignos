/**
 * Traductor de Lengua de Signos
 *
 * Clase: Programación
 * Descripción:Proyecto Navidad
 *
 * Autor: ARBEYO
 * Fecha de Creación: Enero 2023
 * Versión: 1.0.0
 */
package proyectonavidadsignos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase ConexionDB que gestiona la conexión con la base de datos para la
 * aplicación Traductor de Signos. Esta clase proporciona métodos estáticos para
 * establecer y cerrar conexiones con la base de datos.
 */
public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/tsignos";
    private static final String USER = "signos";
    private static final String PASSWORD = "signos123";

    /**
     * Establece una conexión con la base de datos.
     *
     * @return La conexión establecida con la base de datos.
     * @throws RuntimeException Si ocurre un error al intentar conectar con la
     * base de datos.
     */
    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }

    /**
     * Cierra la conexión proporcionada con la base de datos.
     *
     * @param conexion La conexión a cerrar.
     * @throws RuntimeException Si ocurre un error al intentar cerrar la
     * conexión.
     */
    public static void cerrar(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException("Error al cerrar la conexión a la base de datos", e);
            }
        }
    }
}
