package pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.appwarehouse.payload.CategoryDto;
import pdp.appwarehouse.payload.Result;
import pdp.appwarehouse.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result addCategoryController(@RequestBody CategoryDto categoryDto){
        return categoryService.addCategoryService(categoryDto);
    }

    @GetMapping
    public Result getCategoryList(){
        return categoryService.getCategoryList();
    }

    @GetMapping("/{id}")
    public Result getCategoryById(@PathVariable Integer id){
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public Result updateCategoryById(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        return categoryService.updateCategory(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteCategoryById(@PathVariable Integer id){
        return categoryService.deleteCategoryByID(id);
    }
}
