package model;

import java.time.LocalDate;

/**
 * Representa a un chofer interno de la agencia, encargado de conducir los
 * vehículos propios. Extiende a {@link Empleado} agregando su tipo de licencia.
 *
 * @author Pablo Smith
 * @version 1.0
 */
public class Chofer extends Empleado {

    private String tipoLicencia;

    /**
     * Crea un chofer.
     *
     * @param nombre            nombre del chofer; no puede estar vacío
     * @param rut               RUT del chofer; no puede ser nulo
     * @param direccion         dirección del chofer; no puede ser nula
     * @param correo            correo de contacto; no puede estar vacío
     * @param telefono          teléfono de contacto; no puede estar vacío
     * @param sueldo            sueldo mensual; debe ser mayor a 0
     * @param fechaContratacion fecha de contratación; no puede ser futura
     * @param tipoLicencia      tipo de licencia de conducir; no puede estar vacío
     * @throws IllegalArgumentException si algún dato no cumple las validaciones
     */
    public Chofer(String nombre, Rut rut, Direccion direccion, String correo, String telefono,
                  int sueldo, LocalDate fechaContratacion, String tipoLicencia) {
        super(nombre, rut, direccion, correo, telefono, sueldo, fechaContratacion);
        setTipoLicencia(tipoLicencia);
    }

    /**
     * Obtiene el tipo de licencia del chofer.
     *
     * @return tipo de licencia de conducir
     */
    public String getTipoLicencia() {
        return tipoLicencia;
    }

    /**
     * Establece el tipo de licencia del chofer.
     *
     * @param tipoLicencia tipo de licencia no vacío
     * @throws IllegalArgumentException si el tipo de licencia es nulo o está vacío
     */
    public void setTipoLicencia(String tipoLicencia) {
        if (tipoLicencia == null || tipoLicencia.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de licencia es obligatorio");
        }
        this.tipoLicencia = tipoLicencia.trim();
    }

    /**
     * @return el mensaje que confirma el alta del chofer en el sistema
     */
    @Override
    public String registrar() {
        return "Chofer registrado: " + getNombre() + " (Licencia " + tipoLicencia + ")";
    }

    /**
     * @return los datos completos del chofer; delega en {@link #toString()}
     */
    @Override
    public String mostrarDatos() {
        return toString();
    }

    /**
     * @return la información del chofer, anteponiendo su tipo al texto heredado
     */
    @Override
    public String toString() {
        return "Chofer: " + super.toString() + " | Licencia: " + tipoLicencia;
    }
}