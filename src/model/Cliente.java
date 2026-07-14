package model;

/**
 * Representa a un cliente de la agencia, es decir, la persona que contrata
 * los tours. No agrega atributos propios: lo que lo distingue es su rol dentro
 * del sistema y su participación en las reservas.
 *
 * @author Pablo Smith
 * @version 1.0
 */
public class Cliente extends Persona {

    /**
     * Crea un cliente.
     *
     * @param nombre    nombre del cliente; no puede estar vacío
     * @param rut       RUT del cliente; no puede ser nulo
     * @param direccion dirección del cliente; no puede ser nula
     * @param correo    correo de contacto; no puede estar vacío
     * @param telefono  teléfono de contacto; no puede estar vacío
     * @throws IllegalArgumentException si algún dato no cumple las validaciones
     */
    public Cliente(String nombre, Rut rut, Direccion direccion, String correo, String telefono) {
        super(nombre, rut, direccion, correo, telefono);
    }

    /**
     * @return el mensaje que confirma el alta del cliente en el sistema
     */
    @Override
    public String registrar() {
        return "Cliente registrado: " + getNombre() + " (" + getRut() + ")";
    }

    /**
     * @return los datos completos del cliente; delega en {@link #toString()}
     *         para mantener una unica fuente de formato
     */
    @Override
    public String mostrarDatos() {
        return toString();
    }

    /**
     * @return la información del cliente, anteponiendo su tipo al texto heredado
     */
    @Override
    public String toString() {
        return "Cliente: " + super.toString();
    }
}