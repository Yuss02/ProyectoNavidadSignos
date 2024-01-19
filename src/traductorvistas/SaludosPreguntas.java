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
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;
import proyectonavidadsignos.ConexionDB;

public class SaludosPreguntas extends JPanel {

    private JSplitPane splitPane;
    private JPanel panelBotones;
    private JLabel labelImagen;

    public SaludosPreguntas() {
        setLayout(new BorderLayout());

        panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelBotones);
        labelImagen = new JLabel();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, labelImagen);
        add(splitPane, BorderLayout.CENTER);

        cargarPreguntas();
    }

    private void cargarPreguntas() {
        try {
            Connection conexion = ConexionDB.conectar();
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SaludoPregunta, URLImagen FROM saludos_preguntas");

            while (rs.next()) {
                String pregunta = rs.getString("SaludoPregunta");
                String urlImagen = rs.getString("URLImagen");

                JButton boton = new JButton(pregunta);
                boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, boton.getMinimumSize().height));
                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
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
