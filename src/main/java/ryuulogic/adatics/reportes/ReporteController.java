package ryuulogic.adatics.reportes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ryuulogic.adatics.actividad.Actividad;
import ryuulogic.adatics.actividad.ActividadRepository;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/reporte")
public class ReporteController {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private ActividadRepository actividadRepository;

    @GetMapping
    public ResponseEntity<Iterable<Reporte>> findAll() {
        return ResponseEntity.ok(reporteRepository.findAll());
    }

    @GetMapping("/{idReporte}")
    public ResponseEntity<Reporte> findById(@PathVariable Long idReporte) {
        return reporteRepository.findById(idReporte)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reporte> create(@RequestBody Reporte reporte, UriComponentsBuilder uriBuilder) {
        if (reporte.getActividad() == null || reporte.getActividad().getIdActividad() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Actividad> actividadOptional = actividadRepository.findById(reporte.getActividad().getIdActividad());
        if (actividadOptional.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        reporte.setActividad(actividadOptional.get());
        Reporte savedReporte = reporteRepository.save(reporte);

        URI uri = uriBuilder.path("/reporte/{idReporte}").buildAndExpand(savedReporte.getIdReporte()).toUri();
        return ResponseEntity.created(uri).body(savedReporte);
    }

    @PutMapping("/{idReporte}")
    public ResponseEntity<Reporte> update(@PathVariable Long idReporte, @RequestBody Reporte reporte) {
        if (reporte.getActividad() == null || reporte.getActividad().getIdActividad() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Actividad> actividadOptional = actividadRepository.findById(reporte.getActividad().getIdActividad());
        if (actividadOptional.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (!reporteRepository.existsById(idReporte)) {
            return ResponseEntity.notFound().build();
        }

        reporte.setActividad(actividadOptional.get());
        reporte.setIdReporte(idReporte);
        Reporte updatedReporte = reporteRepository.save(reporte);
        return ResponseEntity.ok(updatedReporte);
    }

    @DeleteMapping("/{idReporte}")
    public ResponseEntity<Void> deleteById(@PathVariable Long idReporte) {
        if (!reporteRepository.existsById(idReporte)) {
            return ResponseEntity.notFound().build();
        }
        reporteRepository.deleteById(idReporte);
        return ResponseEntity.noContent().build();
    }
}

