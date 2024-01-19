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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * Clase Inicio que proporciona la pantalla de bienvenida y las instrucciones
 * iniciales para la aplicación Traductor de Signos. Esta clase presenta una
 * interfaz visual informativa que incluye un enlace a la Fundación CNSE y
 * detalles sobre cómo usar la aplicación.
 */
public class Inicio extends JPanel {

    /**
     * Constructor que inicializa y configura la interfaz de usuario de la
     * pantalla de inicio. Organiza los componentes en un diseño de BorderLayout
     * con paneles izquierdo y derecho para la información y las instrucciones.
     */
    public Inicio() {
        setLayout(new BorderLayout());

        // Título
        JLabel lblTitulo = new JLabel("Traductor de Signos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 24));
        add(lblTitulo, BorderLayout.NORTH);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Agrega margen

        // Panel principal dividido en dos columnas
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(1, 2));

        // Panel izquierdo
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.add(Box.createVerticalGlue()); // Espacio vertical flexible antes de los componentes
        panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Agrega margen

        JLabel lblLogo = new JLabel();
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblLogo.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20)); // Agrega margen
        try {
            URL urlImagen = new URL("https://www.fundacioncnse.org/imagenes/logo_fundacion_cnse.png");
            ImageIcon icono = new ImageIcon(urlImagen);
            lblLogo.setIcon(icono);
        } catch (Exception e) {
            lblLogo.setText("Error al cargar la imagen.");
        }
        panelIzquierdo.add(lblLogo);

        JLabel lblDescripcion = new JLabel("<html>Este proyecto apoya a la Fundación CNSE y busca mejorar la comunicación entre personas oyentes y no oyentes.</html>");
        lblDescripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIzquierdo.add(lblDescripcion);

        panelIzquierdo.add(Box.createVerticalGlue()); // Espacio vertical flexible después de los componentes

        // Panel derecho
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.add(Box.createVerticalGlue()); // Espacio vertical flexible antes de los componentes
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Agrega margen

        JLabel lblInstruccionesTitulo = new JLabel("Instrucciones", SwingConstants.CENTER);
        lblInstruccionesTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblInstruccionesTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20)); // Agrega margen
        panelDerecho.add(lblInstruccionesTitulo);

        JLabel lblInstrucciones = new JLabel("<html> - Regístrate para comenzar.<br>- Usa 'Traductor' para traducir palabras letra a letra   <br>- Usa Ropa, Saludos,Cortesía y Preguntas para palabras ya completas. <br>- Tmbién puedes Registrarte, ver el historial y eliminar usuarios </html>");
        lblInstrucciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerecho.add(lblInstrucciones);

        panelDerecho.add(Box.createVerticalGlue()); // Espacio vertical flexible después de los componentes

        panelPrincipal.add(panelIzquierdo);
        panelPrincipal.add(panelDerecho);
        add(panelPrincipal, BorderLayout.CENTER);

        // Enlace a la fundación
        JLabel lblEnlace = new JLabel("<html><div style='text-align: center;'><a href='https://www.fundacioncnse.org'>Visite la página web de la Fundación CNSE</a></div></html>");
        lblEnlace.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblEnlace.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20)); // Agrega margen

        lblEnlace.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                try {
                    Desktop.getDesktop().browse(new URL("https://www.fundacioncnse.org").toURI());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        add(lblEnlace, BorderLayout.SOUTH);
    }
}
