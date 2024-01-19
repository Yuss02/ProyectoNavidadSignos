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
package traductorvistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import proyectonavidadsignos.ConexionDB;

/**
 * Clase ConsultarConsultas que proporciona una interfaz gráfica para buscar y
 * mostrar las consultas realizadas por un usuario específico. Permite al
 * usuario ingresar un correo electrónico y buscar todas las consultas asociadas
 * a ese correo en la base de datos.
 */
public class ConsultarConsultas extends JPanel {

    private JTextField txtCorreo;
    private JButton btnBuscar;
    private JTextArea areaResultados;

    /**
     * Constructor que inicializa la interfaz de usuario para consultar las
     * consultas. Configura los campos de texto, el botón de búsqueda y el área
     * donde se muestran los resultados.
     */
    public ConsultarConsultas() {
        setLayout(new BorderLayout());

        // Panel para la entrada de correo
        JPanel panelEntrada = new JPanel();
        panelEntrada.add(new JLabel("Correo del usuario:"));
        txtCorreo = new JTextField(20);
        panelEntrada.add(txtCorreo);

        // Botón para buscar las consultas
        btnBuscar = new JButton("Buscar Consultas");
        panelEntrada.add(btnBuscar);
        add(panelEntrada, BorderLayout.NORTH);

        // Área de texto para mostrar los resultados
        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        add(new JScrollPane(areaResultados), BorderLayout.CENTER);

        // Evento del botón buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarConsultas();
            }
        });
    }

    /**
     * Método que se ejecuta cuando se presiona el botón Buscar. Realiza una
     * búsqueda en la base de datos de todas las consultas asociadas al correo
     * electrónico proporcionado y muestra los resultados en el área de texto.
     */
    private void buscarConsultas() {
        String correo = txtCorreo.getText();
        areaResultados.setText(""); // Limpiar resultados previos

        // Lógica para buscar las consultas en la base de datos
        Connection conexion = null;
        try {
            conexion = ConexionDB.conectar();
            String sql = "SELECT * FROM consultas WHERE Correo = ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, correo);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // Formatear y mostrar cada consulta
                String textoConsulta = rs.getString("Consulta");
                Timestamp fechaConsulta = rs.getTimestamp("FechaConsulta");
                areaResultados.append("Consulta: " + textoConsulta + " - Fecha: " + fechaConsulta.toString() + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar consultas: " + e.getMessage());
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
