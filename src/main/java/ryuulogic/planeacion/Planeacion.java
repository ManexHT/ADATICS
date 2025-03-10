package ryuulogic.planeacion;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ryuulogic.objetivo.Objetivo;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "planeacion")
public class Planeacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlaneacion;

    @Column(nullable = false, length = 10) //Numerico
    private byte numActividad;

    @Column(nullable = false) //Alfanumerico
    private String objetivo; //Este atributo debería hacer referencia e la clase objetivo en las relaciones

    @Column(nullable = false, length = 7) //Numerico (1-7)
    private int componente;

    @Column(nullable = false, length = 50) //Texto 50 caracteres
    private String unidadResponsable;

    @Column(nullable = false, length = 60) //Texto 60 caracteres
    private String jefeUnidad;

    @Column(nullable = false, length = 100) //Texto 100 caracteres
    private String actividades;

    @Column(nullable = false, length = 100) //Texto 100 caracteres
    private String medioVerificacion;

    @Column(nullable = false, length = 100) //Texto 100 caracteres
    private String indicadorResultados;

    @Column(nullable = false, length = 100)
    private String calendarizacion;

    //Este esta de mas, hay que eliminarlo del documento de la IEEE
    private double presupuesto;

    @Column(nullable = false, length = 3) //Numerico 3 digitos
    private double cantidadAnual;

    @Column(nullable = false, length = 3) //Numerico 3 digitos
    private String unidadMedida;


    //RELACIONES
    /*@OneToMany(mappedBy = "planeacion")
    private List<Reporte> reportes;*/ //Comente esta sección debido a que requerimos los reportes

    @OneToMany(mappedBy = "planeacion")
    private List<Objetivo> objetive;
}
