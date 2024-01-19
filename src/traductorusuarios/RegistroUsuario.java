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
 * Clase RegistroUsuario que proporciona una interfaz gráfica para registrar
 * usuarios. Esta clase permite a los usuarios ingresar su nombre, apellido y
 * correo electrónico, y registra estos datos en la base de datos.
 */
public class RegistroUsuario extends JPanel {

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCorreo;
    private JButton btnRegistrar;
    private JLabel txtMensaje;

    /**
     * Constructor que inicializa la interfaz de usuario para el registro de
     * usuarios. Configura los campos de texto y el botón de registro en un
     * panel de formulario.
     */
    public RegistroUsuario() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10)); // Modificado a 5 filas para incluir el botón

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(20);
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Apellido:"));
        txtApellido = new JTextField(20); // Asumiendo que tienes una variable txtApellido
        panelFormulario.add(txtApellido);

        panelFormulario.add(new JLabel("Correo:"));
        txtCorreo = new JTextField(20);
        panelFormulario.add(txtCorreo);

        btnRegistrar = new JButton("Registrar");
        panelFormulario.add(btnRegistrar); // Agrega el botón al panelFormulario

        // Añadiendo panelFormulario al JPanel (RegistroUsuario)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Para que el panel se expanda horizontalmente
        gbc.insets = new Insets(20, 20, 20, 20);
        add(panelFormulario, gbc);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
    }

    /**
     * Método que se ejecuta cuando se presiona el botón Registrar. Recoge los
     * datos de los campos de texto y los guarda en la base de datos.
     */
    private void registrarUsuario() {
        String Nombre = txtNombre.getText();
        String Apellido = txtCorreo.getText();
        String CorreoElectronico = new String(txtCorreo.getText());

        Connection conexion = null;
        try {
            conexion = ConexionDB.conectar();
            String sql = "INSERT INTO usuario (Nombre, Apellido, CorreoElectronico) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, Nombre);
            pstmt.setString(2, Apellido);
            pstmt.setString(3, CorreoElectronico);

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Usuario registrado con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo registrar el usuario.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al registrar el usuario: " + e.getMessage());
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
