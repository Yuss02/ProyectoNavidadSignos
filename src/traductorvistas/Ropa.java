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

public class Ropa extends JPanel {

    private JSplitPane splitPane;
    private JPanel panelBotones;
    private JLabel labelImagen;

    /**
     * Clase Ropa que proporciona una interfaz gráfica para mostrar diferentes
     * prendas de ropa en lengua de signos. Esta clase presenta una lista de
     * prendas de ropa y permite al usuario seleccionar una para visualizar su
     * representación en lengua de signos.
     */
    public Ropa() {
        setLayout(new BorderLayout());

        panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelBotones);
        labelImagen = new JLabel();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, labelImagen);
        add(splitPane, BorderLayout.CENTER);

        cargarRopa();
    }

    /**
     * Constructor que inicializa la interfaz de usuario para la sección de
     * ropa. Configura un panel con botones para cada prenda y un área para
     * mostrar la imagen correspondiente.
     */
    private void cargarRopa() {
        try {
            Connection conexion = ConexionDB.conectar();
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Prenda, URLImagen FROM ropa");

            while (rs.next()) {
                String prenda = rs.getString("Prenda");
                String urlImagen = rs.getString("URLImagen");

                JButton boton = new JButton(prenda);
                boton.setAlignmentX(Component.CENTER_ALIGNMENT);
                boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, boton.getMinimumSize().height));

                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (Component comp : panelBotones.getComponents()) {
                            if (comp instanceof JButton) {
                                comp.setBackground(null);
                            }
                        }
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
     * Muestra la imagen de la prenda seleccionada en el panel.
     *
     * @param urlImagen URL de la imagen de la prenda a mostrar.
     */
    private void mostrarImagen(String urlImagen) {
        try {
            URL url = new URL(urlImagen);
            BufferedImage imagenOriginal = ImageIO.read(url);
            double ratio = (double) imagenOriginal.getWidth() / imagenOriginal.getHeight();
            int ancho = labelImagen.getWidth();
            int alto = (int) (ancho / ratio);

            if (alto > labelImagen.getHeight()) {
                alto = labelImagen.getHeight();
                ancho = (int) (alto * ratio);
            }

            Image imagenRedimensionada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            labelImagen.setIcon(new ImageIcon(imagenRedimensionada));
        } catch (IOException e) {
            e.printStackTrace();
            labelImagen.setIcon(null);
            labelImagen.setText("Error al cargar la imagen");
        }
    }
}
