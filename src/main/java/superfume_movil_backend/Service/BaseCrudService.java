package superfume_movil_backend.Service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Implementación base que delega las operaciones CRUD en un {@link JpaRepository}.
 *
 * @param <T> tipo de entidad gestionada.
 */
public abstract class BaseCrudService<T> implements CrudService<T> {

    private final JpaRepository<T, Integer> repository;

    protected BaseCrudService(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    @Override
    public T crear(T entidad) {
        return repository.save(entidad);
    }

    @Override
    public List<T> obtenerTodos() {
        return repository.findAll();
    }

    @Override
    public T actualizar(int id, T entidadActualizada) {
        return repository.findById(id)
            .map(actual -> {
                BeanUtils.copyProperties(entidadActualizada, actual, "id");
                return repository.save(actual);
            })
            .orElse(null);
    }

    @Override
    public void eliminar(int id) {
        repository.deleteById(id);
    }

    @Override
    public T buscarPorId(int id) {
        return repository.findById(id).orElse(null);
    }
}

