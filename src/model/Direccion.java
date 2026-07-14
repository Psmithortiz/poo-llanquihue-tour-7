package model;

/**
 * Representa la dirección física de una persona o de un tour.
 * Se usa como componente en relaciones de composición.
 *
 * @author Pablo Smith
 * @version 1.0
 */
public class Direccion {

    private String calle;
    private int numeroCalle;
    private String comuna;
    private String region;

    /**
     * Crea una dirección. La validación se delega en los setters, de modo que
     * un objeto nunca pueda existir en estado inválido.
     *
     * @param calle       nombre de la calle; no puede estar vacío
     * @param numeroCalle número de la calle; debe ser mayor a 0
     * @param comuna      nombre de la comuna; no puede estar vacío
     * @param region      nombre de la región; no puede estar vacío
     * @throws IllegalArgumentException si algún dato no cumple las validaciones
     */
    public Direccion(String calle, int numeroCalle, String comuna, String region) {
        setCalle(calle);
        setNumeroCalle(numeroCalle);
        setComuna(comuna);
        setRegion(region);
    }

    /**
     * Obtiene el nombre de la calle.
     *
     * @return nombre de la calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Establece el nombre de la calle.
     *
     * @param calle nombre de la calle; no puede estar vacío
     * @throws IllegalArgumentException si la calle es nula o está vacía
     */
    public void setCalle(String calle) {
        if (calle == null || calle.trim().isEmpty()) {
            throw new IllegalArgumentException("La calle es obligatoria");
        }
        this.calle = calle.trim();
    }

    /**
     * Obtiene el número de la calle.
     *
     * @return número de la calle
     */
    public int getNumeroCalle() {
        return numeroCalle;
    }

    /**
     * Establece el número de la calle.
     *
     * @param numeroCalle número de la calle; debe ser mayor a 0
     * @throws IllegalArgumentException si el número es menor o igual a 0
     */
    public void setNumeroCalle(int numeroCalle) {
        if (numeroCalle <= 0) {
            throw new IllegalArgumentException("El numero de calle debe ser mayor a 0");
        }
        this.numeroCalle = numeroCalle;
    }

    /**
     * Obtiene el nombre de la comuna.
     *
     * @return nombre de la comuna
     */
    public String getComuna() {
        return comuna;
    }

    /**
     * Establece el nombre de la comuna.
     *
     * @param comuna nombre de la comuna; no puede estar vacío
     * @throws IllegalArgumentException si la comuna es nula o está vacía
     */
    public void setComuna(String comuna) {
        if (comuna == null || comuna.trim().isEmpty()) {
            throw new IllegalArgumentException("La comuna es obligatoria");
        }
        this.comuna = comuna.trim();
    }

    /**
     * Obtiene el nombre de la región.
     *
     * @return nombre de la región
     */
    public String getRegion() {
        return region;
    }

    /**
     * Establece el nombre de la región.
     *
     * @param region nombre de la región; no puede estar vacío
     * @throws IllegalArgumentException si la región es nula o está vacía
     */
    public void setRegion(String region) {
        if (region == null || region.trim().isEmpty()) {
            throw new IllegalArgumentException("La region es obligatoria");
        }
        this.region = region.trim();
    }

    /**
     * Retorna una representación legible de la dirección.
     *
     * @return cadena con calle, número, comuna y región
     */
    @Override
    public String toString() {
        return "Calle " + calle + " " + numeroCalle + ", " + comuna + ", " + region;
    }
}