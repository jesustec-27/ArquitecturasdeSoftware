package com.pagina.crud.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

/**
 * Entidad que representa la tabla de Calificaciones en la base de datos.
 * <p>
 * Contiene la información del alumno y su calificación, así como las reglas de validación
 * para asegurar la integridad de los datos antes de ser guardados.
 * </p>
 */
@Entity
public class Calificaciones {

    /**
     * Identificador único del registro (Clave Primaria).
     * Generado automáticamente por la base de datos (Auto-incremental).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del alumno.
     * No puede estar vacío según la validación {@link NotEmpty}.
     */
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;

    /**
     * Calificación numérica.
     * Debe estar entre 0 y 100 según las validaciones {@link Min} y {@link Max}.
     */
    @Min(value = 0, message = "La calificación no puede ser menor a 0")
    @Max(value = 100, message = "La calificación no puede ser mayor a 100")
    private double calificacion;

    /**
     * Constructor vacío requerido por JPA/Hibernate.
     */
    public Calificaciones() {
    }

    /**
     * Constructor para crear una nueva instancia con datos.
     *
     * @param nombre       El nombre del alumno.
     * @param calificacion La calificación obtenida.
     */
    public Calificaciones(String nombre, double calificacion) {
        this.nombre = nombre;
        this.calificacion = calificacion;
    }

    // --- Getters y Setters ---

    /**
     * Obtiene el ID del registro.
     * @return El identificador único.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del registro.
     * @param id El nuevo identificador.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del alumno.
     * @return El nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del alumno.
     * @param nombre El nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la calificación.
     * @return El valor numérico de la calificación.
     */
    public double getCalificacion() {
        return calificacion;
    }

    /**
     * Establece la calificación.
     * @param calificacion El nuevo valor numérico.
     */
    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }
}