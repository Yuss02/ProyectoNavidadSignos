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

public class Usuario {

    private String nombre;
    private String correo;
    private String contraseña;

    public Usuario(String nombre, String correo, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    // Métodos getter
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    // Métodos setter
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    // Métodos para registrar y eliminar usuario
    public void registrar() {
        // Lógica para registrar el usuario en la base de datos
    }

    public void eliminar() {
        // Lógica para eliminar el usuario de la base de datos
    }
}
