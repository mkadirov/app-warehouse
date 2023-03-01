package pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.payload.UserDto;
import pdp.appwarehouse.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public Result getUserList(){
        return userService.getUsersList();
    }

    @GetMapping("/{id}")
    public Result getUserByID(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @PostMapping
    public Result addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    @PutMapping("/{id}")
    public Result uploadUser(@PathVariable Integer id, @RequestBody UserDto userDto){
        return userService.uploadUserById(id, userDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){
        return userService.deleteUserById(id);
    }
}
