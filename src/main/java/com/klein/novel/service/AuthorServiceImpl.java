package com.klein.novel.service;

import com.klein.novel.entity.Author;
import com.klein.novel.entity.Novel;
import com.klein.novel.repository.AuthorRepository;
import com.klein.novel.repository.NovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    NovelRepository novelRepository;
    @Override
    public Author getAuthor(Long id) {
        return authorRepository.findById(id).get();
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        Author oldAuthor = authorRepository.findById(id).get();
        oldAuthor.setName(author.getName());
        return authorRepository.save(oldAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        List<Novel> novels = novelRepository.findByAuthorId(id);
        for(Novel novel : novels) {
            novel.setAuthor(null);
        }
        authorRepository.flush();
        authorRepository.deleteById(id);
    }
}
