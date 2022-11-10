package com.mustache.bbs.Controller;


import com.mustache.bbs.Domain.ArticleDto;
import com.mustache.bbs.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    /* Spring이 DI하는 구간
       ArticleRepository는 interface지만 그 구현제(Article)를 SpringBoot가 넣어준다.

     */
    private final ArticleRepository articleRepository;


    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // 데이터 입력 페이지 이동
    @GetMapping("/new")
    public String newArticleForm(){
        return "new";
    }

    // DB 전체 출력
    @GetMapping("/list")
    public String list(Model model){
        List<Article> articleList = articleRepository.findAll();
        model.addAttribute("articles", articleList);
        return "list";
    }

    // 아무것도 입력받지 않으면 /articles/list로 이동
    @GetMapping("")
    public String index(){
        return "redirect:/articles/list";
    }

    // id를 통한 데이터 출력 페이지 
    @GetMapping("/{id}")
    public String selectSingle(@PathVariable Long id, Model model){
        Optional<Article> optArticle = articleRepository.findById(id);  // id를 통해 데이터를 가져옴
        // Optional를 사용해 null 체크함

        if(!optArticle.isEmpty()) { // 데이터가 있다면
            model.addAttribute("article", optArticle.get());   // model에 id에 해당하는 title, content 데이터를 저장함
            return "show";
        }else {                     // 데이터가 null이라면
            return "error";
        }
    }

    // 사용자에게 입력받은 데이터 DB에 저장 후 /articles/%d url로 이동
    @PostMapping("")
    public String addAriticle(ArticleDto articledto){
        log.info(articledto.toString());
        Article saveArticle = articleRepository.save(articledto.toEntity());
        log.info("generatedId:{}", saveArticle.getId());
        return String.format("redirect:/articles/%d", saveArticle.getId());
    }
}