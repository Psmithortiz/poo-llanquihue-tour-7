package model;

/**
 * Representa un tour ofrecido por la agencia Llanquihue Tour. Mantiene una
 * relación de composición con {@link Direccion} e implementa {@link Registrable}.
 *
 * @author Pablo Smith
 * @version 1.0
 */
public class Tour implements Registrable {

    private String nombre;
    private Direccion direccion;
    private int precioPorPersona;
    private int capacidadMaxima;

    /**
     * Crea un tour. La validación se delega en los setters, de modo que un
     * objeto nunca pueda existir en estado inválido.
     *
     * @param nombre           nombre del tour; no puede estar vacío
     * @param direccion        dirección donde se realiza; no puede ser nula
     * @param precioPorPersona precio por persona en pesos; no puede ser negativo
     * @param capacidadMaxima  capacidad máxima de personas; debe ser mayor a 0
     * @throws IllegalArgumentException si algún dato no cumple las validaciones
     */
    public Tour(String nombre, Direccion direccion, int precioPorPersona, int capacidadMaxima) {
        setNombre(nombre);
        setDireccion(direccion);
        setPrecioPorPersona(precioPorPersona);
        setCapacidadMaxima(capacidadMaxima);
    }

    /**
     * Obtiene el nombre del tour.
     *
     * @return nombre del tour
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del tour.
     *
     * @param nombre nombre no vacío
     * @throws IllegalArgumentException si el nombre es nulo o está vacío
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del tour es obligatorio");
        }
        this.nombre = nombre.trim();
    }

    /**
     * Obtiene la dirección del tour.
     *
     * @return dirección donde se realiza el tour
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del tour.
     *
     * @param direccion dirección no nula
     * @throws IllegalArgumentException si la dirección es nula
     */
    public void setDireccion(Direccion direccion) {
        if (direccion == null) {
            throw new IllegalArgumentException("La direccion del tour es obligatoria");
        }
        this.direccion = direccion;
    }

    /**
     * Obtiene el precio por persona.
     *
     * @return precio por persona en pesos chilenos
     */
    public int getPrecioPorPersona() {
        return precioPorPersona;
    }

    /**
     * Establece el precio por persona.
     *
     * @param precioPorPersona precio en pesos; no puede ser negativo
     * @throws IllegalArgumentException si el precio es negativo
     */
    public void setPrecioPorPersona(int precioPorPersona) {
        if (precioPorPersona < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo: " + precioPorPersona);
        }
        this.precioPorPersona = precioPorPersona;
    }

    /**
     * Obtiene la capacidad máxima del tour.
     *
     * @return capacidad máxima de personas
     */
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    /**
     * Establece la capacidad máxima del tour.
     *
     * @param capacidadMaxima capacidad; debe ser mayor a 0
     * @throws IllegalArgumentException si la capacidad es menor o igual a 0
     */
    public void setCapacidadMaxima(int capacidadMaxima) {
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad maxima debe ser mayor a 0");
        }
        this.capacidadMaxima = capacidadMaxima;
    }

    /**
     * @return el mensaje que confirma el alta del tour en el sistema
     */
    @Override
    public String registrar() {
        return "Tour registrado: " + nombre + " (capacidad " + capacidadMaxima + ")";
    }

    /**
     * @return los datos completos del tour; delega en {@link #toString()}
     */
    @Override
    public String mostrarDatos() {
        return toString();
    }

    /**
     * @return la información del tour: nombre, ubicación, precio y capacidad
     */
    @Override
    public String toString() {
        return "Tour: " + nombre
                + " | Ubicacion: " + direccion
                + " | Precio: $" + String.format("%,d", precioPorPersona) + " por persona"
                + " | Capacidad: " + capacidadMaxima;
    }
}