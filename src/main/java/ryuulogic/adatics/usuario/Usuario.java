package ryuulogic.adatics.usuario;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import ryuulogic.adatics.planeacion.Planeacion;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="usuario")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idUsuario")
    private Long idUsuario;

    @Column(nullable = false)
    private String rol;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String permisos;

    @Column(nullable = false)
    private String correo;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idPlaneacion")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Planeacion planeacion;
}
