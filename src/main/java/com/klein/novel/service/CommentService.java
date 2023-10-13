package com.klein.novel.service;

import com.klein.novel.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment getComment(Long id);
    List<Comment> getCommentsByChapterId(Long chapterId);
    void postComment(Comment comment, Long chapterId,String username);
    void deleteComment(Long id);
}
