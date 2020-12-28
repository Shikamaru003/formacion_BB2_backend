package formacionBB2.controllers;

import formacionBB2.models.PriceReduction;
import formacionBB2.models.Product;
import formacionBB2.services.ProductService;
import formacionBB2.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("products/all")
    public List<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("products")
    public Page<Product> getProducts(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortField, @RequestParam Integer sortOrder) {
        Pageable pageable;
        if (sortField.isEmpty()) {
            pageable = PageRequest.of(page, size);
        } else {
            if (sortOrder == 1) {
                pageable = PageRequest.of(page, size, Sort.by(sortField).ascending());
            } else {
                pageable = PageRequest.of(page, size, Sort.by(sortField).descending());
            }
        }
        Page<Product> products = productService.findProducts(pageable);
        return products;
    }

    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Optional<Product> optional = productService.findProductById(id);
        Product product = optional.isPresent() ? optional.get() : null;
        if (product != null) {
            for (PriceReduction priceReduction : product.getPriceReductions()) {
                if (priceReduction.getEndDate().before(new Date())) {
                    product.getPriceReductions().remove(priceReduction);
                }
            }
            product = productService.saveProduct(product);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PostMapping("products")
    public ResponseEntity<Product> newProduct(@RequestBody Product product) {
        Product newProduct = productService.saveProduct(product);
        return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
    }

    @PutMapping("products/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product productUpdated = productService.saveProduct(product);
        return new ResponseEntity<Product>(productUpdated, HttpStatus.OK);
    }

    @DeleteMapping("products/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("products/cheapest")
    public List<Product> getCheapestProducts() {
        return productService.findCheapestProductPerSupplier();
    }

}
