package model;

import java.time.LocalDate;

/**
 * Representa a un empleado interno de la agencia Llanquihue Tour. Es una clase
 * abstracta intermedia que agrupa los atributos comunes a todo empleado: su
 * sueldo y su fecha de contratación.
 * Al ser abstracta, no implementa los metodos de {@link Registrable}: los
 * traslada a sus subclases concretas.
 *
 * @author Pablo Smith
 * @version 1.0
 */
public abstract class Empleado extends Persona {

    private int sueldo;
    private LocalDate fechaContratacion;

    /**
     * Crea un empleado. Delega los datos personales en la superclase y valida
     * sus propios atributos mediante los setters.
     *
     * @param nombre            nombre del empleado; no puede estar vacío
     * @param rut               RUT del empleado; no puede ser nulo
     * @param direccion         dirección del empleado; no puede ser nula
     * @param correo            correo de contacto; no puede estar vacío
     * @param telefono          teléfono de contacto; no puede estar vacío
     * @param sueldo            sueldo mensual en pesos; debe ser mayor a 0
     * @param fechaContratacion fecha de contratación; no puede ser futura
     * @throws IllegalArgumentException si algún dato no cumple las validaciones
     */
    public Empleado(String nombre, Rut rut, Direccion direccion, String correo,
                    String telefono, int sueldo, LocalDate fechaContratacion) {
        super(nombre, rut, direccion, correo, telefono);
        setSueldo(sueldo);
        setFechaContratacion(fechaContratacion);
    }

    /**
     * Obtiene el sueldo del empleado.
     *
     * @return sueldo mensual en pesos
     */
    public int getSueldo() {
        return sueldo;
    }

    /**
     * Establece el sueldo del empleado.
     *
     * @param sueldo sueldo mensual en pesos; debe ser mayor a 0
     * @throws IllegalArgumentException si el sueldo es menor o igual a 0
     */
    public void setSueldo(int sueldo) {
        if (sueldo <= 0) {
            throw new IllegalArgumentException("El sueldo debe ser mayor a 0");
        }
        this.sueldo = sueldo;
    }

    /**
     * Obtiene la fecha de contratación.
     *
     * @return fecha en que el empleado fue contratado
     */
    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    /**
     * Establece la fecha de contratación, validando que no sea futura: un
     * empleado no puede haber sido contratado en una fecha que aun no ocurre.
     *
     * @param fechaContratacion fecha de contratación; no puede ser posterior a hoy
     * @throws IllegalArgumentException si la fecha es nula o futura
     */
    public void setFechaContratacion(LocalDate fechaContratacion) {
        if (fechaContratacion == null) {
            throw new IllegalArgumentException("La fecha de contratacion es obligatoria");
        }
        if (fechaContratacion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de contratacion no puede ser futura");
        }
        this.fechaContratacion = fechaContratacion;
    }

    /**
     * @return el texto común a todo empleado: los datos de la persona mas su
     *         sueldo y fecha de contratación
     */
    @Override
    public String toString() {
        return super.toString()
                + " | Sueldo: $" + String.format("%,d", sueldo)
                + " | Contratado: " + fechaContratacion;
    }
}