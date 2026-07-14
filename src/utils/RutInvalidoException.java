package utils;

/**
 * Excepción personalizada que se lanza cuando un RUT no cumple con el formato
 * válido esperado por el sistema.
 *
 * @author Pablo Smith
 * @version 1.0
 */
public class RutInvalidoException extends Exception {

    /**
     * Crea la excepción con un mensaje descriptivo del error.
     *
     * @param mensaje detalle del motivo por el que el RUT es inválido
     */
    public RutInvalidoException(String mensaje) {
        super(mensaje);
    }
}