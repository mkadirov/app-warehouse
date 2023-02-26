package pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.appwarehouse.entity.Supplier;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping
    public Result getSupplierList(){
        return supplierService.getSupplierList();
    }

    @PostMapping
    public Result addSupplier(@RequestBody Supplier supplier){
        return supplierService.addSupplier(supplier);
    }

    @GetMapping("/{id}")
    public Result getSupplierById(@PathVariable Integer id){
        return supplierService.getSupplierById(id);
    }

    @PutMapping("/{id}")
    public Result updateSupplier(@PathVariable Integer id, @RequestBody Supplier supplier){
        return supplierService.updateSupplier(id, supplier);
    }
    @DeleteMapping("/{id}")
    public Result deleteSupplier(@PathVariable Integer id){
        return supplierService.deleteSupplier(id);
    }
}
