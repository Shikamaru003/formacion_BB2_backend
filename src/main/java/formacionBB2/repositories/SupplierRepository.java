package formacionBB2.repositories;

import formacionBB2.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findByIdNotIn(List<Long> ids);

    @Query(value = "SELECT s.* " +
            "FROM suppliers s " +
            "JOIN product_supplier ps ON ps.supplier_id=s.id " +
            "JOIN products p ON p.id=ps.product_id " +
            "JOIN product_price_reduction ppr ON ppr.product_id=p.id;", nativeQuery = true)
    List<Supplier> findSuppliersWithPriceReductions();
}