# 🎯 Evaluación Final Transversal – Desarrollo Orientado a Objetos I

## 👤 Autor del proyecto

- **Nombre completo:** Pablo Smith
- **Carrera:** Analista Programador
- **Sede:** Campus Online

---

## ▶️ Ejecución del programa

**Clase principal:** `app/Main.java`

Ejecuta esa clase para iniciar la aplicación. Al arrancar, el sistema carga
los datos desde los archivos de texto y muestra un menú de consola para
consultar y registrar entidades.

---

## 📘 Descripción general del sistema

Este proyecto corresponde a la Evaluación Final Transversal de la asignatura
*Desarrollo Orientado a Objetos I*. Es un sistema orientado a objetos
desarrollado en Java para la agencia de turismo Llanquihue Tour (Región de Los
Lagos), cuyo objetivo es modelar y gestionar las entidades de una agencia real,
aplicando los principios de encapsulamiento, composición, herencia,
polimorfismo e interfaces.

El sistema gestiona clientes, empleados (guías, choferes y administrativos),
proveedores externos (operadores de transporte y alojamientos), tours y
reservas. Los datos se cargan desde archivos de texto, se administran en
colecciones y se consultan mediante un menú de consola que permite, entre otras
cosas, registrar nuevas entidades, filtrar información y controlar el cupo de
los tours para evitar sobreventas.

---

## 🧩 Principios y conceptos aplicados

- **Encapsulamiento:** todos los atributos son privados y se acceden mediante
  getters y setters. La validación vive en los setters y los constructores
  delegan en ellos, de modo que ningún objeto pueda existir en estado inválido.
- **Composición:** una `Persona` se compone de un `Rut` y una `Direccion`, y una
  `Reserva` se compone de un `Cliente` y un `Tour`, formando composición en
  varios niveles.
- **Herencia:** jerarquía encabezada por la clase abstracta `Persona`, con
  clases intermedias abstractas (`Empleado`, `Proveedor`) y subclases concretas.
- **Polimorfismo:** las entidades se recorren a través de la interfaz común
  `Registrable`, y cada objeto responde con la versión de sus métodos
  correspondiente a su tipo real.
- **Interfaces:** `Registrable` define el contrato común (`registrar()` y
  `mostrarDatos()`) implementado por personas, tours y reservas.
- **Diferenciación de tipos:** se usa `instanceof` para operaciones que
  requieren distinguir el tipo real de las entidades (cálculo de nómina de
  empleados y conteo por tipo).
- **Manejo de excepciones:** validación mediante excepciones, incluyendo la
  excepción personalizada `RutInvalidoException`.
- **Colecciones:** uso de `ArrayList` para las listas de entidades y `HashMap`
  para relacionar reservas con clientes y tours durante la carga.

---

## 🗂️ Estructura general del proyecto

```
src/
├── app/      Clase Main, punto de entrada con el menú de consola
├── model/    Clases de dominio e interfaz Registrable
├── data/     GestorAgencia: colecciones y lógica de negocio
└── utils/    LectorArchivos y RutInvalidoException

resources/    Archivos .txt con los datos de las entidades
```

### Clases del paquete `model`

- **`Registrable`** (interfaz): contrato común con `registrar()` y `mostrarDatos()`.
- **`Rut`, `Direccion`**: componentes usados por composición.
- **`Persona`** (abstracta): superclase con nombre, RUT, dirección, correo y teléfono.
- **`Cliente`**: cliente de la agencia.
- **`Empleado`** (abstracta): sueldo y fecha de contratación.
- **`Guia`, `Chofer`, `Administrativo`**: subclases de `Empleado`.
- **`Proveedor`** (abstracta): empresa y servicio que presta.
- **`OperadorTransporte`, `ProveedorAlojamiento`**: subclases de `Proveedor`.
- **`Tour`**: tour ofrecido, con capacidad máxima para el control de cupo.
- **`Reserva`**: entidad central, relaciona un cliente con un tour.

---

## ⚙️ Instrucciones para clonar y ejecutar el proyecto

1. Clona el repositorio desde GitHub:

   ```
   git clone https://github.com/Psmithortiz/poo-llanquihue-tour-7.git
   ```

2. Abre el proyecto en IntelliJ IDEA.
3. Verifica que la carpeta `resources/` con los archivos `.txt` esté en la raíz
   del proyecto.
4. Ejecuta el archivo `Main.java` desde el paquete `app`.
5. Sigue las instrucciones del menú en la consola.

---

## 📋 Requisitos

- **Java:** JDK 8 o superior.
- **IDE recomendado:** IntelliJ IDEA.

---

**Repositorio GitHub:** https://github.com/Psmithortiz/poo-llanquihue-tour-7
**Fecha de entrega:** 19/07/2026

---

© Duoc UC | Escuela de Informática y Telecomunicaciones | Evaluación Final Transversal EFT