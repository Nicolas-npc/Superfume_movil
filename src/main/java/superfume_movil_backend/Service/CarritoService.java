package superfume_movil_backend.Service;

import org.springframework.stereotype.Service;

import superfume_movil_backend.Model.CarritoModel;
import superfume_movil_backend.Repository.CarritoRepository;
import superfume_movil_backend.Repository.PerfumeRepository;

@Service
public class CarritoService extends BaseCrudService<CarritoModel> {

    private final PerfumeRepository perfumeRepository;

    public CarritoService(CarritoRepository carritoRepository, PerfumeRepository perfumeRepository) {
        super(carritoRepository);
        this.perfumeRepository = perfumeRepository;
    }

    @Override
    public CarritoModel crear(CarritoModel entidad) {
        actualizarTotal(entidad);
        return super.crear(entidad);
    }

    @Override
    public CarritoModel actualizar(int id, CarritoModel entidadActualizada) {
        actualizarTotal(entidadActualizada);
        return super.actualizar(id, entidadActualizada);
    }

    private void actualizarTotal(CarritoModel carrito) {
        if (carrito.getPerfume() != null && carrito.getPerfume().getId() != 0) {
            perfumeRepository.findById(carrito.getPerfume().getId())
                .ifPresent(perfume -> carrito.setTotal(perfume.getPrecio() * carrito.getCantidad()));
        }
    }
}

