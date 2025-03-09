package ryuulogic.planeacion;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "planeacion");
public class Planeacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlaneacion;
    @Column(nullable = false, length = 10)
    private
}
