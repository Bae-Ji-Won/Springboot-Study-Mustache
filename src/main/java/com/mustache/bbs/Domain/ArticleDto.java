package com.mustache.bbs.Domain;

import com.mustache.bbs.entity.Article;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String content;

    public Article toEntity(){
        return new Article(this.id,this.title,this.content);
    }
}
