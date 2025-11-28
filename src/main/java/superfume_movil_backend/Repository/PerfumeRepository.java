package superfume_movil_backend.Repository;
import org.springframework.stereotype.Repository;
import superfume_movil_backend.Model.PerfumeModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PerfumeRepository extends JpaRepository <PerfumeModel, Integer> {

}

