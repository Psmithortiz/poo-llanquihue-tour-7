package data;

import model.*;
import utils.LectorArchivos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Gestiona todas las entidades del sistema de la agencia Llanquihue Tour.
 * Mantiene las colecciones tipadas para las operaciones propias de cada tipo y
 * una coleccion polimorfica de {@link Registrable} para las operaciones que
 * cruzan tipos. Concentra la carga de datos y la logica de negocio.
 *
 * @author Pablo Smith
 * @version 1.0
 */
public class GestorAgencia {

    private final ArrayList<Cliente> clientes;
    private final ArrayList<Guia> guias;
    private final ArrayList<Chofer> choferes;
    private final ArrayList<Administrativo> administrativos;
    private final ArrayList<OperadorTransporte> operadores;
    private final ArrayList<ProveedorAlojamiento> alojamientos;
    private final ArrayList<Tour> tours;
    private final ArrayList<Reserva> reservas;

    /**
     * Coleccion polimorfica que reune todas las entidades registrables.
     */
    private final ArrayList<Registrable> registrables;

    private final LectorArchivos lector;

    /**
     * Crea el gestor e inicializa todas las colecciones vacias.
     */
    public GestorAgencia() {
        // Se crean todas las arraylist + inicio de lector de archivos
        clientes = new ArrayList<>();
        guias = new ArrayList<>();
        choferes = new ArrayList<>();
        administrativos = new ArrayList<>();
        operadores = new ArrayList<>();
        alojamientos = new ArrayList<>();
        tours = new ArrayList<>();
        reservas = new ArrayList<>();
        registrables = new ArrayList<>();
        lector = new LectorArchivos();
    }

    /**
     * Carga todas las entidades desde los archivos de texto. El orden importa:
     * los clientes y tours deben cargarse antes que las reservas, ya que cada
     * reserva referencia a un cliente y a un tour existentes. Cada entidad
     * cargada se agrega tambien a la coleccion polimorfica.
     */
    public void cargarDatos() {
        // Se cargan todas las arraylist
        clientes.addAll(lector.leerClientes());
        guias.addAll(lector.leerGuias());
        choferes.addAll(lector.leerChoferes());
        administrativos.addAll(lector.leerAdministrativos());
        operadores.addAll(lector.leerOperadores());
        alojamientos.addAll(lector.leerAlojamientos());
        tours.addAll(lector.leerTours());

        // Las reservas necesitan clientes y tours ya cargados, indexados por su clave
        HashMap<String, Cliente> mapaClientes = new HashMap<>();
        for (Cliente c : clientes) {
            mapaClientes.put(c.getRut().getNumeroRut(), c);
        }
        HashMap<String, Tour> mapaTours = new HashMap<>();
        for (Tour t : tours) {
            mapaTours.put(t.getNombre(), t);
        }

        for (Reserva reserva : lector.leerReservas(mapaClientes, mapaTours)) {
            agregarReserva(reserva);
        }

        // Todas las entidades se reunen en la coleccion polimorfica
        registrables.addAll(clientes);
        registrables.addAll(guias);
        registrables.addAll(choferes);
        registrables.addAll(administrativos);
        registrables.addAll(operadores);
        registrables.addAll(alojamientos);
        registrables.addAll(tours);
        // NOTA: las reservas NO se agregan aqui: agregarReserva() ya las agrego arriba
    }

    // getters de las colecciones
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Guia> getGuias() {
        return guias;
    }

