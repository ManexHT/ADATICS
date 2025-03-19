package ryuulogic.adatics.planeacion;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ryuulogic.adatics.objetivo.Objetivo;

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

    @Column(nullable = false, length = 10)
    private int numActividad; // Cambiado de byte a int

    @Column(nullable = false)
    private int componente;

    @Column(nullable = false, length = 50)
    private String unidadResponsable;

    @Column(nullable = false, length = 60)
    private String jefeUnidad;

    @Column(nullable = false, length = 100)
    private String actividades;

    @Column(nullable = false, length = 100)
    private String medioVerificacion;

    @Column(nullable = false, length = 100)
    private String indicadorResultados;

    @Column(nullable = false, length = 100)
    private String calendarizacion;

    @Column(nullable = false)
    private double presupuesto;

    @Column(nullable = false, length = 3)
    private double cantidadAnual;

    @Column(nullable = false, length = 50)
    private String unidadMedida;


    @OneToMany(mappedBy = "planeacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Objetivo> objetivos;
}