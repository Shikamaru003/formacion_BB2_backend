package formacion.bb2.repositories;

import formacion.bb2.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p.* FROM products p" +
            " JOIN product_supplier ps ON ps.product_id = p.id " +
            " JOIN ( " +
            "       SELECT ps.supplier_id, MIN(p.price) min_price " +
            "       FROM products p " +
            "       JOIN product_supplier ps ON ps.product_id = p.id " +
            "       GROUP BY ps.supplier_id " +
            " ) x" +
            " ON x.supplier_id = ps.supplier_id " +
            " AND x.min_price = p.price;"
            , nativeQuery = true)
    List<Product> findCheapestProductPerSupplier();

}