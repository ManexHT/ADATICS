package ryuulogic.adatics.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ryuulogic.adatics.reportes.Reporte;
import ryuulogic.adatics.reportes.ReporteRepository;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")

public class UsuarioController {



    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<Iterable<Usuario>> findAll() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> findById(@PathVariable Integer idUsuario) {
        return usuarioRepository.findById(Math.toIntExact(idUsuario))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        }
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(savedUsuario);
    }


    @PutMapping("/{idUsuario}")
    public ResponseEntity<Usuario> update(@PathVariable Long idUsuario, @RequestBody Usuario usuario) {
        Optional<Usuario> existingUsuario = usuarioRepository.findById(Math.toIntExact(idUsuario));
        if (existingUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuarioToUpdate = existingUsuario.get();
        usuarioToUpdate.setDescripcion(usuario.getDescripcion());
        usuarioToUpdate.setRol(usuario.getRol());
        usuarioToUpdate.setPermisos(usuario.getPermisos());
        usuarioToUpdate.setCorreo(usuario.getCorreo());

        Usuario updatedUsuario = usuarioRepository.save(usuarioToUpdate);
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deleteById(@PathVariable Long idUsuario) {
        if (!usuarioRepository.existsById(Math.toIntExact(idUsuario))) {
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(Math.toIntExact(idUsuario));
        return ResponseEntity.noContent().build();
    }
}
