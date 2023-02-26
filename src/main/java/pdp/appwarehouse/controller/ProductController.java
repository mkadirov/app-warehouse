package pdp.appwarehouse.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.appwarehouse.payload.ProductDto;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto){
        return productService.addProductService(productDto);
    }

    @GetMapping
    public Result getProductList(){
        return productService.getProductList();
    }

    @GetMapping("/{id}")
    public Result getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Result updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto){
        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteProduct(@PathVariable Integer id){
        return productService.deleteProduct(id);
    }
}
