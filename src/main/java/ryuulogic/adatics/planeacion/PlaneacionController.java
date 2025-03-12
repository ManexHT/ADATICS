package ryuulogic.adatics.planeacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/planeacion")
public class PlaneacionController {
    @Autowired
    private PlaneacionRepository planeacionRepository;

    /*Hay que sincronizar con ReporteRepository
    @Autowired
    private ReporteRepository reporteRepository
     */

    @GetMapping()
    public ResponseEntity<Iterable<Planeacion>> findAll() {
        return ResponseEntity.ok(planeacionRepository.findAll());
    }

    @GetMapping("/{idPlaneacion}")
    public ResponseEntity<Planeacion> findById(@PathVariable Long idPlaneacion) {
        Optional<Planeacion> planeacionOptional = planeacionRepository.findById(idPlaneacion);
        if (planeacionOptional.isPresent()) {
            return ResponseEntity.ok(planeacionOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /* REQUIERE DE LA EXISTENCIA DE Reportes
    @PostMapping
    public ResponseEntity<Planeacion> create(@RequestBody Planeacion planeacion, UriComponentsBuilder uriBuilder) {
        Optional<>
    }

    @PutMapping
    */

    @DeleteMapping("/{idPlaneacion}")
    public ResponseEntity<Void> delete(@PathVariable Long idPlaneacion) {
        if(planeacionRepository.findById(idPlaneacion).isPresent()) {
            planeacionRepository.deleteById(idPlaneacion);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /* REQUIERE DE LA EXISTENCIA DE REPORTES
    @GetMapping("/reportes/{idPlaneacion}")
    public ResponseEntity<Iterable<Reporte>tResporte>(@PathVariable Long idPlaneacion){
        Optional<Planeacion> planeacionOptional = planeacionRepository.findById(idPlaneacion);
        if (planeacionOptional.isPresent()) {
            return ResponseEntity.ok(planeacionOptional.get().getReportes());
        }
        return ResponseEntity.notFound().build();
    }
     */
}
