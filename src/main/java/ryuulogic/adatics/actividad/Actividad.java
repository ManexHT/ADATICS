package ryuulogic.adatics.actividad;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ryuulogic.adatics.actividad.Actividad;
import ryuulogic.adatics.planeacion.Planeacion;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "actividad")
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActividad;

    @Column(nullable = false)
    private Integer numActividad;

    @Column(nullable = false, length = 100)
    private String unidadResponsable;

    @Column(nullable = false, length = 100)
    private String jefeUnidad;

    @Column(nullable = false, length = 255)
    private String descripcionActividades;

    @Column(nullable = false, length = 255)
    private String mediosVerificacion;

    @Column(nullable = false, length = 255)
    private String indicadorResultados;

    @Column(nullable = false, length = 50)
    private String unidadMedida;

    @Column(nullable = false)
    private Integer cantidadAnual;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idPlaneacion")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Planeacion planeacion;
}
