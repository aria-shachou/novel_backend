package com.klein.novel.service;

import com.klein.novel.dto.ChapterDto;
import com.klein.novel.entity.Chapter;

import java.util.List;

public interface ChapterService {
    List<Chapter> getAllChapterByNovelId(Long id);
    ChapterDto getChapterByNovelIdAndChapterNumber(Long novelId, int chapterNumber);
    ChapterDto getChapterById(Long id);
    Chapter createChapter(Chapter chapter, Long novelId);
    Chapter updateChapter(Long id, Chapter chapter);
    void deleteChapter(Long id);
}
