package pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.appwarehouse.entity.Supplier;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    //POST method to add new Supplier
    public Result addSupplier(Supplier supplier){
        if(supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber())){
            return new Result("PhoneNumber exists", false);
        }
        supplierRepository.save(supplier);
        return new Result("Successfully added", true);
    }
    //GET method to get list of Supplier
    public Result getSupplierList(){
        List<Supplier> supplierList = supplierRepository.findAll();
        return new Result("Successfully retrieved", true, supplierList);
    }

    //GET method to get a supplier by ID
    public Result getSupplierById(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        return optionalSupplier.map(supplier
                -> new Result("Successfully retrieved", true, supplier)).orElseGet(()
                -> new Result("Supplier not found", false));
    }

    //PUT method to upload a supplier by ID
    public Result updateSupplier(Integer id, Supplier supplier){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if(optionalSupplier.isPresent()) {
            Supplier updatedSupplier = optionalSupplier.get();
            if(!updatedSupplier.getPhoneNumber().equals(supplier.getPhoneNumber())){
                if(supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber())){
                    return new Result("Phone number exists", false);
                }
                updatedSupplier.setPhoneNumber(supplier.getPhoneNumber());
            }
            updatedSupplier.setName(supplier.getPhoneNumber());
            supplierRepository.save(updatedSupplier);
            return new Result("Successfully uploded", true);
        }
        return new Result("Supplier not found", false);
    }

    //DELETE method to delete supplier by ID
    public Result deleteSupplier(Integer id){
        if(supplierRepository.existsById(id)){
            supplierRepository.deleteById(id);
            return new Result("Successfully deleted", true);
        }
        return new Result("Supplier not found", false);
    }
}
