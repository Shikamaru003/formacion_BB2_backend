package BB2.formacion.services;

import BB2.formacion.models.PriceReduction;
import BB2.formacion.repositories.PriceReductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceReductionService {

    @Autowired
    PriceReductionRepository priceReductionRepository;

    public List<PriceReduction> findAllPriceReductions() {
        return priceReductionRepository.findAll();
    }

    public Optional<PriceReduction> findPriceReductionById(Long id) {
        return priceReductionRepository.findById(id);
    }

    public List<PriceReduction> findAvailablePriceReductions(List<Long> ids) {
        return priceReductionRepository.findByIdNotIn(ids);
    }


}
