package superfume_movil_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import superfume_movil_backend.Model.CarritoModel;

@Repository
public interface CarritoRepository extends JpaRepository<CarritoModel, Integer> {
}

