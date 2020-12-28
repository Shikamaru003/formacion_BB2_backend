package BB2.formacion.repositories;

import BB2.formacion.models.PriceReduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceReductionRepository extends JpaRepository<PriceReduction, Long> {

    List<PriceReduction> findByIdNotIn(List<Long> ids);

}