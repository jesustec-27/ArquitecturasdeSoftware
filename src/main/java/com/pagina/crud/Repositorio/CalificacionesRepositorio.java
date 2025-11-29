package com.pagina.crud.Repositorio;

import com.pagina.crud.Modelo.Calificaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Interfaz de Repositorio para la entidad {@link Calificaciones}.
 * <p>
 * Extiende de {@link JpaRepository} para proporcionar operaciones CRUD estándar
 * (Crear, Leer, Actualizar, Borrar) sin necesidad de implementar código SQL manualmente.
 * </p>
 */
@Repository
public interface CalificacionesRepositorio extends JpaRepository<Calificaciones, Long> {

    /**
     * Busca y devuelve una lista de calificaciones cuyo nombre contenga la cadena proporcionada.
     * <p>
     * Spring Data JPA genera automáticamente la consulta SQL basada en el nombre del método:
     * {@code SELECT * FROM calificaciones WHERE nombre LIKE %palabraClave%}
     * </p>
     *
     * @param palabraClave El texto o fragmento de nombre a buscar.
     * @return Una lista de objetos {@link Calificaciones} que coinciden con la búsqueda.
     */
    List<Calificaciones> findByNombreContaining(String palabraClave);
}