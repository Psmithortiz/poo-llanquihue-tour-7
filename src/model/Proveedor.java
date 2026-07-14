package model;

/**
 * Representa a un proveedor externo que presta servicios a la agencia. Es una
 * clase abstracta intermedia que agrupa los atributos comunes a todo proveedor:
 * la empresa a la que pertenece y el servicio que presta.
 * A diferencia de un {@link Empleado}, un proveedor no forma parte de la
 * agencia: se le contrata por servicio, no tiene sueldo.
 *
 * @author Pablo Smith
 * @version 1.0
 */
public abstract class Proveedor extends Persona {

    private String empresa;
    private String servicioQuePresta;

    /**
     * Crea un proveedor externo.
     *
     * @param nombre            nombre del contacto; no puede estar vacío
     * @param rut               RUT del proveedor; no puede ser nulo
     * @param direccion         dirección del proveedor; no puede ser nula
     * @param correo            correo de contacto; no puede estar vacío
     * @param telefono          teléfono de contacto; no puede estar vacío
     * @param empresa           empresa a la que pertenece; no puede estar vacío
     * @param servicioQuePresta servicio que presta; no puede estar vacío
     * @throws IllegalArgumentException si algún dato no cumple las validaciones
     */
    public Proveedor(String nombre, Rut rut, Direccion direccion, String correo,
                     String telefono, String empresa, String servicioQuePresta) {
        super(nombre, rut, direccion, correo, telefono);
        setEmpresa(empresa);
        setServicioQuePresta(servicioQuePresta);
    }

    /**
     * Obtiene la empresa del proveedor.
     *
     * @return empresa a la que pertenece
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Establece la empresa del proveedor.
     *
     * @param empresa empresa no vacía
     * @throws IllegalArgumentException si la empresa es nula o está vacía
     */
    public void setEmpresa(String empresa) {
        if (empresa == null || empresa.trim().isEmpty()) {
            throw new IllegalArgumentException("La empresa es obligatoria");
        }
        this.empresa = empresa.trim();
    }

    /**
     * Obtiene el servicio que presta el proveedor.
     *
     * @return servicio que presta
     */
    public String getServicioQuePresta() {
        return servicioQuePresta;
    }

    /**
     * Establece el servicio que presta el proveedor.
     *
     * @param servicioQuePresta servicio no vacío
     * @throws IllegalArgumentException si el servicio es nulo o está vacío
     */
    public void setServicioQuePresta(String servicioQuePresta) {
        if (servicioQuePresta == null || servicioQuePresta.trim().isEmpty()) {
            throw new IllegalArgumentException("El servicio que presta es obligatorio");
        }
        this.servicioQuePresta = servicioQuePresta.trim();
    }

    /**
     * @return el texto común a todo proveedor: los datos de la persona más su
     *         empresa y el servicio que presta
     */
    @Override
    public String toString() {
        return super.toString()
                + " | Empresa: " + empresa
                + " | Servicio: " + servicioQuePresta;
    }
}