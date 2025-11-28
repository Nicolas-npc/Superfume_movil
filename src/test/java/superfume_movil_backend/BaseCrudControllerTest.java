package superfume_movil_backend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import superfume_movil_backend.Controller.BaseCrudController;
import superfume_movil_backend.Service.CrudService;

class BaseCrudControllerTest {

    @Mock
    private CrudService<TestEntity> crudService;

    private TestController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new TestController(crudService);
    }

    @Test
    void listarDelegatesToService() {
        List<TestEntity> data = List.of(new TestEntity(1, "uno"));
        when(crudService.obtenerTodos()).thenReturn(data);

        List<TestEntity> result = controller.listar();

        assertEquals(data, result);
        verify(crudService, times(1)).obtenerTodos();
    }

    @Test
    void buscarPorIdReturnsEntity() {
        TestEntity entity = new TestEntity(2, "dos");
        when(crudService.buscarPorId(2)).thenReturn(entity);

        TestEntity result = controller.buscarPorId(2);

        assertEquals(entity, result);
    }

    @Test
    void buscarPorIdThrowsWhenMissing() {
        when(crudService.buscarPorId(9)).thenReturn(null);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> controller.buscarPorId(9));

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    @Test
    void crearDelegatesToService() {
        TestEntity entity = new TestEntity(3, "tres");
        when(crudService.crear(entity)).thenReturn(entity);

        TestEntity result = controller.crear(entity);

        assertEquals(entity, result);
        verify(crudService, times(1)).crear(entity);
    }

    @Test
    void actualizarReturnsUpdatedEntity() {
        TestEntity entity = new TestEntity(4, "cuatro");
        when(crudService.actualizar(4, entity)).thenReturn(entity);

        TestEntity result = controller.actualizar(4, entity);

        assertEquals(entity, result);
    }

    @Test
    void actualizarThrowsWhenMissing() {
        TestEntity entity = new TestEntity(5, "cinco");
        when(crudService.actualizar(5, entity)).thenReturn(null);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> controller.actualizar(5, entity));

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    @Test
    void eliminarDelegatesToService() {
        controller.eliminar(6);
        verify(crudService, times(1)).eliminar(6);
    }

    private static class TestController extends BaseCrudController<TestEntity> {
        protected TestController(CrudService<TestEntity> service) {
            super(service, "TestEntity");
        }
    }

    private static class TestEntity {
        private int id;
        private String nombre;

        TestEntity(int id, String nombre) {
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

