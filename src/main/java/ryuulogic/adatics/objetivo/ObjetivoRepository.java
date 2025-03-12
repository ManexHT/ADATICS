package ryuulogic.adatics.objetivo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetivoRepository extends CrudRepository<Objetivo, Long> {
    Long idObjetivo(Long idObjetivo);
}
