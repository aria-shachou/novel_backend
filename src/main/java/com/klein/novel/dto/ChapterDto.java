package com.klein.novel.dto;

import lombok.Data;

@Data
public class ChapterDto {
    private Long id;
    private int chapterNumber;
    private String name;
    private String content;
}
