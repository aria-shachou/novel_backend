package com.klein.novel.service;

import com.klein.novel.entity.Category;

import java.util.List;

public interface CategoryService {
    Category getCategory(Long id);
    List<Category> getAllCategory();
    Category createCategory(Category Category);
    Category updateCategory(Long id, Category Category);
    void deleteCategory(Long id);
}
