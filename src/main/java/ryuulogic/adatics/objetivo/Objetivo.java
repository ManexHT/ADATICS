package ryuulogic.adatics.objetivo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ryuulogic.adatics.planeacion.Planeacion;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "objetivo")
public class Objetivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObjetivo;

    @Column(nullable = false, length = 50) //Texto 50 caracteres
    private byte nombreObjetivo;

    @Column(nullable = false, length = 100) //Texto 100 caracteres
    private byte descripcion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idPlaneacion")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Planeacion planeacion;
}
