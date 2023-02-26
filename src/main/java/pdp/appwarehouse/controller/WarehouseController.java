package pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.appwarehouse.entity.Warehouse;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.service.WarehouseService;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public Result addWarehouse(@RequestBody Warehouse warehouse){
        return warehouseService.addWarehouseService(warehouse);
    }

    @GetMapping
    public Result getWarehouseList(){
        return warehouseService.getListOfWarehouse();
    }

    @GetMapping("/{id}")
    public Result getWarehouseById(@PathVariable  Integer id){
        return warehouseService.getWarehouseById(id);
    }

    @PutMapping("/{id}")
    public Result updateWarehouse(@PathVariable Integer id, @RequestBody Warehouse warehouse){
        return warehouseService.updateWarehouse(id, warehouse);
    }

    @DeleteMapping("/{id}")
    public Result deleteWarehouse(@PathVariable Integer id){
        return warehouseService.deleteWarehouseById(id);
    }
}
