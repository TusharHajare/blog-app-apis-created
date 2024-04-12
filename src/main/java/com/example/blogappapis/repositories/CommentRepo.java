package com.example.blogappapis.repositories;

import com.example.blogappapis.entites.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer>
{

}
