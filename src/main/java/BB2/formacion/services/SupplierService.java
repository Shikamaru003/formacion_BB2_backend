package BB2.formacion.services;

import BB2.formacion.models.Supplier;
import BB2.formacion.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public List<Supplier> findAllSuppliers() {
        return supplierRepository.findAll();
    }

    public List<Supplier> findAvailableSuppliers(List<Long> ids) {
        return supplierRepository.findByIdNotIn(ids);
    }

    public Optional<Supplier> findSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    public List<Supplier> findSuppliersWithPriceReductions(){return supplierRepository.findSuppliersWithPriceReductions();}

}
