/**
 * Traductor de Lengua de Signos
 *
 * Clase: Programación
 * Descripción:Proyecto Navidad
 *
 * Autor: ARBEYO
 * Fecha de Creación: Enero 2024
 * Versión: 1.0.0
 */
package traductorusuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import proyectonavidadsignos.ConexionDB;

/**
 * Clase EliminarUsuario que proporciona una interfaz gráfica para eliminar
 * usuarios. Esta clase permite a los administradores eliminar usuarios del
 * sistema usando su correo electrónico.
 */
public class EliminarUsuario extends JPanel {

    private JTextField txtCorreo;
    private JButton btnEliminar;

    /**
     * Constructor que inicializa la interfaz de usuario para la eliminación de
     * usuarios. Configura los campos de texto y el botón de eliminación en un
     * panel de formulario.
     */
    public EliminarUsuario() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel panelFormulario = new JPanel(new GridLayout(2, 2, 10, 10));

        //  componentes al panelFormulario
        panelFormulario.add(new JLabel("Correo del usuario a eliminar:"));
        txtCorreo = new JTextField(20);
        panelFormulario.add(txtCorreo);

        btnEliminar = new JButton("Eliminar Usuario");
        panelFormulario.add(btnEliminar);

        // añadir panelFormulario al JPanel (EliminarUsuario)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 20, 20, 20);
        add(panelFormulario, gbc);

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });
    }

    /**
     * Método que se ejecuta cuando se presiona el botón Eliminar. Recoge el
     * correo electrónico del campo de texto y elimina al usuario
     * correspondiente de la base de datos.
     */
    private void eliminarUsuario() {
        String correo = txtCorreo.getText();

        Connection conexion = null;
        try {
            conexion = ConexionDB.conectar();
            String sql = "DELETE FROM usuario WHERE CorreoElectronico = ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, correo);

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el usuario.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el usuario: " + e.getMessage());
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
