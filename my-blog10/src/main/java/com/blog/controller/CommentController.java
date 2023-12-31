package com.blog.controller;

import com.blog.entity.Comment;
import com.blog.payload.CommentDto;
import com.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
   @PostMapping
    public ResponseEntity<CommentDto>createComment(@RequestParam("postId") long postId, @RequestBody CommentDto CommentDto){
        CommentDto dto = commentService.createComment(postId, CommentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String > deleteComment(@PathVariable long commentId){
        commentService.deleteComment(commentId);
return  new ResponseEntity<>("Comment is deleted",HttpStatus.OK);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto> >getCommentsByPostId( @PathVariable long postId){
        List<CommentDto> commentsDto = commentService.getCommentsByPostId(postId);
        return  new ResponseEntity<>(commentsDto,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(){
       List<CommentDto> commentDtos= commentService.getAllComments();
        return  new ResponseEntity<>(commentDtos,HttpStatus.OK);
    }
}
