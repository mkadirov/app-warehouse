package pdp.appwarehouse.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pdp.appwarehouse.entity.template.AbsEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsEntity {


    @ManyToOne(optional = false)
    private Category category;

    @OneToOne
    private Attachment attachment;

    @Column(nullable = false, unique = false)
    private String code;

    @ManyToOne(optional = false)
    private Measurement measurement;

}
