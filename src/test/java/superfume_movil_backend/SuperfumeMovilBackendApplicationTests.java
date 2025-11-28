package superfume_movil_backend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import superfume_movil_backend.Model.CarritoModel;
import superfume_movil_backend.Model.EmpleadoModel;
import superfume_movil_backend.Model.PerfumeModel;
import superfume_movil_backend.Model.UsuarioModel;
import superfume_movil_backend.Repository.CarritoRepository;
import superfume_movil_backend.Repository.EmpleadoRepository;
import superfume_movil_backend.Repository.PerfumeRepository;
import superfume_movil_backend.Repository.UsuarioRepository;
import superfume_movil_backend.Service.CarritoService;
import superfume_movil_backend.Service.EmpleadoService;
import superfume_movil_backend.Service.PerfumeService;
import superfume_movil_backend.Service.UsuarioService;

class SuperfumeMovilBackendApplicationTests {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @Mock
    private PerfumeRepository perfumeRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private CarritoRepository carritoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    @InjectMocks
    private PerfumeService perfumeService;

    @InjectMocks
    private UsuarioService usuarioService;

    @InjectMocks
    private CarritoService carritoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /*-------------------------------PERFUMES------------------------------------------*/
    @Test
    void testObtenerTodosPerfumes() {
        List<PerfumeModel> perfumes = new ArrayList<>();
        perfumes.add(new PerfumeModel());
        when(perfumeRepository.findAll()).thenReturn(perfumes);

        List<PerfumeModel> resultado = perfumeService.obtenerTodos();

        assertEquals(perfumes, resultado);
        verify(perfumeRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPerfumePorId() {
        PerfumeModel perfume = new PerfumeModel();
        perfume.setId(1);
        perfume.setNombre("Aqua");
        when(perfumeRepository.findById(1)).thenReturn(Optional.of(perfume));

        PerfumeModel resultado = perfumeService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("Aqua", resultado.getNombre());
        verify(perfumeRepository, times(1)).findById(1);
    }

    @Test
    void testObtenerPerfumePorIdNoExiste() {
        when(perfumeRepository.findById(99)).thenReturn(Optional.empty());

        PerfumeModel resultado = perfumeService.buscarPorId(99);

        assertNull(resultado);
        verify(perfumeRepository, times(1)).findById(99);
    }

    @Test
    void testCrearPerfume() {
        PerfumeModel perfume = new PerfumeModel();
        perfume.setId(2);
        perfume.setNombre("Blue");
        when(perfumeRepository.save(perfume)).thenReturn(perfume);

        PerfumeModel resultado = perfumeService.crear(perfume);

        assertNotNull(resultado);
        assertEquals("Blue", resultado.getNombre());
        verify(perfumeRepository, times(1)).save(perfume);
    }

    @Test
    void testActualizarPerfume() {
        int id = 3;
        PerfumeModel existente = new PerfumeModel();
        existente.setId(id);
        existente.setNombre("Old");
        PerfumeModel nuevo = new PerfumeModel();
        nuevo.setId(id);
        nuevo.setNombre("New");
        when(perfumeRepository.findById(id)).thenReturn(Optional.of(existente));
        when(perfumeRepository.save(any(PerfumeModel.class))).thenReturn(nuevo);

        PerfumeModel resultado = perfumeService.actualizar(id, nuevo);

        assertNotNull(resultado);
        assertEquals("New", resultado.getNombre());
        verify(perfumeRepository, times(1)).findById(id);
        verify(perfumeRepository, times(1)).save(any(PerfumeModel.class));
    }

    @Test
    void testActualizarPerfumeNoExiste() {
        int id = 99;
        PerfumeModel nuevo = new PerfumeModel();
        nuevo.setId(id);
        nuevo.setNombre("No Existe");
        when(perfumeRepository.findById(id)).thenReturn(Optional.empty());

        PerfumeModel resultado = perfumeService.actualizar(id, nuevo);

        assertNull(resultado);
        verify(perfumeRepository, times(1)).findById(id);
        verify(perfumeRepository, never()).save(any(PerfumeModel.class));
    }

    @Test
    void testEliminarPerfume() {
        int id = 4;
        doNothing().when(perfumeRepository).deleteById(id);

        perfumeService.eliminar(id);

        verify(perfumeRepository, times(1)).deleteById(id);
    }

    /*-----------------------Usuarios----------------------------------------------*/

    @Test
    void testObtenerTodosUsuarios() {
        List<UsuarioModel> usuarios = new ArrayList<>();
        usuarios.add(new UsuarioModel());
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<UsuarioModel> resultado = usuarioService.obtenerTodos();

        assertEquals(usuarios, resultado);
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void testObtenerUsuarioPorId() {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setId(1);
        usuario.setNombre("Carlos");
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        UsuarioModel resultado = usuarioService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("Carlos", resultado.getNombre());
        verify(usuarioRepository, times(1)).findById(1);
    }

    @Test
    void testObtenerUsuarioPorIdNoExiste() {
        when(usuarioRepository.findById(99)).thenReturn(Optional.empty());

        UsuarioModel resultado = usuarioService.buscarPorId(99);

        assertNull(resultado);
        verify(usuarioRepository, times(1)).findById(99);
    }

    @Test
    void testCrearUsuario() {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setId(2);
        usuario.setNombre("Lucia");
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        UsuarioModel resultado = usuarioService.crear(usuario);

        assertNotNull(resultado);
        assertEquals("Lucia", resultado.getNombre());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testActualizarUsuario() {
        int id = 3;
        UsuarioModel existente = new UsuarioModel();
        existente.setId(id);
        existente.setNombre("Mario");
        UsuarioModel nuevo = new UsuarioModel();
        nuevo.setId(id);
        nuevo.setNombre("Mario Actualizado");
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(existente));
        when(usuarioRepository.save(any(UsuarioModel.class))).thenReturn(nuevo);

        UsuarioModel resultado = usuarioService.actualizar(id, nuevo);

        assertNotNull(resultado);
        assertEquals("Mario Actualizado", resultado.getNombre());
        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, times(1)).save(any(UsuarioModel.class));
    }

    @Test
    void testActualizarUsuarioNoExiste() {
        int id = 99;
        UsuarioModel nuevo = new UsuarioModel();
        nuevo.setId(id);
        nuevo.setNombre("No Existe");
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        UsuarioModel resultado = usuarioService.actualizar(id, nuevo);

        assertNull(resultado);
        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, never()).save(any(UsuarioModel.class));
    }

    @Test
    void testEliminarUsuario() {
        int id = 4;
        doNothing().when(usuarioRepository).deleteById(id);

        usuarioService.eliminar(id);

        verify(usuarioRepository, times(1)).deleteById(id);
    }

    /*-----------------------Carrito----------------------------------------------*/

    @Test
    void testObtenerTodosCarritos() {
        List<CarritoModel> carritos = new ArrayList<>();
        carritos.add(new CarritoModel());
        when(carritoRepository.findAll()).thenReturn(carritos);

        List<CarritoModel> resultado = carritoService.obtenerTodos();

        assertEquals(carritos, resultado);
        verify(carritoRepository, times(1)).findAll();
    }

    @Test
    void testCrearCarritoCalculaTotal() {
        PerfumeModel perfume = new PerfumeModel();
        perfume.setId(1);
        perfume.setPrecio(15.0);
        UsuarioModel usuario = new UsuarioModel();
        usuario.setId(2);
        CarritoModel carrito = new CarritoModel();
        carrito.setPerfume(perfume);
        carrito.setUsuario(usuario);
        carrito.setCantidad(3);

        when(perfumeRepository.findById(1)).thenReturn(Optional.of(perfume));
        when(carritoRepository.save(carrito)).thenReturn(carrito);

        CarritoModel resultado = carritoService.crear(carrito);

        assertEquals(45.0, resultado.getTotal());
        verify(carritoRepository, times(1)).save(carrito);
    }

    @Test
    void testActualizarCarritoRecalculaTotal() {
        int id = 5;
        PerfumeModel perfume = new PerfumeModel();
        perfume.setId(1);
        perfume.setPrecio(20.0);
        UsuarioModel usuario = new UsuarioModel();
        usuario.setId(3);

        CarritoModel existente = new CarritoModel();
        existente.setId(id);
        existente.setPerfume(perfume);
        existente.setUsuario(usuario);
        existente.setCantidad(1);

        CarritoModel actualizado = new CarritoModel();
        actualizado.setId(id);
        actualizado.setPerfume(perfume);
        actualizado.setUsuario(usuario);
        actualizado.setCantidad(2);

        when(perfumeRepository.findById(1)).thenReturn(Optional.of(perfume));
        when(carritoRepository.findById(id)).thenReturn(Optional.of(existente));
        when(carritoRepository.save(any(CarritoModel.class))).thenReturn(existente);

        CarritoModel resultado = carritoService.actualizar(id, actualizado);

        assertNotNull(resultado);
        assertEquals(40.0, resultado.getTotal());
        verify(carritoRepository, times(1)).findById(id);
        verify(carritoRepository, times(1)).save(any(CarritoModel.class));
    }

    @Test
    void testEliminarCarrito() {
        int id = 7;
        doNothing().when(carritoRepository).deleteById(id);

        carritoService.eliminar(id);

        verify(carritoRepository, times(1)).deleteById(id);
    }

    /*-----------------------Empleados----------------------------------------------*/

    @Test
    void testObtenerTodosEmpleados() {
        List<EmpleadoModel> empleados = new ArrayList<>();
        empleados.add(new EmpleadoModel());
        when(empleadoRepository.findAll()).thenReturn(empleados);

        List<EmpleadoModel> resultado = empleadoService.obtenerTodos();

        assertEquals(empleados, resultado);
        verify(empleadoRepository, times(1)).findAll();
    }

    @Test
    void testObtenerEmpleadoPorId() {
        EmpleadoModel empleado = new EmpleadoModel();
        empleado.setId(1);
        empleado.setNombre("Juan");
        when(empleadoRepository.findById(1)).thenReturn(Optional.of(empleado));

        EmpleadoModel resultado = empleadoService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(empleadoRepository, times(1)).findById(1);
    }

    @Test
    void testObtenerEmpleadoPorIdNoExiste() {
        when(empleadoRepository.findById(99)).thenReturn(Optional.empty());

        EmpleadoModel resultado = empleadoService.buscarPorId(99);

        assertNull(resultado);
        verify(empleadoRepository, times(1)).findById(99);
    }

    @Test
    void testCrearEmpleado() {
        EmpleadoModel empleado = new EmpleadoModel();
        empleado.setId(2);
        empleado.setNombre("Ana");
        when(empleadoRepository.save(empleado)).thenReturn(empleado);

        EmpleadoModel resultado = empleadoService.crear(empleado);

        assertNotNull(resultado);
        assertEquals("Ana", resultado.getNombre());
        verify(empleadoRepository, times(1)).save(empleado);
    }

    @Test
    void testActualizarEmpleado() {
        int id = 3;
        EmpleadoModel existente = new EmpleadoModel();
        existente.setId(id);
        existente.setNombre("Pedro");
        EmpleadoModel nuevo = new EmpleadoModel();
        nuevo.setId(id);
        nuevo.setNombre("Pedro Actualizado");
        when(empleadoRepository.findById(id)).thenReturn(Optional.of(existente));
        when(empleadoRepository.save(any(EmpleadoModel.class))).thenReturn(nuevo);

        EmpleadoModel resultado = empleadoService.actualizar(id, nuevo);

        assertNotNull(resultado);
        assertEquals("Pedro Actualizado", resultado.getNombre());
        verify(empleadoRepository, times(1)).findById(id);
        verify(empleadoRepository, times(1)).save(any(EmpleadoModel.class));
    }

    @Test
    void testActualizarEmpleadoNoExiste() {
        int id = 99;
        EmpleadoModel nuevo = new EmpleadoModel();
        nuevo.setId(id);
        nuevo.setNombre("No Existe");
        when(empleadoRepository.findById(id)).thenReturn(Optional.empty());

        EmpleadoModel resultado = empleadoService.actualizar(id, nuevo);

        assertNull(resultado);
        verify(empleadoRepository, times(1)).findById(id);
        verify(empleadoRepository, never()).save(any(EmpleadoModel.class));
    }

    @Test
    void testEliminarEmpleado() {
        int id = 4;
        doNothing().when(empleadoRepository).deleteById(id);

        empleadoService.eliminar(id);

        verify(empleadoRepository, times(1)).deleteById(id);
    }
}
