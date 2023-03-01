package pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.appwarehouse.entity.Input;

public interface InputRepository extends JpaRepository<Input, Integer> {
    boolean existsByFactureNumber(String name);
}
