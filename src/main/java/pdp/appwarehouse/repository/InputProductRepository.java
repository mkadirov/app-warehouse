package pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.appwarehouse.entity.InputProduct;

public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {
}
