package formacion.bb2.controllers;

import formacion.bb2.dtos.PriceReductionDto;
import formacion.bb2.models.PriceReduction;
import formacion.bb2.services.PriceReductionService;
import formacion.bb2.utils.DTOModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class PriceReductionController {

    @Autowired
    private PriceReductionService priceReductionService;

    @GetMapping("price_reductions")
    public List<PriceReductionDto> getAllSuppliers() {
        return DTOModelMapper.mapList(priceReductionService.findAllPriceReductions(), PriceReductionDto.class);
    }

    @GetMapping("products/{id}/available_price_reductions")
    public List<PriceReductionDto> getAvailablePriceReductions(@PathVariable Long id) {
        return DTOModelMapper.mapList(priceReductionService.findAvailablePriceReductionsByProductId(id), PriceReductionDto.class);
    }

    @GetMapping("price_reductions/{id}")
    public ResponseEntity<PriceReductionDto> getPriceReductionById(@PathVariable Long id) {
        PriceReduction priceReduction = priceReductionService.findPriceReductionById(id);
        return new ResponseEntity<>(DTOModelMapper.map(priceReduction, PriceReductionDto.class), HttpStatus.OK);
    }

}
