package pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.appwarehouse.entity.Supplier;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.repository.SupplierRepository;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    //POST method to add new Supplier
    public Result addSupplier(Supplier supplier){
        
    }
}
