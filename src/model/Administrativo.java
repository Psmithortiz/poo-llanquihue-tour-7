package model;

import java.time.LocalDate;

/**
 * Representa a un empleado administrativo de la agencia, encargado de las
 * labores de gestion interna. Extiende a {@link Empleado} agregando el área
 * en la que se desempeña.
 *
 * @author Pablo Smith
 * @version 1.0
 */
public class Administrativo extends Empleado {

    private String area;

    /**
     * Crea un empleado administrativo.
     *
     * @param nombre            nombre del administrativo; no puede estar vacío
     * @param rut               RUT del administrativo; no puede ser nulo
     * @param direccion         dirección del administrativo; no puede ser nula
     * @param correo            correo de contacto; no puede estar vacío
     * @param telefono          teléfono de contacto; no puede estar vacío
     * @param sueldo            sueldo mensual; debe ser mayor a 0
     * @param fechaContratacion fecha de contratación; no puede ser futura
     * @param area              área en la que se desempeña; no puede estar vacío
     * @throws IllegalArgumentException si algún dato no cumple las validaciones
     */
    public Administrativo(String nombre, Rut rut, Direccion direccion, String correo, String telefono,
                          int sueldo, LocalDate fechaContratacion, String area) {
        super(nombre, rut, direccion, correo, telefono, sueldo, fechaContratacion);
        setArea(area);
    }

    /**
     * Obtiene el area del administrativo.
     *
     * @return área en la que se desempeña
     */
    public String getArea() {
        return area;
    }

    /**
     * Establece el area del administrativo.
     *
     * @param area area no vacía
     * @throws IllegalArgumentException si el area es nula o está vacía
     */
    public void setArea(String area) {
        if (area == null || area.trim().isEmpty()) {
            throw new IllegalArgumentException("El area es obligatoria");
        }
        this.area = area.trim();
    }

    /**
     * @return el mensaje que confirma el alta del administrativo en el sistema
     */
    @Override
    public String registrar() {
        return "Administrativo registrado: " + getNombre() + " (Area " + area + ")";
    }

    /**
     * @return los datos completos del administrativo; delega en {@link #toString()}
     */
    @Override
    public String mostrarDatos() {
        return toString();
    }

    /**
     * @return la información del administrativo, anteponiendo su tipo al texto heredado
     */
    @Override
    public String toString() {
        return "Administrativo: " + super.toString() + " | Area: " + area;
    }
}