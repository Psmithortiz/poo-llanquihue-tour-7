package utils;

import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Utilidad encargada de leer los archivos de texto de datos y transformarlos
 * en objetos del modelo. Cada metodo lee un archivo, separa los campos de cada
 * linea y construye los objetos correspondientes, ignorando las lineas con
 * datos invalidos para no detener la carga completa.
 *
 * @author Pablo Smith
 * @version 1.0
 */
public class LectorArchivos {

    /**
     * Construye una direccion a partir de los campos comunes de una linea de
     * persona (posiciones 2 a 5). Centraliza el armado de la direccion para no
     * repetirlo en cada metodo de lectura de personas.
     *
     * @param partes campos de la linea ya separados
     * @return la direccion construida
     */
    private Direccion construirDireccion(String[] partes) {
        return new Direccion(partes[2], Integer.parseInt(partes[3]), partes[4], partes[5]);
    }

    /**
     * Lee el archivo de clientes y construye los objetos {@link Cliente}.
     *
     * @return lista de clientes cargados; vacia si el archivo no se puede leer
     */
    public ArrayList<Cliente> leerClientes() {
        ArrayList<Cliente> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/clientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    String[] p = linea.split(";");
                    Cliente cliente = new Cliente(
                            p[0], new Rut(p[1]), construirDireccion(p), p[6], p[7]);
                    lista.add(cliente);
                } catch (RutInvalidoException | RuntimeException e) {
                    System.out.println("Linea ignorada en clientes: " + linea + " -> " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer clientes.txt: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Lee el archivo de guias y construye los objetos {@link Guia}.
     *
     * @return lista de guias cargados; vacia si el archivo no se puede leer
     */
    public ArrayList<Guia> leerGuias() {
        ArrayList<Guia> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/guias.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    String[] p = linea.split(";");
                    Guia guia = new Guia(
                            p[0], new Rut(p[1]), construirDireccion(p), p[6], p[7],
                            Integer.parseInt(p[8]),        // sueldo
                            LocalDate.parse(p[9]),         // fecha
                            p[10],                         // idioma
                            Integer.parseInt(p[11]));      // experiencia
                    lista.add(guia);
                } catch (RutInvalidoException | RuntimeException e) {
                    System.out.println("Linea ignorada en guias: " + linea + " -> " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer guias.txt: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Lee el archivo de choferes y construye los objetos {@link Chofer}.
     *
     * @return lista de choferes cargados; vacia si el archivo no se puede leer
     */
    public ArrayList<Chofer> leerChoferes() {
        ArrayList<Chofer> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/choferes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    String[] p = linea.split(";");
                    Chofer chofer = new Chofer(
                            p[0], new Rut(p[1]), construirDireccion(p), p[6], p[7],
                            Integer.parseInt(p[8]),        // sueldo
                            LocalDate.parse(p[9]),         // fecha de contratacion
                            p[10]);                        // tipoLicencia
                    lista.add(chofer);
                } catch (RutInvalidoException | RuntimeException e) {
                    System.out.println("Linea ignorada en choferes: " + linea + " -> " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer choferes.txt: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Lee el archivo de administrativos y construye los objetos {@link Administrativo}.
     *
     * @return lista de administrativos cargados; vacia si el archivo no se puede leer
     */
    public ArrayList<Administrativo> leerAdministrativos() {
        ArrayList<Administrativo> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/administrativos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    String[] p = linea.split(";");
                    Administrativo admin = new Administrativo(
                            p[0], new Rut(p[1]), construirDireccion(p), p[6], p[7],
                            Integer.parseInt(p[8]),        // sueldo
                            LocalDate.parse(p[9]),         // fecha de contratacion
                            p[10]);                        // area
                    lista.add(admin);
                } catch (RutInvalidoException | RuntimeException e) {
                    System.out.println("Linea ignorada en administrativos: " + linea + " -> " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer administrativos.txt: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Lee el archivo de operadores y construye los objetos {@link OperadorTransporte}.
     *
     * @return lista de operadores cargados; vacia si el archivo no se puede leer
     */
    public ArrayList<OperadorTransporte> leerOperadores() {
        ArrayList<OperadorTransporte> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/operadores.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    String[] p = linea.split(";");
                    OperadorTransporte operador = new OperadorTransporte(
                            p[0], new Rut(p[1]), construirDireccion(p), p[6], p[7],
                            p[8],                          // empresa
                            p[9],                          // servicio
                            Integer.parseInt(p[10]),       // capacidad
                            p[11]);                        // tipoVehiculo
                    lista.add(operador);
                } catch (RutInvalidoException | RuntimeException e) {
                    System.out.println("Linea ignorada en operadores: " + linea + " -> " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer operadores.txt: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Lee el archivo de alojamientos y construye los objetos {@link ProveedorAlojamiento}.
     *
     * @return lista de alojamientos cargados; vacia si el archivo no se puede leer
     */
    public ArrayList<ProveedorAlojamiento> leerAlojamientos() {
        ArrayList<ProveedorAlojamiento> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/alojamientos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    String[] p = linea.split(";");
                    ProveedorAlojamiento alojamiento = new ProveedorAlojamiento(
                            p[0], new Rut(p[1]), construirDireccion(p), p[6], p[7],
                            p[8],                          // empresa
                            p[9],                          // servicio
                            Integer.parseInt(p[10]));      // habitaciones
                    lista.add(alojamiento);
                } catch (RutInvalidoException | RuntimeException e) {
                    System.out.println("Linea ignorada en alojamientos: " + linea + " -> " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer alojamientos.txt: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Lee el archivo de tours y construye los objetos {@link Tour}.
     *
     * @return lista de tours cargados; vacia si el archivo no se puede leer
     */
    public ArrayList<Tour> leerTours() {
        ArrayList<Tour> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/tours.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    String[] p = linea.split(";");
                    Direccion dir = new Direccion(
                            p[1], Integer.parseInt(p[2]), p[3], p[4]);
                    Tour tour = new Tour(
                            p[0], dir,
                            Integer.parseInt(p[5]),        // precio
                            Integer.parseInt(p[6]));       // capacidad
                    lista.add(tour);
                } catch (RuntimeException e) {
                    System.out.println("Linea ignorada en tours: " + linea + " -> " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer tours.txt: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Lee el archivo de reservas y construye los objetos {@link Reserva}. A
     * diferencia de los demas, este metodo necesita los clientes y tours ya
     * cargados: cada linea referencia un cliente por su RUT y un tour por su
     * nombre, que se buscan en los mapas recibidos. Si la referencia no existe,
     * la linea se ignora.
     *
     * @param mapaClientes clientes ya cargados, indexados por RUT
     * @param mapaTours    tours ya cargados, indexados por nombre
     * @return lista de reservas cargadas; vacia si el archivo no se puede leer
     */
    public ArrayList<Reserva> leerReservas(HashMap<String, Cliente> mapaClientes,
                                           HashMap<String, Tour> mapaTours) {
        ArrayList<Reserva> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/reservas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    String[] p = linea.split(";");
                    Cliente cliente = mapaClientes.get(p[0].trim());
                    Tour tour = mapaTours.get(p[1].trim());

                    if (cliente == null) {
                        System.out.println("Linea ignorada en reservas: cliente no encontrado -> " + p[0]);
                        continue;
                    }
                    if (tour == null) {
                        System.out.println("Linea ignorada en reservas: tour no encontrado -> " + p[1]);
                        continue;
                    }

                    Reserva reserva = new Reserva(
                            cliente, tour,
                            LocalDate.parse(p[2]),
                            Integer.parseInt(p[3]));
                    lista.add(reserva);
                } catch (RuntimeException e) {
                    System.out.println("Linea ignorada en reservas: " + linea + " -> " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer reservas.txt: " + e.getMessage());
        }
        return lista;
    }
}