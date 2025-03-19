package ryuulogic.adatics.planeacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/planeacion")
public class PlaneacionController {

    @Autowired
    private PlaneacionRepository planeacionRepository;

    @GetMapping
    public ResponseEntity<Iterable<Planeacion>> findAll() {
        return ResponseEntity.ok(planeacionRepository.findAll());
    }

    @GetMapping("/{idPlaneacion}")
    public ResponseEntity<Planeacion> findById(@PathVariable Long idPlaneacion) {
        return planeacionRepository.findById(idPlaneacion)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Planeacion> create(@RequestBody Planeacion planeacion, UriComponentsBuilder uriBuilder) {
        if (planeacion.getIdPlaneacion() != null && planeacionRepository.existsById(planeacion.getIdPlaneacion())) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Planeacion savedPlaneacion = planeacionRepository.save(planeacion);
        return ResponseEntity.created(uriBuilder.path("/planeacion/{idPlaneacion}")
                        .buildAndExpand(savedPlaneacion.getIdPlaneacion()).toUri())
                .body(savedPlaneacion);
    }

    @PutMapping("/{idPlaneacion}")
    public ResponseEntity<Planeacion> update(@PathVariable Long idPlaneacion, @RequestBody Planeacion planeacion) {
        if (!planeacionRepository.existsById(idPlaneacion)) {
            return ResponseEntity.notFound().build();
        }
        planeacion.setIdPlaneacion(idPlaneacion);
        Planeacion updatedPlaneacion = planeacionRepository.save(planeacion);
        return ResponseEntity.ok(updatedPlaneacion);
    }

    @DeleteMapping("/{idPlaneacion}")
    public ResponseEntity<Void> delete(@PathVariable Long idPlaneacion) {
        if (!planeacionRepository.existsById(idPlaneacion)) {
            return ResponseEntity.notFound().build();
        }
        planeacionRepository.deleteById(idPlaneacion);
        return ResponseEntity.noContent().build();
    }
}
