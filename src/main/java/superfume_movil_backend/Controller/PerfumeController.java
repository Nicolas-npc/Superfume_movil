package superfume_movil_backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import superfume_movil_backend.Model.PerfumeModel;
import superfume_movil_backend.Service.PerfumeService;

@RestController
@RequestMapping("/Perfume")
public class PerfumeController extends BaseCrudController<PerfumeModel> {

    public PerfumeController(PerfumeService perfumeService) {
        super(perfumeService, "Perfume");
    }
}

