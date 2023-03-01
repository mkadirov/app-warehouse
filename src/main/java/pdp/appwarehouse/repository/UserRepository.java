package pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.appwarehouse.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
