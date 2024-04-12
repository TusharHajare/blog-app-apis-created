package com.example.blogappapis.serviceImpl;

import com.example.blogappapis.entites.Category;
import com.example.blogappapis.exceptions.ResourceNotFoundException;
import com.example.blogappapis.payloads.CategoryDto;
import com.example.blogappapis.repositories.CategoryRepo;
import com.example.blogappapis.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper; // use to go DTO to ENTITY class and ENTITY class to DTO class.

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto)
    {
       Category cat =  this.modelMapper.map(categoryDto, Category.class);
       Category newCategory =  this.categoryRepo.save(cat);
       return this.modelMapper.map(newCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId)
    {
      Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category_ID", categoryId ));
       cat.setCategoryTitle(categoryDto.getCategoryTitle());
       cat.setCategoryDiscription(categoryDto.getCategoryDiscription());

       Category newCat = categoryRepo.save(cat);
       return this.modelMapper.map(newCat, CategoryDto.class);
    }


    @Override
    public void deleteCategory(Integer categoryId)
    {
      Category cat = this.categoryRepo.findById(categoryId)
              .orElseThrow(() -> new ResourceNotFoundException("Category", "category_id", categoryId));
      this.categoryRepo.delete(cat);
    }


    @Override
    public CategoryDto getSingleCategory(Integer categoryId)
    {
        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "Category_Id", categoryId));
        return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories()
    {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> catDtos = categories.stream().
                map((cat) -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());

        return catDtos;
    }

}
