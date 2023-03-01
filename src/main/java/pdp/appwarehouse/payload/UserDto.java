package pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.appwarehouse.entity.Warehouse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String code;
    private String password;

    private boolean active = true;

    private List<Integer> warehousesIdList;
}
