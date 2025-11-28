package superfume_movil_backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import superfume_movil_backend.Model.UsuarioModel;
import superfume_movil_backend.Service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController extends BaseCrudController<UsuarioModel> {

    public UsuarioController(UsuarioService usuarioService) {
        super(usuarioService, "Usuario");
    }
}

