package pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.appwarehouse.entity.Output;

public interface OutputRepository extends JpaRepository<Output, Integer> {
    boolean existsByFactureNumber(String name);
}
