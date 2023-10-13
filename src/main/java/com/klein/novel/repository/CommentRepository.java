package com.klein.novel.repository;

import com.klein.novel.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByChapterId(Long id);
}
