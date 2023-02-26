package pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.appwarehouse.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    boolean existsByName(String name);
}
