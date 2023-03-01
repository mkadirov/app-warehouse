package pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.appwarehouse.entity.OutputProduct;

public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {

}
