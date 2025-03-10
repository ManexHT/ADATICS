package ryuulogic.reportes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.net.URI;
@RestController
@RequestMapping("reporte")

public class ReporteController {
    @Autowired
    private ReporteRepository reporteRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Reporte>> findAll() {
        return ResponseEntity.ok(reporteRepository.findAll());
    }
    @GetMapping("/{idReporte}")
    public ResponseEntity<Optional<Reporte>> findById(@PathVariable Integer idReporte) {
        Optional<Reporte> reporteOptional = reporteRepository.findById(idReporte);
        if (reporteOptional.isPresent()) {
            return ResponseEntity.ok(reporteOptional);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // @PostMapping

    // @PutMapping("/{idReporte}")

    @DeleteMapping("/{idReporte}")
    public ResponseEntity<?> deleteById(@PathVariable Integer idReporte) {
        if (reporteRepository.existsById(idReporte)) {
            reporteRepository.deleteById(idReporte);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
