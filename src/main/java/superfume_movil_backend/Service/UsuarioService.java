package superfume_movil_backend.Service;

import org.springframework.stereotype.Service;

import superfume_movil_backend.Model.UsuarioModel;
import superfume_movil_backend.Repository.UsuarioRepository;

@Service
public class UsuarioService extends BaseCrudService<UsuarioModel> {

    public UsuarioService(UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
    }
}

