package com.klein.novel.controller;

import com.klein.novel.entity.Comment;
import com.klein.novel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.getComment(id), HttpStatus.OK);
    }

    @GetMapping("/chapter/{chapterId}")
    public ResponseEntity<List<Comment>> getCommentsByChapterId(@PathVariable Long chapterId) {
        return new ResponseEntity<>(commentService.getCommentsByChapterId(chapterId),HttpStatus.OK);
    }

    @PostMapping("/chapter/{chapterId}")
    public ResponseEntity<HttpStatus> createComment(@RequestBody Comment comment, @PathVariable Long chapterId,@RequestParam String username) {
        commentService.postComment(comment,chapterId,username);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<HttpStatus>deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
