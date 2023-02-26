package pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.appwarehouse.entity.Warehouse;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    // GET method to take List of warehouse

    public Result getListOfWarehouse(){
        return new Result("WarehouseList retrieved successfully", true, warehouseRepository.findAll());
    }

    //PUT method to add new warehouse
    public Result addWarehouseService(Warehouse warehouse){

        if(warehouseRepository.existsByName(warehouse.getName())){
            return new Result("Warehouse exists", false);
        }
        warehouseRepository.save(warehouse);
        return new Result("Successfully added", true);
    }

    // GET method to retrieve a warehouse by ID
    public Result getWarehouseById(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        return optionalWarehouse.map(warehouse ->
                new Result("Warehouse retrieved successfully", true, warehouse)).orElseGet(()
                -> new Result("Warehouse not found with ID: " + id, false));
    }
    // PUT method to update an existing warehouse
    public Result updateWarehouse(Integer id, Warehouse warehouse) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isEmpty()) {
            return new Result("Warehouse not found with ID: " + warehouse.getId(), false);
        }
        Warehouse updatedWarehouse = optionalWarehouse.get();
        updatedWarehouse.setName(warehouse.getName());
        warehouseRepository.save(updatedWarehouse);
        return new Result("Warehouse updated successfully", true);
    }

    // DELETE method to delete a warehouse by ID
    public Result deleteWarehouseById(Integer id) {
        if (warehouseRepository.existsById(id)) {
            warehouseRepository.deleteById(id);
            return new Result("Warehouse deleted successfully", true);
        } else {
            return new Result( "Warehouse not found with ID: " + id, false);
        }
    }
}
