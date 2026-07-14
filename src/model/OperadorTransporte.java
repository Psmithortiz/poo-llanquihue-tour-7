package model;

/**
 * Proveedor externo que se encarga del traslado de los turistas. Extiende a
 * {@link Proveedor} agregando la capacidad de pasajeros y el tipo de vehículo
 * que opera.
 *
 * @author Pablo Smith
 * @version 1.0
 */
public class OperadorTransporte extends Proveedor {

    private int capacidadPasajeros;
    private String tipoVehiculo;

    /**
     * Crea un operador de transporte.
     *
     * @param nombre             nombre del contacto; no puede estar vacío
     * @param rut                RUT del proveedor; no puede ser nulo
     * @param direccion          dirección del proveedor; no puede ser nula
     * @param correo             correo de contacto; no puede estar vacío
     * @param telefono           teléfono de contacto; no puede estar vacío
     * @param empresa            empresa a la que pertenece; no puede estar vacío
     * @param servicioQuePresta  servicio que presta; no puede estar vacío
     * @param capacidadPasajeros capacidad de pasajeros; debe ser mayor a 0
     * @param tipoVehiculo       tipo de vehículo; no puede estar vacío
     * @throws IllegalArgumentException si algún dato no cumple las validaciones
     */
    public OperadorTransporte(String nombre, Rut rut, Direccion direccion, String correo,
                              String telefono, String empresa, String servicioQuePresta,
                              int capacidadPasajeros, String tipoVehiculo) {
        super(nombre, rut, direccion, correo, telefono, empresa, servicioQuePresta);
        setCapacidadPasajeros(capacidadPasajeros);
        setTipoVehiculo(tipoVehiculo);
    }

    /**
     * Obtiene la capacidad de pasajeros del operador.
     *
     * @return capacidad de pasajeros
     */
    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    /**
     * Establece la capacidad de pasajeros.
     *
     * @param capacidadPasajeros capacidad; debe ser mayor a 0
     * @throws IllegalArgumentException si la capacidad es menor o igual a 0
     */
    public void setCapacidadPasajeros(int capacidadPasajeros) {
        if (capacidadPasajeros <= 0) {
            throw new IllegalArgumentException("La capacidad de pasajeros debe ser mayor a 0");
        }
        this.capacidadPasajeros = capacidadPasajeros;
    }

    /**
     * Obtiene el tipo de vehículo del operador.
     *
     * @return tipo de vehículo
     */
    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    /**
     * Establece el tipo de vehículo.
     *
     * @param tipoVehiculo tipo de vehículo no vacío
     * @throws IllegalArgumentException si el tipo de vehículo es nulo o está vacío
     */
    public void setTipoVehiculo(String tipoVehiculo) {
        if (tipoVehiculo == null || tipoVehiculo.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de vehiculo es obligatorio");
        }
        this.tipoVehiculo = tipoVehiculo.trim();
    }

    /**
     * @return el mensaje que confirma el alta del operador en el sistema
     */
    @Override
    public String registrar() {
        return "Operador de transporte registrado: " + getEmpresa()
                + " (" + tipoVehiculo + ", " + capacidadPasajeros + " pasajeros)";
    }

    /**
     * @return los datos completos del operador; delega en {@link #toString()}
     */
    @Override
    public String mostrarDatos() {
        return toString();
    }

    /**
     * @return la información del operador, anteponiendo su tipo al texto heredado
     */
    @Override
    public String toString() {
        return "Operador transporte: " + super.toString()
                + " | Vehiculo: " + tipoVehiculo
                + " | Capacidad: " + capacidadPasajeros + " pasajeros";
    }
}