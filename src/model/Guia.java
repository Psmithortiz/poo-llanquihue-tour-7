package model;

import java.time.LocalDate;

/**
 * Representa a un guía turístico de la agencia. Extiende a {@link Empleado}
 * agregando el idioma que domina y sus años de experiencia.
 *
 * @author Pablo Smith
 * @version 1.0
 */
public class Guia extends Empleado {

    private String idioma;
    private int anosExperiencia;

    /**
     * Crea un guia turístico.
     *
     * @param nombre            nombre del guía; no puede estar vacío
     * @param rut               RUT del guia; no puede ser nulo
     * @param direccion         dirección del guía; no puede ser nula
     * @param correo            correo de contacto; no puede estar vacío
     * @param telefono          teléfono de contacto; no puede estar vacío
     * @param sueldo            sueldo mensual; debe ser mayor a 0
     * @param fechaContratacion fecha de contratación; no puede ser futura
     * @param idioma            idioma o idiomas que domina; no puede estar vacío
     * @param anosExperiencia   años de experiencia; entre 0 y 60
     * @throws IllegalArgumentException si algún dato no cumple las validaciones
     */
    public Guia(String nombre, Rut rut, Direccion direccion, String correo, String telefono,
                int sueldo, LocalDate fechaContratacion, String idioma, int anosExperiencia) {
        super(nombre, rut, direccion, correo, telefono, sueldo, fechaContratacion);
        setIdioma(idioma);
        setAnosExperiencia(anosExperiencia);
    }

    /**
     * Obtiene el idioma del guia.
     *
     * @return idioma o idiomas que domina
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * Establece el idioma del guía.
     *
     * @param idioma idioma no vacío
     * @throws IllegalArgumentException si el idioma es nulo o está vacío
     */
    public void setIdioma(String idioma) {
        if (idioma == null || idioma.trim().isEmpty()) {
            throw new IllegalArgumentException("El idioma es obligatorio");
        }
        this.idioma = idioma.trim();
    }

    /**
     * Obtiene los años de experiencia del guía.
     *
     * @return años de experiencia
     */
    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    /**
     * Establece los años de experiencia del guía.
     *
     * @param anosExperiencia años de experiencia; entre 0 y 60
     * @throws IllegalArgumentException si el valor está fuera de rango
     */
    public void setAnosExperiencia(int anosExperiencia) {
        if (anosExperiencia < 0 || anosExperiencia > 60) {
            throw new IllegalArgumentException("Anos de experiencia inválidos: " + anosExperiencia);
        }
        this.anosExperiencia = anosExperiencia;
    }

    /**
     * Determina la categoria del guía según sus años de experiencia.
     *
     * @return "Senior" si tiene 5 o más años, "Junior" en caso contrario
     */
    public String getCategoria() {
        return anosExperiencia >= 5 ? "Senior" : "Junior";
    }

    /**
     * @return el mensaje que confirma el alta del guía en el sistema
     */
    @Override
    public String registrar() {
        return "Guia registrado: " + getNombre() + " (" + getIdioma() + ", " + getCategoria() + ")";
    }

    /**
     * @return los datos completos del guía; delega en {@link #toString()}
     */
    @Override
    public String mostrarDatos() {
        return toString();
    }

    /**
     * @return la información del guia, anteponiendo su tipo al texto heredado
     */
    @Override
    public String toString() {
        return "Guia: " + super.toString()
                + " | Idioma: " + idioma
                + " | Experiencia: " + anosExperiencia + " anos (" + getCategoria() + ")";
    }
}