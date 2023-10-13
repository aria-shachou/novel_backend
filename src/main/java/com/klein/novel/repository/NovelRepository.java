package com.klein.novel.repository;

import com.klein.novel.entity.Novel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NovelRepository extends JpaRepository<Novel,Long> {
    List<Novel> findByAuthorId(Long id);
    List<Novel> findByCategories_Id(Long id);
    List<Novel> findByNameContainingIgnoreCase(String name);
}
