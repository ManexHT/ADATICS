package ryuulogic.objetivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ryuulogic.planeacion.Planeacion;
import ryuulogic.planeacion.PlaneacionRepository;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/objetivo")
public class ObjetivoController {
    @Autowired
    private ObjetivoRepository objetivoRepository;
    @Autowired
    private PlaneacionRepository planeacionRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Objetivo>> findAll() {
        return ResponseEntity.ok(objetivoRepository.findAll());
    }

    @GetMapping("/{idObjetivo}")
    public ResponseEntity<Objetivo> findById(@PathVariable Long idObjetivo) {
        Optional<Objetivo> objetivoOptional = objetivoRepository.findById(idObjetivo);
        if (objetivoOptional.isPresent()) {
            return ResponseEntity.ok(objetivoOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //Falta terminarlos con respecto a las relaciones que tienen
    /*@PostMapping
    public ResponseEntity<Objetivo> save(@RequestBody Objetivo objetivo, UriComponentsBuilder uriBuilder) {
        Optional<Planeacion> planeacionOptional = planeacionRepository.findById(objetivo.getIdObjetivo())
    }*/

    @PutMapping("/{idObjetivo}")
    public ResponseEntity<Void> update(@PathVariable Long idObjetivo, @RequestBody Objetivo objetivo) {
        Optional<Planeacion> planeacionOptional = planeacionRepository.findById(objetivo.getPlaneacion().getIdPlaneacion());
        if(!planeacionOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        Objetivo objetivoAnterior = objetivoRepository.findById(objetivo.getIdObjetivo()).get();
        if(objetivoAnterior != null){
            objetivo.setPlaneacion(planeacionOptional.get());
            objetivo.setIdObjetivo(objetivoAnterior.getIdObjetivo());
            objetivoRepository.save(objetivo);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idObjetivo}")
    public ResponseEntity<Void> delete(@PathVariable Long idObjetivo) {
        if(objetivoRepository.findById(idObjetivo).isPresent()) {
            objetivoRepository.deleteById(idObjetivo);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
