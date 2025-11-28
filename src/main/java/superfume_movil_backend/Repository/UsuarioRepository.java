package superfume_movil_backend.Repository;
import org.springframework.stereotype.Repository;
import superfume_movil_backend.Model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository extends JpaRepository <UsuarioModel, Integer>{

}

