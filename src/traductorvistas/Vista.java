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
import traductorusuarios.EliminarUsuario;
import traductorusuarios.RegistroUsuario;
import traductorvistas.*;

/**
 * Clase Vista que actúa como la interfaz gráfica principal de la aplicación de
 * traducción de signos. Esta clase configura y muestra todos los elementos de
 * la interfaz de usuario, incluyendo botones de navegación y paneles.
 */
public class Vista {

    private JFrame frame;
    private JPanel panelMenu;
    private JButton btnTraductor;
    private JButton btnSaludosCortesia;
    private JButton btnSaludosPreguntas;
    private JPanel panelContenido;
    private JButton btnRegistroUsuario;
    private JButton btnREliminarUsuario;
    private JButton btnConsultar;
    private JButton btnInicio;
    private JButton btnRopa;

    /**
     * Constructor de Vista. Inicializa la ventana principal y todos los
     * componentes de la interfaz de usuario.
     */
    public Vista() {
        frame = new JFrame("Traductor de Signos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 650);
        frame.setLayout(new BorderLayout());

        panelMenu = new JPanel();
        panelMenu.setBackground(Color.BLUE);
        panelMenu.setPreferredSize(new Dimension(1000, 75));
        frame.add(panelMenu, BorderLayout.SOUTH);

        btnInicio = new JButton("Inicio");

        btnRegistroUsuario = new JButton("Registro");
        btnTraductor = new JButton("Traductor");
        btnSaludosCortesia = new JButton("Saludos y Cortesía");
        btnSaludosPreguntas = new JButton("Saludos y Preguntas");
        btnRopa = new JButton("Ropa");
        btnREliminarUsuario = new JButton("Eliminar Usuario");
        btnConsultar = new JButton("Consultar");

        panelMenu.add(btnInicio);
        panelMenu.add(btnTraductor);
        panelMenu.add(btnSaludosCortesia);
        panelMenu.add(btnSaludosPreguntas);
        panelMenu.add(btnRegistroUsuario);
        panelMenu.add(btnREliminarUsuario);
        panelMenu.add(btnConsultar);
        panelMenu.add(btnRopa);

        panelContenido = new JPanel(new CardLayout());
        frame.add(panelContenido, BorderLayout.CENTER);

        // Agrega los paneles a panelContenido
        panelContenido.add(new Inicio(), "Inicio");
        panelContenido.add(new RegistroUsuario(), "RegistroUsuario");
        panelContenido.add(new Traduccion(), "Traduccion");
        panelContenido.add(new SaludosCortesia(), "SaludosCortesia");
        panelContenido.add(new SaludosPreguntas(), "SaludosPreguntas");
        panelContenido.add(new Ropa(), "Ropa");
        panelContenido.add(new ConsultarConsultas(), "Consultar");
        panelContenido.add(new EliminarUsuario(), "Eliminar Usuario");

        // Establece el panel de inicio como el panel visible inicialmente
        CardLayout cl = (CardLayout) (panelContenido.getLayout());
        cl.show(panelContenido, "Inicio");

        // Manejadores de eventos para los botones
        btnInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (panelContenido.getLayout());
                cl.show(panelContenido, "Inicio");
            }
        });

        btnRegistroUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (panelContenido.getLayout());
                cl.show(panelContenido, "RegistroUsuario");
            }
        });

        btnSaludosPreguntas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (panelContenido.getLayout());
                cl.show(panelContenido, "SaludosPreguntas");
            }
        });

        btnRopa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (panelContenido.getLayout());
                cl.show(panelContenido, "Ropa");
            }
        });

        btnTraductor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (panelContenido.getLayout());
                cl.show(panelContenido, "Traduccion");
            }
        });

        btnSaludosCortesia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (panelContenido.getLayout());
                cl.show(panelContenido, "SaludosCortesia");
            }
        });

        btnREliminarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (panelContenido.getLayout());
                cl.show(panelContenido, "Eliminar Usuario");
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (panelContenido.getLayout());
                cl.show(panelContenido, "Consultar");
            }
        });

        frame.setVisible(true);
    }

    /**
     * Punto de entrada principal de la aplicación. Inicia la interfaz gráfica
     * de usuario en el hilo de despacho de eventos de Swing.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Vista();
            }
        });
    }
}
