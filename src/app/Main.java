package app;

import data.GestorAgencia;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Punto de entrada de la aplicacion de gestion de la agencia Llanquihue Tour.
 * Carga los datos desde archivos y ofrece un menu de consola para consultar y
 * registrar entidades.
 *
 * @author Pablo Smith
 * @version 1.0
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final String SEPARADOR = "=".repeat(55);

    /**
     * Arranca la aplicacion: carga los datos y ejecuta el bucle del menu.
     *
     * @param args argumentos de linea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        GestorAgencia gestor = new GestorAgencia();
        gestor.cargarDatos();
        System.out.println("Datos cargados correctamente.");

        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            String opcion = sc.nextLine().trim();
            switch (opcion) {
                case "1": verTodasLasEntidades(gestor); break;
                case "2": filtrarGuiasPorIdioma(gestor); break;
                case "3": verToursConCupo(gestor); break;
                case "4": registrarCliente(gestor); break;
                case "5": crearReserva(gestor); break;
                case "6": verReservasDeCliente(gestor); break;
                case "7": verNomina(gestor); break;
                case "0": continuar = false; break;
                default: System.out.println("Opcion no valida."); break;
            }
        }
        System.out.println("Sistema finalizado.");
    }

    /**
     * Muestra las opciones del menu principal.
     */
    private static void mostrarMenu() {
        System.out.println("\n" + SEPARADOR);
        System.out.println("        AGENCIA LLANQUIHUE TOUR - MENU PRINCIPAL");
        System.out.println(SEPARADOR);
        System.out.println("1. Ver todas las entidades registradas");
        System.out.println("2. Filtrar guias por idioma");
        System.out.println("3. Ver tours con cupo disponible");
        System.out.println("4. Registrar nuevo cliente");
        System.out.println("5. Crear reserva");
        System.out.println("6. Ver reservas de un cliente");
        System.out.println("7. Calcular nomina de empleados");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    /**
     * Lee un numero entero desde consola de forma segura, repitiendo la
     * peticion hasta recibir un valor valido.
     *
     * @param mensaje texto que se muestra al pedir el dato
     * @return el numero ingresado
     */
    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido.");
            }
        }
    }

    /**
     * Recorre la coleccion polimorfica y muestra los datos de cada entidad. Cada
     * objeto responde con la version de mostrarDatos() de su tipo real.
     *
     * @param gestor gestor con las entidades cargadas
     */
    private static void verTodasLasEntidades(GestorAgencia gestor) {
        System.out.println("\n--- ENTIDADES REGISTRADAS ---");
        for (Registrable r : gestor.getRegistrables()) {
            System.out.println(r.mostrarDatos());
        }
    }

    /**
     * Pide un idioma y muestra los guias que lo dominan.
     *
     * @param gestor gestor con las entidades cargadas
     */
    private static void filtrarGuiasPorIdioma(GestorAgencia gestor) {
        System.out.print("\nIngrese el idioma a buscar: ");
        String idioma = sc.nextLine().trim();
        ArrayList<Guia> encontrados = gestor.buscarGuiasPorIdioma(idioma);
        if (encontrados.isEmpty()) {
            System.out.println("No hay guias que dominen ese idioma.");
        } else {
            for (Guia g : encontrados) {
                System.out.println(g.mostrarDatos());
            }
        }
    }

    /**
     * Muestra los tours que aun tienen cupo disponible, indicando cuantos cupos
     * quedan en cada uno.
     *
     * @param gestor gestor con las entidades cargadas
     */
    private static void verToursConCupo(GestorAgencia gestor) {
        System.out.println("\n--- TOURS CON CUPO DISPONIBLE ---");
        ArrayList<Tour> disponibles = gestor.toursConCupo();
        if (disponibles.isEmpty()) {
            System.out.println("No hay tours con cupo disponible.");
            return;
        }
        for (Tour t : disponibles) {
            int libres = t.getCapacidadMaxima() - gestor.cuposOcupados(t);
            System.out.println(t.getNombre() + " -> cupos libres: " + libres
                    + "/" + t.getCapacidadMaxima());
        }
    }

    /**
     * Registra un nuevo cliente pidiendo sus datos por consola. Si el RUT tiene
     * formato invalido o algun dato no cumple las validaciones, informa el error
     * y no crea el cliente.
     *
     * @param gestor gestor donde se registra el cliente
     */
    private static void registrarCliente(GestorAgencia gestor) {
        System.out.println("\n--- REGISTRAR NUEVO CLIENTE ---");
        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine().trim();
            System.out.print("RUT (ej: 12345678-9): ");
            Rut rut = new Rut(sc.nextLine().trim());
            System.out.print("Calle: ");
            String calle = sc.nextLine().trim();
            int numero = leerEntero("Numero: ");
            System.out.print("Comuna: ");
            String comuna = sc.nextLine().trim();
            System.out.print("Region: ");
            String region = sc.nextLine().trim();
            System.out.print("Correo: ");
            String correo = sc.nextLine().trim();
            System.out.print("Telefono: ");
            String telefono = sc.nextLine().trim();

            Cliente cliente = new Cliente(nombre, rut,
                    new Direccion(calle, numero, comuna, region), correo, telefono);
            gestor.getClientes().add(cliente);
            gestor.getRegistrables().add(cliente);
            System.out.println(cliente.registrar());
        } catch (Exception e) {
            System.out.println("No se pudo registrar el cliente: " + e.getMessage());
        }
    }

    /**
     * Crea una reserva permitiendo elegir un cliente y un tour existentes de
     * listas numeradas, y validando el cupo antes de registrarla.
     *
     * @param gestor gestor con las entidades cargadas
     */
    private static void crearReserva(GestorAgencia gestor) {
        System.out.println("\n--- CREAR RESERVA ---");
        ArrayList<Cliente> clientes = gestor.getClientes();
        ArrayList<Tour> tours = gestor.getTours();

        if (clientes.isEmpty() || tours.isEmpty()) {
            System.out.println("Se necesitan clientes y tours registrados.");
            return;
        }

        System.out.println("Clientes:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + clientes.get(i).getNombre());
        }
        int iCliente = leerEntero("Elija cliente (numero): ") - 1;

        System.out.println("Tours:");
        for (int i = 0; i < tours.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + tours.get(i).getNombre());
        }
        int iTour = leerEntero("Elija tour (numero): ") - 1;

        if (iCliente < 0 || iCliente >= clientes.size() || iTour < 0 || iTour >= tours.size()) {
            System.out.println("Seleccion fuera de rango.");
            return;
        }

        try {
            System.out.print("Fecha (aaaa-mm-dd): ");
            LocalDate fecha = LocalDate.parse(sc.nextLine().trim());
            int personas = leerEntero("Cantidad de personas: ");

            Reserva reserva = new Reserva(clientes.get(iCliente), tours.get(iTour), fecha, personas);
            if (gestor.agregarReserva(reserva)) {
                System.out.println(reserva.registrar());
            }
        } catch (Exception e) {
            System.out.println("No se pudo crear la reserva: " + e.getMessage());
        }
    }

    /**
     * Muestra las reservas asociadas a un cliente elegido de una lista numerada.
     *
     * @param gestor gestor con las entidades cargadas
     */
    private static void verReservasDeCliente(GestorAgencia gestor) {
        System.out.println("\n--- RESERVAS DE UN CLIENTE ---");
        ArrayList<Cliente> clientes = gestor.getClientes();
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + clientes.get(i).getNombre());
        }
        int indice = leerEntero("Elija cliente (numero): ") - 1;
        if (indice < 0 || indice >= clientes.size()) {
            System.out.println("Seleccion fuera de rango.");
            return;
        }
        String rut = clientes.get(indice).getRut().getNumeroRut();
        ArrayList<Reserva> reservas = gestor.buscarReservasPorCliente(rut);
        if (reservas.isEmpty()) {
            System.out.println("Ese cliente no tiene reservas.");
        } else {
            for (Reserva r : reservas) {
                System.out.println(r.mostrarDatos());
            }
        }
    }

    /**
     * Muestra el total de la nomina de empleados de la agencia.
     *
     * @param gestor gestor con las entidades cargadas
     */
    private static void verNomina(GestorAgencia gestor) {
        int total = gestor.calcularNomina();
        System.out.println("\nNomina total de empleados: $" + String.format("%,d", total));
    }

}