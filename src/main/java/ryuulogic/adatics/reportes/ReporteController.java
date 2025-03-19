package ryuulogic.adatics.reportes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reporte")
public class ReporteController {

    @Autowired
    private ReporteRepository reporteRepository;

    @GetMapping
    public ResponseEntity<Iterable<Reporte>> findAll() {
        return ResponseEntity.ok(reporteRepository.findAll());
    }

    @GetMapping("/{idReporte}")
    public ResponseEntity<Reporte> findById(@PathVariable Long idReporte) {
        return reporteRepository.findById(Math.toIntExact(idReporte))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reporte> create(@RequestBody Reporte reporte) {
        if (reporte == null) {
            return ResponseEntity.badRequest().build();
        }
        Reporte savedReporte = reporteRepository.save(reporte);
        return ResponseEntity.ok(savedReporte);
    }


    @PutMapping("/{idReporte}")
    public ResponseEntity<Reporte> update(@PathVariable Long idReporte, @RequestBody Reporte reporte) {
        Optional<Reporte> existingReporte = reporteRepository.findById(Math.toIntExact(idReporte));
        if (existingReporte.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Reporte reporteToUpdate = existingReporte.get();
        reporteToUpdate.setFecha(reporte.getFecha());
        reporteToUpdate.setTrimestre(reporte.getTrimestre());
        reporteToUpdate.setNumActividad(reporte.getNumActividad());
        reporteToUpdate.setEvidenciaFotografica(reporte.getEvidenciaFotografica());
        reporteToUpdate.setRedaccion(reporte.getRedaccion());
        reporteToUpdate.setNombre(reporte.getNombre());

        Reporte updatedReporte = reporteRepository.save(reporteToUpdate);
        return ResponseEntity.ok(updatedReporte);
    }

    @DeleteMapping("/{idReporte}")
    public ResponseEntity<Void> deleteById(@PathVariable Long idReporte) {
        if (!reporteRepository.existsById(Math.toIntExact(idReporte))) {
            return ResponseEntity.notFound().build();
        }
        reporteRepository.deleteById(Math.toIntExact(idReporte));
        return ResponseEntity.noContent().build();
    }
}

