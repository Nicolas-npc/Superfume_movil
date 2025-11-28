package superfume_movil_backend.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import superfume_movil_backend.Service.CrudService;

/**
 * Controlador base que expone endpoints CRUD estándar.
 *
 * @param <T> tipo de entidad servida.
 */
public abstract class BaseCrudController<T> {

    private final CrudService<T> service;
    private final String nombreRecurso;

    protected BaseCrudController(CrudService<T> service, String nombreRecurso) {
        this.service = service;
        this.nombreRecurso = nombreRecurso;
    }

    @GetMapping
    public List<T> listar() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public T buscarPorId(@PathVariable int id) {
        T entidad = service.buscarPorId(id);
        if (entidad == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, nombreRecurso + " no encontrado");
        }
        return entidad;
    }

    @PostMapping
    public T crear(@RequestBody T entidad) {
        return service.crear(entidad);
    }

    @PutMapping("/{id}")
    public T actualizar(@PathVariable int id, @RequestBody T entidad) {
        T actualizada = service.actualizar(id, entidad);
        if (actualizada == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, nombreRecurso + " no encontrado");
        }
        return actualizada;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable int id) {
        service.eliminar(id);
    }
}

