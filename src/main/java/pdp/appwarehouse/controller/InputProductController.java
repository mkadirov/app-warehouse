package pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.appwarehouse.payload.InputProductDto;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.service.InputProductService;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

    @GetMapping
    public Result getInputProductList(){
        return inputProductService.getInputProductList();
    }

    @PostMapping
    public Result addInputProduct(@RequestBody InputProductDto inputProductDto){
        return inputProductService.addInputProduct(inputProductDto);
    }

    @GetMapping("/{id}")
    public Result getInputProductById(@PathVariable Integer id){
        return inputProductService.getInputProductById(id);
    }

    @PutMapping("/{id}")
    public Result uploadInputProduct(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto){
        return inputProductService.uploadInputProduct(id, inputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteInputProduct(@PathVariable Integer id){
        return inputProductService.deleteInputProduct(id);
    }
}
