package pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pdp.appwarehouse.entity.User;
import pdp.appwarehouse.entity.Warehouse;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.payload.UniqueNumberGenerator;
import pdp.appwarehouse.payload.UserDto;
import pdp.appwarehouse.repository.UserRepository;
import pdp.appwarehouse.repository.WarehouseRepository;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    //POST method to add new User
    public Result addUser(@RequestBody UserDto userDto){
        if(userRepository.existsByPhoneNumber(userDto.getPhoneNumber())){
            return new Result("Phone number exists ", false);
        }
        Set<Warehouse> warehouseSet = new HashSet<>();
        for (Integer id : userDto.getWarehousesIdList()) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
            optionalWarehouse.ifPresent(warehouseSet::add);
            return new Result("Warehouse not found", false);
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setCode(String.valueOf(UniqueNumberGenerator.getNextUniqueNumber()));
        user.setPassword(user.getPassword());
        user.setWarehouses(warehouseSet);
        userRepository.save(user);
        return new Result("Successfully added", true);
    }

    //GET method to get list of users
    public Result getUsersList(){
        return new Result("Successfully retrieved", true, userRepository.findAll());
    }

    //GET method to get user by ID
    public Result getUserById(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(user ->
                new Result("Successfully retrieved", true, user)).orElseGet(() ->
                new Result("User not found", false));
    }

    //PUT method to upload User by ID
    public Result uploadUserById(Integer id, UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            return new Result("User not found", false);
        }
        User user = optionalUser.get();
        if(!user.getPhoneNumber().equals(userDto.getPhoneNumber())) {
            if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber())) {
                return new Result("Phone number exists ", false);
            }
            user.setPhoneNumber(userDto.getPhoneNumber());
        }
        Set<Warehouse> warehouseSet = new HashSet<>();
        for (Integer warehouseId : userDto.getWarehousesIdList()) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseId);
            optionalWarehouse.ifPresent(warehouseSet::add);
            return new Result("Warehouse not found", false);
        }

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(user.getPassword());
        user.setWarehouses(warehouseSet);
        userRepository.save(user);
        return new Result("Successfully added", true);
    }

    //DELETE method to delete user by ID
    public Result deleteUserById(Integer id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return new Result("Successfully deleted", true);
        }
        return new Result("User not found", false);
    }
}
