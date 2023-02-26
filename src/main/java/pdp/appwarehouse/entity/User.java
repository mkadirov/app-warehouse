package pdp.appwarehouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique = true)
    private String firstName;
    @Column(nullable = false,unique = true)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @Column(nullable = false,unique = true)
    private String code;
    @Column(nullable = false, unique = true)
    private String password;

    private boolean active;
    @ManyToMany
    private Set<Warehouse> warehouses;

}
