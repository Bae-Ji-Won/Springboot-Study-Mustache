package com.mustache.bbs.Domain;

import com.mustache.bbs.Domain.Dto.ArticleAddResponse;
import com.mustache.bbs.Domain.Dto.ArticleDto;
import com.mustache.bbs.Domain.Dto.HospitalResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "article2")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    public Article(String title,String content) {
        this.title = title;
        this.content = content;
    }

    // ArticleAddResponse DTO로 변환
    public static ArticleAddResponse of2(Article article) {
        return new ArticleAddResponse(article.getId(),
                article.getTitle(),article.getContent());
    }

}
