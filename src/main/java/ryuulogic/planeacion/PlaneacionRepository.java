package ryuulogic.planeacion;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneacionRepository extends CrudRepository<Planeacion, Long> {
}
