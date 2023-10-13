package com.klein.novel.service;

import com.klein.novel.entity.Author;

import java.util.List;

public interface AuthorService {
    Author getAuthor(Long id);
    List<Author> getAllAuthors();
    Author createAuthor(Author author);
    Author updateAuthor(Long id, Author author);
    void deleteAuthor(Long id);
}
