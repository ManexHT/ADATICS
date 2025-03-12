package ryuulogic.adatics.reportes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private Long idReporte;
    private Date fecha;

    private String trimestre;

    @Column(nullable = false, length = 10) //Numerico
    private byte numActividad;

    private String evidencia_Fotografica;

    @Column(nullable = false, length = 100)
    private String redaccion;

    private String nombre;
}
