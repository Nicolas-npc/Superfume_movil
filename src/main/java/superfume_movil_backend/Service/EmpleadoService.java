package superfume_movil_backend.Service;

import org.springframework.stereotype.Service;

import superfume_movil_backend.Model.EmpleadoModel;
import superfume_movil_backend.Repository.EmpleadoRepository;

@Service
public class EmpleadoService extends BaseCrudService<EmpleadoModel> {

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        super(empleadoRepository);
    }
}

