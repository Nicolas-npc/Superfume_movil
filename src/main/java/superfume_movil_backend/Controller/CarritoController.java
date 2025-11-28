package superfume_movil_backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import superfume_movil_backend.Model.CarritoModel;
import superfume_movil_backend.Service.CarritoService;

@RestController
@RequestMapping("/carrito")
public class CarritoController extends BaseCrudController<CarritoModel> {

    public CarritoController(CarritoService carritoService) {
        super(carritoService, "Carrito");
    }
}

