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
package proyectonavidadsignos;

import traductorvistas.Vista;
import javax.swing.SwingUtilities;

/**
 * Clase principal del Proyecto Navidad Signos. Esta clase inicia la aplicación
 * del Traductor de Signos, configurando y mostrando la interfaz gráfica
 * principal para el usuario.
 */
public class ProyectoNavidadSignos {

    /**
     * El método main es el punto de entrada de la aplicación. Utiliza
     * SwingUtilities.invokeLater para asegurar que la interfaz gráfica de
     * usuario se maneje en el hilo de despacho de eventos de Swing, lo cual
     * mejora la estabilidad y rendimiento.
     *
     * @param args Argumentos pasados a la aplicación desde la línea de
     * comandos. No se utilizan en esta aplicación.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Vista();
            }
        });
    }
}
