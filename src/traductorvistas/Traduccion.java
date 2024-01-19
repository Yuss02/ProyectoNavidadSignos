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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.URL;
import proyectonavidadsignos.ConexionDB;

public class Traduccion extends JPanel {

    private JTextField textFieldPalabra;
    private JButton btnTraducir;
    private JPanel panelImagenes;

    public Traduccion() {
        setLayout(new BorderLayout());

        // Panel para entrada de texto y botón
        JPanel panelEntrada = new JPanel();
        panelEntrada.add(new JLabel("Introduce la palabra que quieres traducir: "));
        textFieldPalabra = new JTextField(20);
        btnTraducir = new JButton("Traducir");
        panelEntrada.add(textFieldPalabra);
        panelEntrada.add(btnTraducir);
        add(panelEntrada, BorderLayout.NORTH);

        // Panel para mostrar las imágenes
        panelImagenes = new JPanel();
        panelImagenes.setLayout(new FlowLayout());
        add(new JScrollPane(panelImagenes), BorderLayout.CENTER);

        // Acción del botón Traducir
        btnTraducir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traducirPalabra();
            }
        });

        // Acción de cambio en tiempo real
        textFieldPalabra.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                traducirPalabra();
            }
        });
    }

    private void traducirPalabra() {
        panelImagenes.removeAll();
        String palabra = textFieldPalabra.getText().toLowerCase();

        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            String urlImagen = obtenerURLImagen(letra);
            mostrarImagen(urlImagen);
        }

        panelImagenes.revalidate();
        panelImagenes.repaint();
    }

    private String obtenerURLImagen(char letra) {
        String urlImagen = "";
        Connection conexion = null;
        try {
            conexion = ConexionDB.conectar();
            PreparedStatement pstmt = conexion.prepareStatement("SELECT URLImagen FROM letras WHERE Letra = ?");
            pstmt.setString(1, String.valueOf(letra));

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                urlImagen = rs.getString("URLImagen");
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return urlImagen;
    }

    private void mostrarImagen(String urlImagen) {
        try {
            URL url = new URL(urlImagen);
            BufferedImage imagenOriginal = ImageIO.read(url);
            String palabra = textFieldPalabra.getText().toLowerCase();

            // Obtiene el tamaño disponible del panel
            int anchoMaximo = panelImagenes.getWidth() / palabra.length(); // Divide el ancho del panel por la longitud de la palabra
            int altoMaximo = panelImagenes.getHeight();

            // Calcula el nuevo tamaño manteniendo el ratio de aspecto
            double ratio = (double) imagenOriginal.getWidth() / imagenOriginal.getHeight();
            int anchoRedimensionado = anchoMaximo;
            int altoRedimensionado = (int) (anchoRedimensionado / ratio);

            if (altoRedimensionado > altoMaximo) {
                altoRedimensionado = altoMaximo;
                anchoRedimensionado = (int) (altoRedimensionado * ratio);
            }

            // Redimensiona la imagen
            Image imagenRedimensionada = imagenOriginal.getScaledInstance(anchoRedimensionado, altoRedimensionado, Image.SCALE_SMOOTH);

            // Agrega la imagen redimensionada al panel
            ImageIcon icono = new ImageIcon(imagenRedimensionada);
            JLabel labelImagen = new JLabel(icono);
            panelImagenes.add(labelImagen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
