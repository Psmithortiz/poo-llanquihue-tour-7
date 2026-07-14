package model;

/**
 * Contrato común de las entidades gestionables del sistema. Obliga a quien la
 * implemente a ofrecer un mensaje de registro y un resumen completo de sus
 * datos, de modo que puedan ser tratadas de forma uniforme en las colecciones
 * polimórficas del sistema.
 *
 * @author Pablo Smith
 * @version 1.0
 */
public interface Registrable {

    /**
     * Devuelve el mensaje que confirma el alta de la entidad en el sistema.
     *
     * @return mensaje breve de registro
     */
    String registrar();

    /**
     * Devuelve la información completa de la entidad, apta para mostrarse por
     * pantalla.
     *
     * @return los datos completos de la entidad
     */
    String mostrarDatos();
}