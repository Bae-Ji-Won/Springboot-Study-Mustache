package com.mustache.bbs.Domain.Dto;

import com.mustache.bbs.Domain.Article;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ArticleAddRequest {
    private String title;
    private String content;

    public Article toEntity(){
        Article article = Article.builder()
                .title(this.title)
                .content(this.content)
                .build();

        return article;
    }
}
