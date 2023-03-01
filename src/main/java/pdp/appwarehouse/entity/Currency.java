package pdp.appwarehouse.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pdp.appwarehouse.entity.template.AbsEntity;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Currency extends AbsEntity {

}
