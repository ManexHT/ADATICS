package ryuulogic.adatics.actividad;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ryuulogic.adatics.actividad.Actividad;

@Repository
public interface ActividadRepository extends CrudRepository<Actividad, Long> {

}
