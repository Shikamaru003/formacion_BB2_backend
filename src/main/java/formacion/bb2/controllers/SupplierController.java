package formacion.bb2.controllers;

import formacion.bb2.models.Product;
import formacion.bb2.models.Supplier;
import formacion.bb2.services.ProductService;
import formacion.bb2.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ProductService productService;

    @GetMapping("suppliers/all")
    public List<Supplier> getAllSuppliers() {
        return supplierService.findAllSuppliers();
    }

    @GetMapping("suppliers/{id}")
    public Supplier getSupplierById(@PathVariable Long id) {
        Optional<Supplier> optional = supplierService.findSupplierById(id);
        return optional.orElse(null);
    }

    @GetMapping("products/{id}/available_suppliers")
    public List<Supplier> getAvailableSuppliers(@PathVariable Long id) {
        Optional<Product> optional = productService.findProductById(id);
        if (optional.isPresent()) {
            List<Long> suppliers = new ArrayList<>();
            for (Supplier supplier : optional.get().getSuppliers()) {
                suppliers.add(supplier.getId());
            }
            if (!suppliers.isEmpty()) {
                return supplierService.findAvailableSuppliers(suppliers);
            } else {
                return supplierService.findAllSuppliers();
            }
        }

        return Collections.emptyList();
    }

    @GetMapping("suppliers/with_price_reductions")
    public List<Supplier> getSuppliersWithPriceReduction() {
        return supplierService.findSuppliersWithPriceReductions();
    }

}
