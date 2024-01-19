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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import javax.imageio.ImageIO;
import proyectonavidadsignos.ConexionDB;

/**
 * Clase SaludosCortesia que proporciona una interfaz gráfica para mostrar
 * saludos y frases de cortesía en lengua de signos. Esta clase presenta una
 * lista de saludos y frases de cortesía, permitiendo al usuario seleccionar uno
 * para visualizar su representación en lengua de signos.
 */
public class SaludosCortesia extends JPanel {

    private JSplitPane splitPane;
    private JPanel panelBotones;
    private JLabel labelImagen;

    /**
     * Constructor que inicializa la interfaz de usuario para la sección de
     * saludos y cortesía. Configura un panel con botones para cada saludo y un
     * área para mostrar la imagen correspondiente.
     */
    public SaludosCortesia() {
        setLayout(new BorderLayout());

        panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelBotones);
        labelImagen = new JLabel();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, labelImagen);
        add(splitPane, BorderLayout.CENTER);

        cargarSaludos();
    }

    /**
     * Carga los saludos y frases de cortesía desde la base de datos y los
     * muestra como botones en el panel. Al hacer clic en un botón, se muestra
     * la imagen correspondiente del saludo o frase de cortesía en lengua de
     * signos.
     */
    private void cargarSaludos() {
        try {
            Connection conexion = ConexionDB.conectar();
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SaludoCortesia, URLImagen FROM saludos_cortesia");

            while (rs.next()) {
                String saludo = rs.getString("SaludoCortesia");
                String urlImagen = rs.getString("URLImagen");

                JButton boton = new JButton(saludo);
                boton.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinear al centro
                boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, boton.getMinimumSize().height)); // Tamaño máximo

                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Restablecer el color de todos los botones
                        for (Component comp : panelBotones.getComponents()) {
                            if (comp instanceof JButton) {
                                comp.setBackground(null);
                            }
                        }
                        // Cambiar el color del botón seleccionado
                        boton.setBackground(Color.BLUE);
                        mostrarImagen(urlImagen);
                    }
                });
                panelBotones.add(boton);
            }
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra la imagen del saludo o frase de cortesía seleccionado en el
     * panel.
     *
     * @param urlImagen URL de la imagen del saludo o frase de cortesía a
     * mostrar.
     */
    private void mostrarImagen(String urlImagen) {
        try {
            URL url = new URL(urlImagen);
            BufferedImage imagenOriginal = ImageIO.read(url);

            // Calcula el ratio de aspecto de la imagen original
            double ratio = (double) imagenOriginal.getWidth() / imagenOriginal.getHeight();

            // Determina el ancho y alto para mantener el ratio de aspecto
            int ancho = labelImagen.getWidth();
            int alto = (int) (ancho / ratio);

            // Comprueba si el alto calculado es mayor que el alto del JLabel
            if (alto > labelImagen.getHeight()) {
                alto = labelImagen.getHeight();
                ancho = (int) (alto * ratio);
            }

            // Redimensiona la imagen
            Image imagenRedimensionada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

            // Establece la imagen en el JLabel
            labelImagen.setIcon(new ImageIcon(imagenRedimensionada));
        } catch (IOException e) {
            e.printStackTrace();
            labelImagen.setIcon(null);
            labelImagen.setText("Error al cargar la imagen");
        }
    }

}
