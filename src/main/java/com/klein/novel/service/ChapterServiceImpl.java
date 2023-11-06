package com.klein.novel.service;

import com.klein.novel.dto.ChapterDto;
import com.klein.novel.entity.Chapter;
import com.klein.novel.entity.Novel;
import com.klein.novel.repository.ChapterRepository;
import com.klein.novel.repository.NovelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService{

    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    NovelRepository novelRepository;

    private ModelMapper mapper;

    public ChapterServiceImpl(ChapterRepository chapterRepository, ModelMapper mapper) {
        this.chapterRepository = chapterRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Chapter> getAllChapterByNovelId(Long id) {
        return chapterRepository.findByNovelId(id);
    }


    @Override
    public ChapterDto getChapterByNovelIdAndChapterNumber(Long novelId, int chapterNumber) {
        Chapter chapter = chapterRepository.findByNovelIdAndChapterNumber(novelId,chapterNumber);
        Novel novel = novelRepository.findById(novelId).get();
        novel.setViews(novel.getViews() + 1);

        novelRepository.save(novel);
        return mapToDto(chapter);
    }

    @Override
    public ChapterDto getChapterById(Long id) {
        Chapter chapter = chapterRepository.findById(id).get();
        return mapToDto(chapter);
    }

    @Override
    public Chapter createChapter(Chapter chapter, Long novelId) {
        Novel novel = novelRepository.findById(novelId).get();
        System.out.println(chapter);
        chapter.setNovel(novel);
        return chapterRepository.save(chapter);
    }

    @Override
    public Chapter updateChapter(Long id, Chapter chapter) {
        Chapter oldChapter = chapterRepository.findById(id).get();
        oldChapter.setChapterNumber(chapter.getChapterNumber());
        oldChapter.setContent(chapter.getContent());
        oldChapter.setName(chapter.getName());
        return chapterRepository.save(oldChapter);
    }

    @Override
    public void deleteChapter(Long id) {
        chapterRepository.deleteById(id);
    }

    private ChapterDto mapToDto(Chapter chapter) {
        ChapterDto chapterDto = mapper.map(chapter,ChapterDto.class);
        return chapterDto;
    }

    private Chapter mapToEntity(ChapterDto chapterDto) {
        Chapter chapter = mapper.map(chapterDto,Chapter.class);
        return chapter;
    }
}
