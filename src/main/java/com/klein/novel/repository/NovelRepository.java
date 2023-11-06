package com.klein.novel.repository;

import com.klein.novel.entity.Novel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NovelRepository extends JpaRepository<Novel,Long> {
    List<Novel> findByAuthorId(Long id);
    List<Novel> findByCategories_Id(Long id);
    List<Novel> findByNameContainingIgnoreCase(String name);
    @Query("SELECT n FROM Novel n ORDER BY n.views DESC")
    List<Novel> findTopMostViewsNovels(Pageable pageable);

    List<Novel> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
