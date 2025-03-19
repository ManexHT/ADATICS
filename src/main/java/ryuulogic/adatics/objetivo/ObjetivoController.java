package ryuulogic.adatics.objetivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ryuulogic.adatics.planeacion.Planeacion;
import ryuulogic.adatics.planeacion.PlaneacionRepository;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/objetivo")
public class ObjetivoController {

    @Autowired
    private ObjetivoRepository objetivoRepository;

    @Autowired
    private PlaneacionRepository planeacionRepository;

    @GetMapping
    public ResponseEntity<Iterable<Objetivo>> findAll() {
        return ResponseEntity.ok(objetivoRepository.findAll());
    }

    @GetMapping("/{idObjetivo}")
    public ResponseEntity<Objetivo> findById(@PathVariable Long idObjetivo) {
        return objetivoRepository.findById(idObjetivo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Objetivo> create(@RequestBody Objetivo objetivo, UriComponentsBuilder uriBuilder) {
        System.out.println("Datos recibidos: " + objetivo.toString());

        if (objetivo.getPlaneacion() == null || objetivo.getPlaneacion().getIdPlaneacion() == null) {
            System.out.println("Planeacion es null o idPlaneacion es null");
            return ResponseEntity.badRequest().build();
        }

        Optional<Planeacion> planeacionOptional = planeacionRepository.findById(objetivo.getPlaneacion().getIdPlaneacion());
        if (planeacionOptional.isEmpty()) {
            System.out.println("Planeacion no encontrada con id: " + objetivo.getPlaneacion().getIdPlaneacion());
            return ResponseEntity.unprocessableEntity().build();
        }

        objetivo.setPlaneacion(planeacionOptional.get());
        Objetivo savedObjetivo = objetivoRepository.save(objetivo);

        URI uri = uriBuilder.path("/objetivo/{idObjetivo}").buildAndExpand(savedObjetivo.getIdObjetivo()).toUri();
        return ResponseEntity.created(uri).body(savedObjetivo);
    }

    @PutMapping("/{idObjetivo}")
    public ResponseEntity<Objetivo> update(@PathVariable Long idObjetivo, @RequestBody Objetivo objetivo) {
        if (objetivo.getPlaneacion() == null || objetivo.getPlaneacion().getIdPlaneacion() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Planeacion> planeacionOptional = planeacionRepository.findById(objetivo.getPlaneacion().getIdPlaneacion());
        if (planeacionOptional.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (!objetivoRepository.existsById(idObjetivo)) {
            return ResponseEntity.notFound().build();
        }

        objetivo.setPlaneacion(planeacionOptional.get());
        objetivo.setIdObjetivo(idObjetivo);
        Objetivo updatedObjetivo = objetivoRepository.save(objetivo);
        return ResponseEntity.ok(updatedObjetivo);
    }

    @DeleteMapping("/{idObjetivo}")
    public ResponseEntity<Void> delete(@PathVariable Long idObjetivo) {
        if (!objetivoRepository.existsById(idObjetivo)) {
            return ResponseEntity.notFound().build();
        }
        objetivoRepository.deleteById(idObjetivo);
        return ResponseEntity.noContent().build();
    }
}
