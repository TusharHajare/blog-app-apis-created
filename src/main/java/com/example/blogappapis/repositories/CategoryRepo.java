package com.example.blogappapis.repositories;

import com.example.blogappapis.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer>
{

}
