package com.pagina.crud.Controlador;

import com.pagina.crud.Repositorio.CalificacionesRepositorio;
import com.pagina.crud.Modelo.Calificaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

/**
 * Controlador Web (MVC) que gestiona las peticiones HTTP relacionadas con las calificaciones.
 * <p>
 * Actúa como intermediario entre la vista (HTML Thymeleaf) y la capa de datos (Repositorio),
 * manejando la lógica de navegación, búsqueda, validación y persistencia.
 * </p>
 */
@Controller
public class CalificacionesControlador {

    @Autowired
    private CalificacionesRepositorio repositorio;

    /**
     * Maneja la petición GET a la página principal (/).
     * <p>
     * Si se proporciona una palabra clave, realiza una búsqueda filtrada.
     * De lo contrario, muestra todas las calificaciones existentes.
     * </p>
     *
     * @param modelo       Objeto {@link Model} para pasar datos a la vista.
     * @param palabraClave (Opcional) Texto para filtrar los resultados por nombre.
     * @return El nombre de la plantilla HTML "index" para renderizar.
     */
    @GetMapping("/")
    public String mostrarPaginaPrincipal(Model modelo, @Param("palabraClave") String palabraClave) {

        List<Calificaciones> listaCalificaciones;

        if (palabraClave != null && !palabraClave.isEmpty()) {
            listaCalificaciones = repositorio.findByNombreContaining(palabraClave);
        } else {
            listaCalificaciones = repositorio.findAll();
        }

        modelo.addAttribute("listaCal", listaCalificaciones);
        modelo.addAttribute("palabraClave", palabraClave);

        return "index";
    }

    /**
     * Muestra el formulario para crear una nueva calificación.
     *
     * @param modelo Objeto {@link Model} para pasar un objeto vacío a la vista.
     * @return El nombre de la plantilla HTML "nuevo".
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioDeNuevaCalificacion(Model modelo) {
        Calificaciones calificacionVacia = new Calificaciones();
        modelo.addAttribute("calif", calificacionVacia);
        return "nuevo";
    }

    /**
     * Muestra el formulario para editar una calificación existente.
     * <p>
     * Busca el registro en la base de datos por su ID.
     * <strong>Manejo de Excepciones:</strong> Si el ID no existe, se captura la excepción
     * y se redirige al usuario a la página principal para evitar errores.
     * </p>
     *
     * @param id     El ID de la calificación a editar (extraído de la URL).
     * @param modelo Objeto {@link Model} para pasar el objeto encontrado a la vista.
     * @return El nombre de la plantilla HTML "nuevo" o redirección a "/" si falla.
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo) {
        try {
            Calificaciones calificacionAEditar = repositorio.findById(id).get();
            modelo.addAttribute("calif", calificacionAEditar);
            return "nuevo";
        } catch (Exception e) {
            return "redirect:/";
        }
    }

    /**
     * Procesa el envío del formulario para guardar (crear o actualizar) una calificación.
     * <p>
     * Incluye validación de datos. Si hay errores, devuelve al formulario mostrando los fallos.
     * Si el ID es nulo, crea un nuevo registro. Si el ID existe, actualiza el registro.
     * </p>
     *
     * @param calificacion  El objeto {@link Calificaciones} con los datos del formulario.
     * @param bindingResult Objeto que captura los errores de validación si existen.
     * @return Redirección a la página principal si es exitoso, o vuelta a "nuevo" si hay errores.
     */
    @PostMapping("/guardar")
    public String guardarCalificacion(@Valid @ModelAttribute("calif") Calificaciones calificacion,
                                      BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "nuevo";
        }

        repositorio.save(calificacion);
        return "redirect:/";
    }

    /**
     * Elimina una calificación específica por su ID.
     *
     * @param id El ID de la calificación a borrar.
     * @return Redirección a la página principal.
     */
    @GetMapping("/borrar/{id}")
    public String borrarCalificacion(@PathVariable Long id) {
        repositorio.deleteById(id);
        return "redirect:/";
    }

    /**
     * Elimina múltiples calificaciones seleccionadas mediante checkboxes.
     *
     * @param ids Lista de IDs seleccionados enviados desde el formulario.
     * @return Redirección a la página principal.
     */
    @PostMapping("/borrar-seleccionados")
    public String borrarCalificacionesSeleccionadas(@RequestParam("ids") List<Long> ids) {
        repositorio.deleteAllById(ids);
        return "redirect:/";
    }

}