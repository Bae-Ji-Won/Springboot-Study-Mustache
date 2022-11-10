package com.mustache.bbs.Controller;

import com.mustache.bbs.Domain.ArticleDto;
import com.mustache.bbs.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping(value = "/posts")
    public String createArticle(ArticleDto form){
        log.info(form.toString());
        Article article = form.toEntity();  // form 데이터를 Entity 형식으로 변경(JPA로 DB에 데이터를 보내기 위해 JPA형식으로 가공한 셈)
        articleRepository.save(article);    // Repository를 통해 DB에 값 저장
        return "";
    }
}
