package superfume_movil_backend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import superfume_movil_backend.Service.BaseCrudService;

@ExtendWith(MockitoExtension.class)
class BaseCrudServiceTest {

    @Mock
    private JpaRepository<DemoEntity, Integer> repository;

    private DemoService demoService;

    @BeforeEach
    void setUp() {
        demoService = new DemoService(repository);
    }

    @Test
    void crearDelegatesToRepository() {
        DemoEntity entity = new DemoEntity(1, "alpha");
        when(repository.save(entity)).thenReturn(entity);

        DemoEntity result = demoService.crear(entity);

        assertEquals(entity, result);
        verify(repository, times(1)).save(entity);
    }

    @Test
    void obtenerTodosReturnsRepositoryData() {
        List<DemoEntity> entities = List.of(new DemoEntity(1, "alpha"));
        when(repository.findAll()).thenReturn(entities);

        List<DemoEntity> result = demoService.obtenerTodos();

        assertEquals(entities, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    void actualizarCopiesPropertiesWithoutOverwritingId() {
        DemoEntity existing = new DemoEntity(1, "old");
        DemoEntity update = new DemoEntity(99, "new");
        when(repository.findById(1)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        DemoEntity result = demoService.actualizar(1, update);

        assertNotNull(result);
        assertEquals(1, result.getId(), "El id no debe cambiar");
        assertEquals("new", result.getNombre(), "El nombre debe actualizarse");
        verify(repository, times(1)).save(existing);
    }

    @Test
    void actualizarReturnsNullWhenEntityMissing() {
        when(repository.findById(5)).thenReturn(Optional.empty());

        DemoEntity result = demoService.actualizar(5, new DemoEntity());

        assertNull(result);
        verify(repository, never()).save(any());
    }

    @Test
    void eliminarDelegatesToRepository() {
        demoService.eliminar(10);
        verify(repository, times(1)).deleteById(10);
    }

    @Test
    void buscarPorIdReturnsEntity() {
        DemoEntity entity = new DemoEntity(2, "beta");
        when(repository.findById(2)).thenReturn(Optional.of(entity));

        DemoEntity result = demoService.buscarPorId(2);

        assertEquals(entity, result);
    }

    @Test
    void buscarPorIdReturnsNullWhenMissing() {
        when(repository.findById(3)).thenReturn(Optional.empty());

        DemoEntity result = demoService.buscarPorId(3);

        assertNull(result);
    }

    private static class DemoService extends BaseCrudService<DemoEntity> {
        protected DemoService(JpaRepository<DemoEntity, Integer> repository) {
            super(repository);
        }
    }

    private static class DemoEntity {
        private int id;
        private String nombre;

        DemoEntity() {
        }

        DemoEntity(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    }
}

