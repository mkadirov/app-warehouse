package pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.appwarehouse.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
