package formacion.bb2.services;

import formacion.bb2.models.PriceReduction;
import formacion.bb2.models.Product;
import formacion.bb2.repositories.PriceReductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PriceReductionService {

    @Autowired
    protected ProductService productService;

    @Autowired
    protected PriceReductionRepository priceReductionRepository;

    public List<PriceReduction> findAllPriceReductions() {
        return priceReductionRepository.findAll();
    }

    public PriceReduction findPriceReductionById(Long id) {
        Optional<PriceReduction> optional = priceReductionRepository.findById(id);
        return optional.orElse(null);
    }

    public List<PriceReduction> findAvailablePriceReductionsByProductId(Long id) {
        Product product = productService.findProductById(id);

        if (product != null) {
            List<Long> priceReductions = new ArrayList<>();

            for (PriceReduction priceReduction : product.getPriceReductions()) {
                priceReductions.add(priceReduction.getId());
            }

            if (!priceReductions.isEmpty()) {
                return findAvailablePriceReductions(priceReductions);
            } else {
                return findAllPriceReductions();
            }
        }

        return Collections.emptyList();
    }

    public List<PriceReduction> findAvailablePriceReductions(List<Long> ids) {
        return priceReductionRepository.findByIdNotIn(ids);
    }

}
