package formacion.bb2.services;

import formacion.bb2.models.Product;
import formacion.bb2.models.State;
import formacion.bb2.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    protected ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public void deactivateProductById(Long id, String reason, String username) {
        Optional<Product> opt = productRepository.findById(id);
        if (opt.isPresent()) {
            opt.get().setState(State.DISCONTINUED);
            opt.get().setDeactivateReason(reason);
            opt.get().setDeactivateUser(username);
            productRepository.save(opt.get());
        }
    }

    public List<Product> findCheapestProductPerSupplier() {
        return productRepository.findCheapestProductPerSupplier();
    }


}
