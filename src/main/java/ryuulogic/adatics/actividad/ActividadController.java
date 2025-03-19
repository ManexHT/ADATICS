package ryuulogic.adatics.actividad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ryuulogic.adatics.planeacion.Planeacion;
import ryuulogic.adatics.planeacion.PlaneacionRepository;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/actividad")
public class ActividadController {

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private PlaneacionRepository planeacionRepository;

    @GetMapping
    public ResponseEntity<Iterable<Actividad>> findAll() {
        return ResponseEntity.ok(actividadRepository.findAll());
    }

    @GetMapping("/{idActividad}")
    public ResponseEntity<Actividad> findById(@PathVariable Long idActividad) {
        return actividadRepository.findById(idActividad)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Actividad> create(@RequestBody Actividad actividad, UriComponentsBuilder uriBuilder) {
        System.out.println("Datos recibidos: " + actividad.toString());

        if (actividad.getPlaneacion() == null || actividad.getPlaneacion().getIdPlaneacion() == null) {
            System.out.println("Planeacion es null o idPlaneacion es null");
            return ResponseEntity.badRequest().build();
        }

        Optional<Planeacion> planeacionOptional = planeacionRepository.findById(actividad.getPlaneacion().getIdPlaneacion());
        if (planeacionOptional.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        actividad.setPlaneacion(planeacionOptional.get());
        Actividad savedActividad = actividadRepository.save(actividad);

        URI uri = uriBuilder.path("/actividad/{idActividad}").buildAndExpand(savedActividad.getIdActividad()).toUri();
        return ResponseEntity.created(uri).body(savedActividad);
    }

    @PutMapping("/{idActividad}")
    public ResponseEntity<Actividad> update(@PathVariable Long idActividad, @RequestBody Actividad actividad) {
        if (actividad.getPlaneacion() == null || actividad.getPlaneacion().getIdPlaneacion() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Planeacion> planeacionOptional = planeacionRepository.findById(actividad.getPlaneacion().getIdPlaneacion());
        if (planeacionOptional.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (!actividadRepository.existsById(idActividad)) {
            return ResponseEntity.notFound().build();
        }

        actividad.setPlaneacion(planeacionOptional.get());
        actividad.setIdActividad(idActividad);
        Actividad updatedActividad = actividadRepository.save(actividad);
        return ResponseEntity.ok(updatedActividad);
    }

    @DeleteMapping("/{idActividad}")
    public ResponseEntity<Void> delete(@PathVariable Long idActividad) {
        if (!actividadRepository.existsById(idActividad)) {
            return ResponseEntity.notFound().build();
        }
        actividadRepository.deleteById(idActividad);
        return ResponseEntity.noContent().build();
    }

}
