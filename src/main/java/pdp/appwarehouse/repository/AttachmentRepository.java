package pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.appwarehouse.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
