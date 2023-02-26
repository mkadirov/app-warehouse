package pdp.appwarehouse.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.appwarehouse.entity.Attachment;
import pdp.appwarehouse.entity.Category;
import pdp.appwarehouse.entity.Measurement;
import pdp.appwarehouse.entity.Product;
import pdp.appwarehouse.payload.ProductDto;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.payload.UniqueNumberGenerator;
import pdp.appwarehouse.repository.AttachmentRepository;
import pdp.appwarehouse.repository.CategoryRepository;
import pdp.appwarehouse.repository.MeasurementRepository;
import pdp.appwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    ProductRepository productRepository;



    //POST method to add new product
    public Result addProductService(ProductDto productDto){

        // check if name of product exists in this category
        if(productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId())){
            return new Result("Product exists", false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty()){
            return new Result("Category not found", false);
        }
        // check if measurement exists
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (optionalMeasurement.isEmpty()){
            return new Result("Measurement not found", false);
        }
        // Check if file exists
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (optionalAttachment.isEmpty()){
            return new Result("File not found", false);
        }
        Product product = new Product();
        product.setName(product.getName());
        product.setCode(String.valueOf(UniqueNumberGenerator.getNextUniqueNumber()));
        product.setAttachment(optionalAttachment.get());
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new Result("Successfully added", true);
    }

    //GET method to get List of Products

    public Result getProductList(){
        List<Product> allProducts = productRepository.findAll();
        return new Result("Successfully retrieved", true, allProducts);
    }

    //GET method to get product by ID

    public Result getProductById(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            return new Result("Successfully retrieved", true, product);
        }else {
            return new Result("product not found", false);
        }
    }

    // PUT method update Product by ID
    public Result updateProduct(Integer id, ProductDto productDto){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (!product.getName().equals(productDto.getName())) {
                // check if name of product exists in this category
                if (productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId())) {
                    return new Result("Product exists", false);
                }
                product.setName(product.getName());
            }
            Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
            if (optionalCategory.isEmpty()) {
                return new Result("Category not found", false);
            }
            // check if measurement exists
            Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
            if (optionalMeasurement.isEmpty()) {
                return new Result("Measurement not found", false);
            }
            // Check if file exists
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
            if (optionalAttachment.isEmpty()) {
                return new Result("File not found", false);
            }

            product.setCode(String.valueOf(UniqueNumberGenerator.getNextUniqueNumber()));
            product.setAttachment(optionalAttachment.get());
            product.setCategory(optionalCategory.get());
            product.setMeasurement(optionalMeasurement.get());
            productRepository.save(product);
            return new Result("Successfully added", true);
        }
        return new Result("product not found", false);
    }

    //DELETE method to delete product by ID
    public Result deleteProduct(Integer id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return  new Result("Successfully deleted", true);
        }else {
            return new Result("Product not found", false);
        }
    }

}
