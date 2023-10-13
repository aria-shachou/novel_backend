package com.klein.novel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "chapter")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int chapterNumber;
    @Column(nullable = false)
    private String name;
    @Lob
    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id", name = "novel_id")
    private Novel novel;

    @JsonIgnore
    @OneToMany(mappedBy = "chapter",cascade = CascadeType.ALL)
    private List<Comment> commentList;

}
