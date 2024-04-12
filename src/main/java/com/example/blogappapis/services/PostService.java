package com.example.blogappapis.services;


import com.example.blogappapis.entites.Post;
import com.example.blogappapis.payloads.PostDto;
import com.example.blogappapis.payloads.PostResponse;

import java.util.List;

public interface PostService
{
    //create
    PostDto createPost(PostDto postDto,  Integer userId, Integer categoryId);

    //put
    PostDto updatePost(PostDto postDto, Integer postId);

    //delete
    void deletePost(Integer postId);

    //get by id
    PostDto getPostById(Integer postId);

    //getAll
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get all post by user
    List<PostDto> getPostByUser(Integer userId);

    //get all post by category
    List<PostDto> getPostByCategory(Integer categoryId);


    //Search Post
    List<PostDto> searchPosts(String keyword);

}
