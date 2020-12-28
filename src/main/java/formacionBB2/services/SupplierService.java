package formacionBB2.services;

import formacionBB2.models.Supplier;
import formacionBB2.repositories.SupplierRepository;
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
