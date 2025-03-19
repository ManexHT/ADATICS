package ryuulogic.adatics.reportes;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.*;
import ryuulogic.adatics.actividad.Actividad;
import ryuulogic.adatics.planeacion.Planeacion;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="reporte")
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @JsonProperty("idReporte")
    private Long idReporte;

    private Date fecha;

    private String trimestre;

    @Column(nullable = false)
    private int numActividad;

    private String evidenciaFotografica;

    @Column(nullable = false, length = 100)
    private String redaccion;

    @Column(nullable = false, length = 50)
    private String nombre;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idActividad")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Actividad actividad;
}
