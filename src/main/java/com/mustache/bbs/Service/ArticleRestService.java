package com.mustache.bbs.Service;

import com.mustache.bbs.Domain.Article;
import com.mustache.bbs.Domain.Dto.ArticleAddRequest;
import com.mustache.bbs.Domain.Dto.ArticleAddResponse;
import com.mustache.bbs.Domain.Dto.ArticleDto;
import com.mustache.bbs.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleRestService {

    private final ArticleRepository articleRepository;

    public ArticleRestService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleAddResponse getfind(Long id){
        Optional<Article> optarticle = articleRepository.findById(id);
        Article article = optarticle.get();
        ArticleAddResponse articleDto = Article.of2(article);

        return articleDto;
    }

    public ArticleAddResponse add(ArticleAddRequest dto){
        Article article = dto.toEntity();
        Article savedArticle = articleRepository.save(article);
        return new ArticleAddResponse(savedArticle.getId(), savedArticle.getTitle(), savedArticle.getContent());
    }
}
