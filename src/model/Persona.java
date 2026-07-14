package model;

/**
 * Representa a una persona vinculada a la agencia Llanquihue Tour. Es la
 * superclase abstracta de la jerarquía de personas y mantiene relaciones de
 * composición con {@link Rut} y {@link Direccion}.
 *
 * Implementa {@link Registrable}, de modo que toda persona queda obligada a
 * ofrecer un mensaje de registro y un resumen de sus datos. Al ser abstracta,
 * no implementa esos métodos: los hereda como abstractos y traslada la
 * obligación a las subclases concretas.
 *
 * @author Pablo Smith
 * @version 1.0
 */
public abstract class Persona implements Registrable {

    private String nombre;
    private Rut rut;
    private Direccion direccion;
    private String correo;
    private String telefono;

    /**
     * Crea una persona. La validación se delega en los setters, de modo que un
     * objeto nunca pueda existir en estado inválido.
     *
     * @param nombre    nombre de la persona; no puede estar vacío
     * @param rut       RUT de la persona; no puede ser nulo
     * @param direccion dirección de la persona; no puede ser nula
     * @param correo    correo de contacto; no puede estar vacío
     * @param telefono  teléfono de contacto; no puede estar vacío
     * @throws IllegalArgumentException si algún dato no cumple las validaciones
     */
    public Persona(String nombre, Rut rut, Direccion direccion, String correo, String telefono) {
        setNombre(nombre);
        setRut(rut);
        setDireccion(direccion);
        setCorreo(correo);
        setTelefono(telefono);
    }

    /**
     * Obtiene el nombre de la persona.
     *
     * @return nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la persona.
     *
     * @param nombre nombre no vacío
     * @throws IllegalArgumentException si el nombre es nulo o está vacío
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        this.nombre = nombre.trim();
    }

    /**
     * Obtiene el RUT de la persona.
     *
     * @return RUT de la persona
     */
    public Rut getRut() {
        return rut;
    }

    /**
     * Establece el RUT de la persona.
     *
     * @param rut RUT no nulo
     * @throws IllegalArgumentException si el RUT es nulo
     */
    public void setRut(Rut rut) {
        if (rut == null) {
            throw new IllegalArgumentException("El RUT es obligatorio");
        }
        this.rut = rut;
    }

    /**
     * Obtiene la dirección de la persona.
     *
     * @return dirección de la persona
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección de la persona.
     *
     * @param direccion dirección no nula
     * @throws IllegalArgumentException si la dirección es nula
     */
    public void setDireccion(Direccion direccion) {
        if (direccion == null) {
            throw new IllegalArgumentException("La direccion es obligatoria");
        }
        this.direccion = direccion;
    }

    /**
     * Obtiene el correo de contacto.
     *
     * @return correo de la persona
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo de contacto.
     *
     * @param correo correo no vacío
     * @throws IllegalArgumentException si el correo es nulo o está vacío
     */
    public void setCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio");
        }
        this.correo = correo.trim();
    }

    /**
     * Obtiene el teléfono de contacto.
     *
     * @return teléfono de la persona
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono de contacto.
     *
     * @param telefono teléfono no vacío
     * @throws IllegalArgumentException si el teléfono es nulo o está vacío
     */
    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El telefono es obligatorio");
        }
        this.telefono = telefono.trim();
    }

    /**
     * Retorna el texto común a toda persona. Las subclases lo reutilizan con
     * {@code super.toString()} y le anteponen su tipo más sus atributos propios.
     *
     * @return cadena con nombre, RUT, dirección, correo y teléfono
     */
    @Override
    public String toString() {
        return nombre + " | RUT: " + rut
                + " | Direccion: " + direccion
                + " | Correo: " + correo
                + " | Tel: " + telefono;
    }
}