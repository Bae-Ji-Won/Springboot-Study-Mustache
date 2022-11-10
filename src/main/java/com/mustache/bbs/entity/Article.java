package com.mustache.bbs.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "article2")
@NoArgsConstructor
@Getter
@SequenceGenerator(
        name = "BOARD_SEQ_GENERATOR",
        sequenceName = "BOARD_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "BOARD_SEQ_GENERATOR")
    private Long id;

    private String title;
    private String content;

    public Article(String title,String content) {
        this.title = title;
        this.content = content;
    }
}
