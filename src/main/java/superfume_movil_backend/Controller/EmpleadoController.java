package superfume_movil_backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import superfume_movil_backend.Model.EmpleadoModel;
import superfume_movil_backend.Service.EmpleadoService;

@RestController
@RequestMapping("/Empleado")
public class EmpleadoController extends BaseCrudController<EmpleadoModel> {

    public EmpleadoController(EmpleadoService empleadoService) {
        super(empleadoService, "Empleado");
    }
}