    public ArrayList<Tour> getTours() {
        return tours;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public ArrayList<Registrable> getRegistrables() {
        return registrables;
    }

    /**
     * Calcula cuantos cupos estan ocupados en un tour, sumando las personas de
     * todas las reservas asociadas a ese tour. Recorre la coleccion completa de
     * reservas para no dejar fuera ninguna.
     *
     * @param tour tour a consultar
     * @return numero de personas ya reservadas para ese tour
     */
    public int cuposOcupados(Tour tour) {
        int ocupados = 0;
        for (Reserva reserva : reservas) {
            if (reserva.getTour().equals(tour)) {
                ocupados += reserva.getCantidadPersonas();
            }
        }
        return ocupados;
    }

    /**
     * Determina si un tour tiene cupo suficiente para una cantidad adicional de
     * personas, comparando los cupos ya ocupados mas los solicitados contra la
     * capacidad maxima del tour.
     *
     * @param tour           tour a consultar
     * @param personasNuevas cantidad de personas que se desea reservar
     * @return {@code true} si hay cupo suficiente, {@code false} en caso contrario
     */
    public boolean hayCupo(Tour tour, int personasNuevas) {
        return cuposOcupados(tour) + personasNuevas <= tour.getCapacidadMaxima();
    }

    /**
     * Intenta registrar una nueva reserva, validando primero que el tour tenga
     * cupo disponible. Si no hay cupo, la reserva no se agrega y se informa el
     * motivo.
     *
     * @param reserva reserva a registrar
     * @return {@code true} si la reserva se agrego; {@code false} si no habia cupo
     */
    public boolean agregarReserva(Reserva reserva) {
        Tour tour = reserva.getTour();
        if (!hayCupo(tour, reserva.getCantidadPersonas())) {
            System.out.println("Reserva rechazada: no hay cupo en " + tour.getNombre()
                    + " (ocupados " + cuposOcupados(tour) + "/" + tour.getCapacidadMaxima() + ")");
            return false;
        }
        reservas.add(reserva);
        registrables.add(reserva);
        return true;
    }

    /**
     * Filtra los guias que dominan un idioma determinado.
     *
     * @param idioma idioma a buscar
     * @return lista de guias que dominan ese idioma
     */
    public ArrayList<Guia> buscarGuiasPorIdioma(String idioma) {
        ArrayList<Guia> resultado = new ArrayList<>();
        for (Guia guia : guias) {
            if (guia.getIdioma().equalsIgnoreCase(idioma)) {
                resultado.add(guia);
            }
        }
        return resultado;
    }

    /**
     * Busca todas las reservas asociadas a un cliente, identificado por su RUT.
     *
     * @param rutCliente RUT normalizado del cliente
     * @return lista de reservas de ese cliente
     */
    public ArrayList<Reserva> buscarReservasPorCliente(String rutCliente) {
        ArrayList<Reserva> resultado = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getCliente().getRut().getNumeroRut().equals(rutCliente)) {
                resultado.add(reserva);
            }
        }
        return resultado;
    }

    /**
     * Lista los tours que aun tienen cupo disponible.
     *
     * @return lista de tours con al menos un cupo libre
     */
    public ArrayList<Tour> toursConCupo() {
        ArrayList<Tour> resultado = new ArrayList<>();
        for (Tour tour : tours) {
            if (cuposOcupados(tour) < tour.getCapacidadMaxima()) {
                resultado.add(tour);
            }
        }
        return resultado;
    }

    /**
     * Calcula el total de sueldos que la agencia paga a sus empleados. Recorre
     * la coleccion polimorfica y, mediante {@code instanceof}, identifica cuales
     * entidades son empleados para acceder a su sueldo, un dato que solo la
     * clase {@link Empleado} posee.
     *
     * @return suma de los sueldos de todos los empleados
     */
    public int calcularNomina() {
        int total = 0;
        for (Registrable r : registrables) {
            if (r instanceof Empleado) {
                Empleado empleado = (Empleado) r;
                total += empleado.getSueldo();
            }
        }
        return total;
    }

    /**
     * Recorre la coleccion polimorfica y cuenta cuantas entidades hay de cada
     * tipo principal de persona (clientes, empleados y proveedores), usando
     * {@code instanceof} para diferenciar el tipo real de cada objeto.
     *
     * @return texto con el conteo de entidades por tipo
     */
    public String contarPorTipo() {
        int clientesCount = 0;
        int empleadosCount = 0;
        int proveedoresCount = 0;

        for (Registrable r : registrables) {
            if (r instanceof Cliente) {
                clientesCount++;
            } else if (r instanceof Empleado) {
                empleadosCount++;
            } else if (r instanceof Proveedor) {
                proveedoresCount++;
            }
        }

        return "Clientes: " + clientesCount
                + " | Empleados: " + empleadosCount
                + " | Proveedores: " + proveedoresCount;
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
        registrables.add(cliente);
    }


}