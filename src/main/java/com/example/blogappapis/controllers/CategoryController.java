package com.example.blogappapis.controllers;

import com.example.blogappapis.entites.Category;
import com.example.blogappapis.payloads.ApiResponce;
import com.example.blogappapis.payloads.CategoryDto;
import com.example.blogappapis.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;
    //Post
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCat(@Valid @RequestBody CategoryDto categoryDto)
    {
        CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }
    //Put
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCat(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId)
    {
        CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, catId);
        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponce> deleteCat(@Valid @PathVariable("id") Integer catId)
    {
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponce>
                (new ApiResponce("Category is deleted succesfully !!",true), HttpStatus.OK);
    }
    //Get
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCat(@Valid @PathVariable("id") Integer Id)
    {
        CategoryDto categoryDto = this.categoryService.getSingleCategory(Id);
        return new ResponseEntity<CategoryDto> (categoryDto, HttpStatus.OK);
    }
    //Getall
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCat()
    {
        List<CategoryDto> categories = this.categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

}

