package com.example.blogappapis.services;

import com.example.blogappapis.payloads.CategoryDto;

import java.util.List;

public interface CategoryService
{
    //post
    CategoryDto createCategory(CategoryDto categoryDto);

    //put
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    //get
    CategoryDto getSingleCategory(Integer categoryId);

    //getAll
    List<CategoryDto> getAllCategories();

    //delete
    void  deleteCategory(Integer categoryId);
}
