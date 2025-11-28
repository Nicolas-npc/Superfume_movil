package superfume_movil_backend.Service;

import java.util.List;

/**
 * Contrato genérico para las operaciones CRUD básicas.
 *
 * @param <T> tipo de entidad gestionada por el servicio.
 */
public interface CrudService<T> {

    T crear(T entidad);

    List<T> obtenerTodos();

    T actualizar(int id, T entidadActualizada);

    void eliminar(int id);

    T buscarPorId(int id);
}

