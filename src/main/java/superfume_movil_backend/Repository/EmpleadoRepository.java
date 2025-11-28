package superfume_movil_backend.Repository;
import org.springframework.stereotype.Repository;
import superfume_movil_backend.Model.EmpleadoModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoModel, Integer>{

}

