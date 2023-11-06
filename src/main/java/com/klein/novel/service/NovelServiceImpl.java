package com.klein.novel.service;

import com.klein.novel.entity.Category;
import com.klein.novel.entity.Novel;
import com.klein.novel.exception.ResourceNotFoundException;
import com.klein.novel.repository.AuthorRepository;
import com.klein.novel.repository.CategoryRepository;
import com.klein.novel.repository.NovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NovelServiceImpl implements NovelService {
    @Autowired
    NovelRepository novelRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AuthorRepository authorRepository;

    public NovelServiceImpl(NovelRepository novelRepository) {
        this.novelRepository = novelRepository;
    }

    @Override
    public Novel getNovel(Long id) {
        return novelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Novel","id",id));
    }

    @Override
    public List<Novel> getAllNovels() {
        return novelRepository.findAll();
    }

    @Override
    public List<Novel> getNovelsByAuthorId(Long id) {

        authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author","id",id));
        return novelRepository.findByAuthorId(id);
    }

    @Override
    public List<Novel> getNovelsByName(String name) {
        return novelRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Novel> getNovelsByCategoryId(Long id) {
        categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category","id",id));
        return novelRepository.findByCategories_Id(id);
    }

    @Override
    public List<Novel> getTopMostViews(int top) {
        Pageable pageable = PageRequest.of(0,top);
        return novelRepository.findTopMostViewsNovels(pageable);
    }

    @Override
    public List<Novel> getLatestPublishedNovels(int numberOfNovels) {
        Pageable pageable = PageRequest.of(0,numberOfNovels);
        return novelRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public Novel createNovel(Novel novel,List<Long> ids) {
        List<Category> categories = (List<Category>)categoryRepository.findAllById(ids);
        novel.setCategories(categories);
        novel.setViews(0);
        return novelRepository.save(novel);
    }

    @Override
    public Novel updateNovel(Novel novel, Long id,List<Long> ids) {
        Novel oldNovel = novelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author","id",id));;
        List<Category> categories = (List<Category>)categoryRepository.findAllById(ids);
        oldNovel.setAuthor(novel.getAuthor());
        oldNovel.setName(novel.getName());
        oldNovel.setCategories(categories);
        oldNovel.setStatus(novel.getStatus());
        oldNovel.setDescription(novel.getDescription());
        oldNovel.setChapters(novel.getChapters());
        return novelRepository.save(oldNovel);
    }

    @Transactional
    @Override
    public void deleteNovel(Long id) {
        novelRepository.deleteById(id);
    }

}
