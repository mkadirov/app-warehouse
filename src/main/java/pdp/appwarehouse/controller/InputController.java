package pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.appwarehouse.payload.InputDto;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.service.InputService;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;

    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto){
        return inputService.addInput(inputDto);
    }

    @GetMapping
    public Result getInputList(){
        return inputService.getInputList();
    }

    @GetMapping("/{id}")
    public Result getInputById(@PathVariable Integer id){
        return inputService.getInputById(id);
    }

    @PutMapping("/{id}")
    public Result uploadInput(@PathVariable Integer id, @RequestBody InputDto inputDto){
        return inputService.uploadInput(id, inputDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteInput(@PathVariable Integer id){
        return inputService.deleteInput(id);
    }
}
