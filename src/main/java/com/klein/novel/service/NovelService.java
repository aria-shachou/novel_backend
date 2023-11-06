package com.klein.novel.service;

import com.klein.novel.entity.Novel;

import java.util.List;

public interface NovelService {
    Novel getNovel(Long id);
    List<Novel> getAllNovels();
    List<Novel> getNovelsByAuthorId(Long id);
    List<Novel> getNovelsByName(String name);
    List<Novel> getNovelsByCategoryId(Long id);

    List <Novel> getTopMostViews(int top);

    List<Novel> getLatestPublishedNovels(int numberOfNovels);
    Novel createNovel(Novel novel, List<Long> ids);
    Novel updateNovel(Novel novel, Long id, List<Long> ids);
    void  deleteNovel(Long id);
}
