package com.klein.novel.controller;

import com.klein.novel.dto.ChapterDto;
import com.klein.novel.entity.Chapter;
import com.klein.novel.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@RestController
@RequestMapping("/api/v1/chapter")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @GetMapping("/{chapterNumber}/novel/{novelId}")
    public ResponseEntity<ChapterDto> getChapterByNovelIdAndChapterNumber(@PathVariable int chapterNumber, @PathVariable Long novelId) {
        return new ResponseEntity<>(chapterService.getChapterByNovelIdAndChapterNumber(novelId,chapterNumber), HttpStatus.OK);
    }

    @GetMapping("/{chapterId}")
    public ResponseEntity<ChapterDto> getChapter(@PathVariable Long chapterId) {
        return new ResponseEntity<>(chapterService.getChapterById(chapterId),HttpStatus.OK);
    }

    @GetMapping("/novel/{novelId}")
    public ResponseEntity<List<Chapter>> getAllChapter(@PathVariable Long novelId) {
        return new ResponseEntity<>(chapterService.getAllChapterByNovelId(novelId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/novel/{novelId}")
    public ResponseEntity<Chapter> createChapter(@RequestBody Chapter chapter, @PathVariable Long novelId) {
        return new ResponseEntity<>(chapterService.createChapter(chapter,novelId), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Chapter> updateChapter(@RequestBody Chapter chapter, @PathVariable Long id) {
        return new ResponseEntity<>(chapterService.updateChapter(id,chapter), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteNovel(@PathVariable Long id) {
        chapterService.deleteChapter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
