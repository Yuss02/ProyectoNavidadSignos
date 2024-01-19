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
package traductorconsultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import proyectonavidadsignos.ConexionDB;

/**
 * Clase que representa una consulta realizada por un usuario en la aplicación
 * de traducción de signos. Esta clase gestiona la información de la consulta,
 * incluyendo el texto, la fecha y el identificador del usuario.
 */
public class Consultas {

    private String consultaTexto;
    private Date fechaConsulta;
    private int idUsuario;

    /**
     * Constructor de la clase Consultas.
     *
     * @param consultaTexto El texto de la consulta realizada por el usuario.
     * @param idUsuario El identificador del usuario que realiza la consulta.
     */
    public Consultas(String consultaTexto, int idUsuario) {
        this.consultaTexto = consultaTexto;
        this.fechaConsulta = new Date(); // Fecha actual
        this.idUsuario = idUsuario;
    }

    // Métodos getters y setters
    public void setConsultaTexto(String consultaTexto) {
        this.consultaTexto = consultaTexto;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getConsultaTexto() {
        return consultaTexto;
    }

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Guarda la consulta en la base de datos. Este método inserta la consulta
     * del usuario junto con la fecha y el ID del usuario en la base de datos.
     */
    public void guardarConsulta() {
        Connection conexion = null;
        try {
            conexion = ConexionDB.conectar();
            String sql = "INSERT INTO consultas (Consulta, FechaConsulta, ID) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, consultaTexto);
            pstmt.setTimestamp(2, new java.sql.Timestamp(fechaConsulta.getTime()));
            pstmt.setInt(3, idUsuario);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo del error
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
