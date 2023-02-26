package pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.appwarehouse.entity.Category;
import pdp.appwarehouse.payload.CategoryDto;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    //POST method to add new Category
    public Result addCategoryService(CategoryDto categoryDto){
        Category category = new Category();
        if(categoryRepository.existsByName(categoryDto.getName())) {
            return new Result("Category exists", false);
        }
        if(categoryDto.getParentId()!=null) {

            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentId());
            if (optionalCategory.isPresent()){
                category.setParentCategory(optionalCategory.get());
            }else {
                return new Result("Parent Category not found", false);
            }
        }
        category.setName(category.getName());
        categoryRepository.save(category);
        return new Result("Successfully added", true);
    }

    // GET method to get list of Category
    public Result getCategoryList(){
        return new Result("Successfully retrieved", true, categoryRepository.findAll());
    }


    //Get mapping to retrieve a Category by ID
    public Result getCategoryById(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.map(category ->
                new Result("Successfully retrieved", true, category)).orElseGet(()
                -> new Result("Category not found", false));
    }

    //Put method to update Category by ID
    public Result updateCategory(Integer id, CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            if(!category.getName().equals(categoryDto.getName())) {
                if (categoryRepository.existsByName(categoryDto.getName())) {
                    return new Result("Category exists", false);
                }
                category.setName(category.getName());
            }
            if(categoryDto.getParentId()!=null) {

                Optional<Category> opParentCategory = categoryRepository.findById(categoryDto.getParentId());
                if (opParentCategory.isPresent()){
                    category.setParentCategory(optionalCategory.get());
                }else {
                    return new Result("Parent Category not found", false);
                }
            }
            categoryRepository.save(category);
            return new Result("Successfully added", true);
        }
       return new Result("Category not found", false);
    }

    // DELETE method to delete Category by ID
    public Result deleteCategoryByID(Integer id){
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return new Result("Successfully deleted", true);
        }
        return new Result("Category not found", true);
    }
}
