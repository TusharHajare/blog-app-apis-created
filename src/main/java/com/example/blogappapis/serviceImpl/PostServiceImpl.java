package com.example.blogappapis.serviceImpl;

import com.example.blogappapis.entites.Category;
import com.example.blogappapis.entites.Post;
import com.example.blogappapis.entites.User;
import com.example.blogappapis.exceptions.ResourceNotFoundException;
import com.example.blogappapis.payloads.PostDto;
import com.example.blogappapis.payloads.PostResponse;
import com.example.blogappapis.repositories.CategoryRepo;
import com.example.blogappapis.repositories.PostRepo;
import com.example.blogappapis.repositories.userRepo;
import com.example.blogappapis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService
{
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private userRepo userRepos;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId)
    {
        User user = this.userRepos.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "User_id", userId));
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "category_id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        //        post.setTitle(postDto.getTitle()); //        post.setContent(postDto.getContent());
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);


        Post post1= this.postRepo.save(post);
        return this.modelMapper.map(post1, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId)
    {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId)
    {
      Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post" , "post id", postId));
      this.postRepo.delete(post);
    }

    @Override
    public PostDto getPostById(Integer postId)
    {
       Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post id", postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir)
    {
     // if you want to give the url based command like asc and dsc like wise they operate
        Sort sort = null;
       if (sortDir.equalsIgnoreCase("asc"))
       {
           sort = Sort.by(sortBy).ascending();
       }
       else
       {
        sort = Sort.by(sortBy).descending();
       }
       //same thing will do by using ternary operator(single line code)-> format(if(-)?(true):(false))
//Sort sort = (sortDir.equalsIgnoreCase("asc"))?sort = Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort); //replace the place of sort->(Sort.by(sortBy).descending())
        Page<Post> pagePost = this.postRepo.findAll(p);
        List<Post> allPosts = pagePost.getContent();
        List<PostDto> postDtos = allPosts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId)
    {
        User user = this.userRepos.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User_id", userId));
        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
    @Override
    public List<PostDto> getPostByCategory(Integer categoryId)
    {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category_id", categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);//find the posts which is come under category
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword)
    {
        List<Post> posts = this.postRepo.searchByTitle("%"+keyword+"%");
        List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
