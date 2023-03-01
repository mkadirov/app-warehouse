package pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.appwarehouse.entity.*;
import pdp.appwarehouse.payload.InputProductDto;
import pdp.appwarehouse.payload.OutputProductDto;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.repository.OutputProductRepository;
import pdp.appwarehouse.repository.OutputRepository;
import pdp.appwarehouse.repository.ProductRepository;

import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    ProductRepository productRepository;

    //POST method to add new Output Product
    public Result addOutputProduct(OutputProductDto outputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if(optionalProduct.isEmpty()){
            return new Result("Product not found", false);
        }
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (optionalOutput.isEmpty()){
            return new Result("Output not found", false);
        }
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setAmount(outputProduct.getAmount());
        outputProduct.setPrice(outputProduct.getPrice());
        outputProductRepository.save(outputProduct);
        return new Result("Successfully added", true);
    }

    //GET method to get List of Output Products
    public Result getOutputProductList(){
        return new Result("Successfully retrieved", true, outputProductRepository.findAll());
    }

    //GET method to get Output Product by ID
    public Result getOutputProductById(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        return optionalOutputProduct.map(outputProduct ->
                new Result("Successfully retrieved", true, outputProduct)).orElseGet(() ->
                new Result("OutputProduct not found", false));
    }

    //PUT method to upload output Product
    public Result uploadOutputProduct(Integer id, OutputProductDto outputProductDto){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if(optionalOutputProduct.isPresent()) {
            OutputProduct outputProduct = optionalOutputProduct.get();
            Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
            if(optionalProduct.isEmpty()){
                return new Result("Product not found", false);
            }
            Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
            if (optionalOutput.isEmpty()){
                return new Result("Output not found", false);
            }
            outputProduct.setProduct(optionalProduct.get());
            outputProduct.setOutput(optionalOutput.get());
            outputProduct.setAmount(outputProduct.getAmount());
            outputProduct.setPrice(outputProduct.getPrice());
            outputProductRepository.save(outputProduct);
            return new Result("Successfully added", true);
        }
        return new Result("OutputProduct not found", false);
    }

    //DELETE method to delete Output Product by ID
    public Result deleteOutputProduct(Integer id){
        if(outputProductRepository.existsById(id)){
            outputProductRepository.deleteById(id);
            return new Result("Successfully deleted", true);
        }
        return new Result("OutputProduct not found", false);
    }
}
