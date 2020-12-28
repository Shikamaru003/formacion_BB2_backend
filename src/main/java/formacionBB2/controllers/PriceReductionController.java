package formacionBB2.controllers;

import formacionBB2.models.PriceReduction;
import formacionBB2.models.Product;
import formacionBB2.services.PriceReductionService;
import formacionBB2.services.ProductService;
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
