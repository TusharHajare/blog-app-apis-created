package com.example.blogappapis.controllers;

import com.example.blogappapis.payloads.ApiResponce;
import com.example.blogappapis.payloads.CommentDto;
import com.example.blogappapis.repositories.CommentRepo;
import com.example.blogappapis.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController
{
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId)
    {
       CommentDto commentDto1 = this.commentService.CreateComment(commentDto, postId);
       return new ResponseEntity<CommentDto>(commentDto1, HttpStatus.CREATED);

    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponce> deleteCommentById(@PathVariable("commentId") Integer id)
    {
     this.commentService.deleteComment(id);
     return new ResponseEntity<ApiResponce>(new ApiResponce("Comment is deleted...!", true), HttpStatus.OK);
    }

}
