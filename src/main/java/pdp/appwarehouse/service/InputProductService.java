package pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.appwarehouse.entity.Input;
import pdp.appwarehouse.entity.InputProduct;
import pdp.appwarehouse.entity.Product;
import pdp.appwarehouse.payload.InputProductDto;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.repository.InputProductRepository;
import pdp.appwarehouse.repository.InputRepository;
import pdp.appwarehouse.repository.ProductRepository;

import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    ProductRepository productRepository;

    //POST method to add new Input Product
    public Result addInputProduct(InputProductDto inputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if(optionalProduct.isEmpty()){
            return new Result("Product not found", false);
        }
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInput.isEmpty()){
            return new Result("Input not found", false);
        }
        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProductRepository.save(inputProduct);
        return new Result("Successfully added", true);
    }

    //GET method to get List of Input Products
    public Result getInputProductList(){
        return new Result("Successfully retrieved", true, inputProductRepository.findAll());
    }

    //GET method to get Input Product by ID
    public Result getInputProductById(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        return optionalInputProduct.map(inputProduct ->
                new Result("Successfully retrieved", true, inputProduct)).orElseGet(() ->
                new Result("InputProduct not found", false));
    }

    //PUT method to upload Input Product
    public Result uploadInputProduct(Integer id, InputProductDto inputProductDto){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if(optionalInputProduct.isPresent()) {
            InputProduct inputProduct = optionalInputProduct.get();
            Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
            if (optionalProduct.isEmpty()) {
                return new Result("Product not found", false);
            }
            Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
            if (optionalInput.isEmpty()) {
                return new Result("Input not found", false);
            }

            inputProduct.setProduct(optionalProduct.get());
            inputProduct.setInput(optionalInput.get());
            inputProduct.setAmount(inputProductDto.getAmount());
            inputProduct.setPrice(inputProductDto.getPrice());
            inputProduct.setExpireDate(inputProductDto.getExpireDate());
            inputProductRepository.save(inputProduct);
            return new Result("Successfully uploaded", true);
        }
        return new Result("InputProduct not found", false);
    }

    //DELETE method to delete Input Product by ID
    public Result deleteInputProduct(Integer id){
        if(inputProductRepository.existsById(id)){
            inputProductRepository.deleteById(id);
            return new Result("Successfully deleted", true);
        }
        return new Result("InputProduct not found", false);
    }
}
