package com.klein.novel.service;

import com.klein.novel.entity.Chapter;
import com.klein.novel.entity.Comment;
import com.klein.novel.repository.ChapterRepository;
import com.klein.novel.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ChapterRepository chapterRepository;
    @Override
    public Comment getComment(Long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public List<Comment> getCommentsByChapterId(Long chapterId) {
        return commentRepository.findByChapterId(chapterId);
    }

    @Override
    public void postComment(Comment comment, Long chapterId,String username) {
        comment.setUsername(username);
        Chapter chapter = chapterRepository.findById(chapterId).get();
        comment.setChapter(chapterRepository.findById(chapterId).get());
        List<Comment> commentList = chapter.getCommentList();
        commentList.add(comment);
        chapterRepository.save(chapter);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
