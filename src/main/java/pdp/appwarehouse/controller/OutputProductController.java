package pdp.appwarehouse.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.appwarehouse.payload.OutputProductDto;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.service.OutputProductService;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;

    @GetMapping
    public Result getOutputList(){
        return outputProductService.getOutputProductList();
    }

    @PostMapping
    public Result addOutput(@RequestBody OutputProductDto outputProductDto){
        return outputProductService.addOutputProduct(outputProductDto);
    }

    @GetMapping("/{id}")
    public Result getOutputById(@PathVariable Integer id){
        return outputProductService.getOutputProductById(id);
    }

    @PutMapping("/{id}")
    public Result uploadOutput(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto){
        return outputProductService.uploadOutputProduct(id, outputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteOutput(@PathVariable Integer id){
        return outputProductService.deleteOutputProduct(id);
    }

}
