package com.klein.novel.controller;


import com.klein.novel.entity.ImageModel;
import com.klein.novel.entity.Novel;
import com.klein.novel.service.ImageService;
import com.klein.novel.service.NovelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/novel")
public class NovelController {

    @Autowired
    private NovelService novelService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<Novel> getNovel(@PathVariable Long id) {
        return new ResponseEntity<>(novelService.getNovel(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Novel>> getAllNovels() {
        return new ResponseEntity<>(novelService.getAllNovels(), HttpStatus.OK);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<Novel>> getNovelsByAuthorId(@PathVariable Long id) {
        return new ResponseEntity<>(novelService.getNovelsByAuthorId(id), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Novel>> getNovelsByCategoryId(@PathVariable Long id) {
        return new ResponseEntity<>(novelService.getNovelsByCategoryId(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Novel>> getNovelByName(@RequestParam String name) {
        return new ResponseEntity<>(novelService.getNovelsByName(name),HttpStatus.OK);
    }

    @GetMapping("/image/{imageName:.+}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
        byte[] imageByte = imageService.getImageBytes(imageName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageByte, headers, HttpStatus.OK);
    }

    @GetMapping("/top/{top}")
    public ResponseEntity<List<Novel>> getTopMostViews(@PathVariable int top) {
        return new ResponseEntity<>(novelService.getTopMostViews(top),HttpStatus.OK);
    }

    @GetMapping("/publish/{publish}")
    public ResponseEntity<List<Novel>> getLatestPublishedNovels(@PathVariable int publish) {
        return new ResponseEntity<>(novelService.getLatestPublishedNovels(publish),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/category",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // /category?ids=1&id=2
    public ResponseEntity<Novel> createNovel(@Valid @RequestPart Novel novel,
                                             @RequestPart("imageFile") MultipartFile file,
                                             @RequestParam List<Long> ids) throws IOException {
        try {
            ImageModel image = uploadImage(file);
            novel.setImage(image);
            return new ResponseEntity<>(novelService.createNovel(novel, ids), HttpStatus.CREATED);
        }
        catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }


    public ImageModel uploadImage(MultipartFile files) throws IOException {
        ImageModel imageModel = new ImageModel(files.getOriginalFilename()
                ,files.getContentType()
                ,files.getBytes());
        return imageModel;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/category")
    // /category?ids=1&id=2
    public ResponseEntity<Novel> updateNovel(@Valid @RequestBody Novel novel, @PathVariable Long id, @RequestParam List<Long> ids) {
        return new ResponseEntity<>(novelService.updateNovel(novel, id, ids), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteNovel(@PathVariable Long id) {
        novelService.deleteNovel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
