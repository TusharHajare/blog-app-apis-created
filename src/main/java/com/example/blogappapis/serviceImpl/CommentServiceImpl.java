package com.example.blogappapis.serviceImpl;

import com.example.blogappapis.entites.Comment;
import com.example.blogappapis.entites.Post;
import com.example.blogappapis.exceptions.ResourceNotFoundException;
import com.example.blogappapis.payloads.CommentDto;
import com.example.blogappapis.payloads.PostDto;
import com.example.blogappapis.repositories.CommentRepo;
import com.example.blogappapis.repositories.PostRepo;
import com.example.blogappapis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService
{
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto CreateComment(CommentDto commentDto, Integer postId)
    {
        Post post1 = this.postRepo.findById(postId).
                orElseThrow(()-> new ResourceNotFoundException("Post", "Post_id", postId ));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post1);
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId)
    {
       Comment comment = this.commentRepo.findById(commentId).
               orElseThrow(()-> new ResourceNotFoundException("Comment" ,"Comment_Id", commentId));

       this.commentRepo.delete(comment);
    }
}
