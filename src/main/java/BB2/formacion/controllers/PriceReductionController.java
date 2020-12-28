package BB2.formacion.controllers;

import BB2.formacion.models.PriceReduction;
import BB2.formacion.models.Product;
import BB2.formacion.services.PriceReductionService;
import BB2.formacion.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class PriceReductionController {

	@Autowired
	private PriceReductionService priceReductionService;
	@Autowired
	private ProductService productService;

	@GetMapping("price_reductions")
	public List<PriceReduction> getAllSuppliers() {
		return priceReductionService.findAllPriceReductions();
	}

	@GetMapping("price_reductions/{id}")
	public PriceReduction getPriceReductionById(@PathVariable Long id) {
		Optional<PriceReduction> optional = priceReductionService.findPriceReductionById(id);
		return optional.isPresent() ? optional.get() : null;
	}
	@GetMapping("products/{id}/available_price_reductions")
	public List<PriceReduction> getAvailableSuppliers(@PathVariable Long id) {
		Optional<Product> optional = productService.findProductById(id);
		if(optional.isPresent()){
			List<Long> priceReductions = new ArrayList<>();
			for (PriceReduction priceReduction:optional.get().getPriceReductions()) {
				priceReductions.add(priceReduction.getId());
			}
			if(!priceReductions.isEmpty()){
				return priceReductionService.findAvailablePriceReductions(priceReductions);
			}
			else{
				return priceReductionService.findAllPriceReductions();
			}
		}

		return Collections.EMPTY_LIST;
	}


}
