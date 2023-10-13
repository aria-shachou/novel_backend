package com.klein.novel.repository;

import com.klein.novel.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter,Long> {
    List<Chapter> findByNovelId(Long id);
    Chapter findByNovelIdAndChapterNumber(Long novelId,int chapterNumber);
}
