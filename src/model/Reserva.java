package model;

import java.time.LocalDate;

/**
 * Representa la reserva de un tour por parte de un cliente. Es la entidad
 * central del sistema: mantiene relaciones de composición con {@link Cliente} y
 * {@link Tour}, y registra la fecha y la cantidad de personas.
 *
 * El total a pagar no se almacena: se calcula a partir del precio del tour y la
 * cantidad de personas, de modo que no pueda quedar desactualizado.
 *
 * @author Pablo Smith
 * @version 1.0
 */
public class Reserva implements Registrable {

    private Cliente cliente;
    private Tour tour;
    private LocalDate fecha;
    private int cantidadPersonas;

    /**
     * Crea una reserva. La validación se delega en los setters, de modo que un
     * objeto nunca pueda existir en estado inválido.
     *
     * @param cliente          cliente que reserva; no puede ser nulo
     * @param tour             tour reservado; no puede ser nulo
     * @param fecha            fecha del tour; no puede ser anterior a hoy
     * @param cantidadPersonas cantidad de personas; debe ser mayor a 0
     * @throws IllegalArgumentException si algún dato no cumple las validaciones
     */
    public Reserva(Cliente cliente, Tour tour, LocalDate fecha, int cantidadPersonas) {
        setCliente(cliente);
        setTour(tour);
        setFecha(fecha);
        setCantidadPersonas(cantidadPersonas);
    }

    /**
     * Obtiene el cliente de la reserva.
     *
     * @return cliente que realizó la reserva
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Establece el cliente de la reserva.
     *
     * @param cliente cliente no nulo
     * @throws IllegalArgumentException si el cliente es nulo
     */
    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente es obligatorio");
        }
        this.cliente = cliente;
    }

    /**
     * Obtiene el tour reservado.
     *
     * @return tour de la reserva
     */
    public Tour getTour() {
        return tour;
    }

    /**
     * Establece el tour de la reserva.
     *
     * @param tour tour no nulo
     * @throws IllegalArgumentException si el tour es nulo
     */
    public void setTour(Tour tour) {
        if (tour == null) {
            throw new IllegalArgumentException("El tour es obligatorio");
        }
        this.tour = tour;
    }

    /**
     * Obtiene la fecha del tour reservado.
     *
     * @return fecha de la reserva
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la reserva, validando que no sea una fecha pasada:
     * no tiene sentido reservar un tour que ya ocurrió.
     *
     * @param fecha fecha del tour; no puede ser anterior a hoy
     * @throws IllegalArgumentException si la fecha es nula o pasada
     */
    public void setFecha(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de la reserva es obligatoria");
        }
        if (fecha.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de la reserva no puede ser pasada: " + fecha);
        }
        this.fecha = fecha;
    }

    /**
     * Obtiene la cantidad de personas de la reserva.
     *
     * @return cantidad de personas
     */
    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    /**
     * Establece la cantidad de personas de la reserva.
     *
     * @param cantidadPersonas cantidad; debe ser mayor a 0
     * @throws IllegalArgumentException si la cantidad es menor o igual a 0
     */
    public void setCantidadPersonas(int cantidadPersonas) {
        if (cantidadPersonas <= 0) {
            throw new IllegalArgumentException("La cantidad de personas debe ser mayor a 0");
        }
        this.cantidadPersonas = cantidadPersonas;
    }

    /**
     * Calcula el total a pagar por la reserva, multiplicando el precio por
     * persona del tour por la cantidad de personas. Es un dato derivado: se
     * calcula en el momento y no se almacena, de modo que siempre refleje el
     * estado actual de la reserva.
     *
     * @return total a pagar en pesos chilenos
     */
    public int calcularTotal() {
        return tour.getPrecioPorPersona() * cantidadPersonas;
    }

    /**
     * @return el mensaje que confirma el alta de la reserva en el sistema
     */
    @Override
    public String registrar() {
        return "Reserva registrada: " + cliente.getNombre() + " -> " + tour.getNombre()
                + " (" + fecha + ", " + cantidadPersonas + " personas)";
    }

    /**
     * @return los datos completos de la reserva; delega en {@link #toString()}
     */
    @Override
    public String mostrarDatos() {
        return toString();
    }

    /**
     * @return la información de la reserva: cliente, tour, fecha, personas y total
     */
    @Override
    public String toString() {
        return "Reserva: " + cliente.getNombre() + " (" + cliente.getRut() + ")"
                + " | Tour: " + tour.getNombre()
                + " | Fecha: " + fecha
                + " | Personas: " + cantidadPersonas
                + " | Total: $" + String.format("%,d", calcularTotal());
    }
}