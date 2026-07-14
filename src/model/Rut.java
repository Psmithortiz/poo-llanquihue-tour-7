package model;

import utils.RutInvalidoException;

/**
 * Encapsula y valida el RUT de una persona. La validación ocurre al construir
 * el objeto, garantizando que no pueda existir una instancia con un formato
 * inválido. Se usa como componente en relaciones de composición.
 *
 * @author Pablo Smith
 * @version 1.0
 */
public class Rut {

    private String numeroRut;

    /**
     * Crea un RUT a partir de un texto, normalizándolo y validándolo. Elimina
     * los puntos, recorta espacios y convierte a mayúsculas antes de comprobar
     * el formato.
     *
     * @param numero RUT en formato de texto (por ejemplo, "12.345.678-K")
     * @throws RutInvalidoException si el RUT no cumple con el formato válido
     */
    public Rut(String numero) throws RutInvalidoException {
        if (numero == null) {
            throw new RutInvalidoException("El RUT es obligatorio");
        }
        String rutLimpio = numero.replace(".", "").trim().toUpperCase();
        if (!validarFormato(rutLimpio)) {
            throw new RutInvalidoException("El RUT no tiene formato valido: " + numero);
        }
        this.numeroRut = rutLimpio;
    }

    /**
     * Verifica que el RUT cumpla con el formato esperado: 7 u 8 dígitos, un
     * guion y un dígito verificador (0-9 o K).
     *
     * @param rut RUT ya normalizado, sin puntos ni espacios
     * @return {@code true} si el formato es válido, {@code false} en caso contrario
     */
    private boolean validarFormato(String rut) {
        return rut.matches("[0-9]{7,8}-[0-9K]");
    }

    /**
     * Obtiene el RUT normalizado.
     *
     * @return RUT sin puntos y en mayúsculas
     */
    public String getNumeroRut() {
        return numeroRut;
    }

    /**
     * Retorna una representación legible del RUT.
     *
     * @return el RUT normalizado
     */
    @Override
    public String toString() {
        return numeroRut;
    }
}