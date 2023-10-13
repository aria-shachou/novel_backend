package com.klein.novel.dto;

import lombok.Data;

@Data
public class NovelDto {
    private Long id;
    private String name;
    private String description;
    private String status;
}
