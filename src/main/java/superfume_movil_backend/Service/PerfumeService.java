package superfume_movil_backend.Service;

import org.springframework.stereotype.Service;

import superfume_movil_backend.Model.PerfumeModel;
import superfume_movil_backend.Repository.PerfumeRepository;

@Service
public class PerfumeService extends BaseCrudService<PerfumeModel> {

    public PerfumeService(PerfumeRepository perfumeRepository) {
        super(perfumeRepository);
    }
}

