package model;

/**
 * Proveedor externo que ofrece alojamiento a los turistas. Extiende a
 * {@link Proveedor} agregando el número de habitaciones disponibles.
 *
 * @author Pablo Smith
 * @version 1.0
 */
public class ProveedorAlojamiento extends Proveedor {

    private int numeroHabitaciones;

    /**
     * Crea un proveedor de alojamiento.
     *
     * @param nombre             nombre del contacto; no puede estar vacío
     * @param rut                RUT del proveedor; no puede ser nulo
     * @param direccion          dirección del proveedor; no puede ser nula
     * @param correo             correo de contacto; no puede estar vacío
     * @param telefono           teléfono de contacto; no puede estar vacío
     * @param empresa            empresa a la que pertenece; no puede estar vacío
     * @param servicioQuePresta  servicio que presta; no puede estar vacío
     * @param numeroHabitaciones número de habitaciones; debe ser mayor a 0
     * @throws IllegalArgumentException si algún dato no cumple las validaciones
     */
    public ProveedorAlojamiento(String nombre, Rut rut, Direccion direccion, String correo,
                                String telefono, String empresa, String servicioQuePresta,
                                int numeroHabitaciones) {
        super(nombre, rut, direccion, correo, telefono, empresa, servicioQuePresta);
        setNumeroHabitaciones(numeroHabitaciones);
    }

    /**
     * Obtiene el número de habitaciones disponibles.
     *
     * @return número de habitaciones
     */
    public int getNumeroHabitaciones() {
        return numeroHabitaciones;
    }

    /**
     * Establece el número de habitaciones disponibles.
     *
     * @param numeroHabitaciones número de habitaciones; debe ser mayor a 0
     * @throws IllegalArgumentException si el número es menor o igual a 0
     */
    public void setNumeroHabitaciones(int numeroHabitaciones) {
        if (numeroHabitaciones <= 0) {
            throw new IllegalArgumentException("El numero de habitaciones debe ser mayor a 0");
        }
        this.numeroHabitaciones = numeroHabitaciones;
    }

    /**
     * @return el mensaje que confirma el alta del proveedor en el sistema
     */
    @Override
    public String registrar() {
        return "Proveedor de alojamiento registrado: " + getEmpresa()
                + " (" + numeroHabitaciones + " habitaciones)";
    }

    /**
     * @return los datos completos del proveedor; delega en {@link #toString()}
     */
    @Override
    public String mostrarDatos() {
        return toString();
    }

    /**
     * @return la información del proveedor, anteponiendo su tipo al texto heredado
     */
    @Override
    public String toString() {
        return "Proveedor alojamiento: " + super.toString()
                + " | Habitaciones: " + numeroHabitaciones;
    }
}