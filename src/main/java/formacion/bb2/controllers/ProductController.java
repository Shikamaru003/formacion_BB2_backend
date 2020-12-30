package formacion.bb2.controllers;

import formacion.bb2.dtos.ProductDto;
import formacion.bb2.models.Product;
import formacion.bb2.services.ProductService;
import formacion.bb2.utils.DTOModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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

    @GetMapping("products/all")
    public List<ProductDto> getAllProducts() {
        return DTOModelMapper.mapList(productService.getAllProducts(), ProductDto.class);
    }

    @GetMapping("products")
    public Page<ProductDto> getProducts(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortField, @RequestParam Integer sortOrder) {
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

        Page<Product> productsPage = productService.getProducts(pageable);
        List<ProductDto> products = DTOModelMapper.mapList(productsPage.getContent(), ProductDto.class);
        return new PageImpl<>(products, pageable, productsPage.getTotalElements());
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        Optional<Product> optional = productService.findProductById(id);
        Product product = optional.orElse(null);

        if (product != null) {
            product.getPriceReductions().removeIf(priceReduction -> priceReduction.getEndDate().before(new Date()));
            product = productService.saveProduct(product);
        }

        return new ResponseEntity<>(DTOModelMapper.map(product, ProductDto.class), HttpStatus.OK);
    }

    @PostMapping("products")
    public ResponseEntity<ProductDto> newProduct(@RequestBody ProductDto productDto) {
        Product product = DTOModelMapper.map(productDto, Product.class);
        Product newProduct = productService.saveProduct(product);
        return new ResponseEntity<>(DTOModelMapper.map(newProduct, ProductDto.class), HttpStatus.OK);
    }

    @PutMapping("products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        Product product = DTOModelMapper.map(productDto, Product.class);
        Product productUpdated = productService.saveProduct(product);
        return new ResponseEntity<>(DTOModelMapper.map(productUpdated, ProductDto.class), HttpStatus.OK);
    }

    @DeleteMapping("products/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("products/{id}/deactivate")
    public ResponseEntity<Void> deactivateProduct(@PathVariable Long id, @RequestParam String reason, @RequestParam String username) {
        productService.deactivateProductById(id, reason, username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("products/cheapest")
    public List<ProductDto> getCheapestProducts() {
        return DTOModelMapper.mapList(productService.findCheapestProductPerSupplier(), ProductDto.class);
    }

}
