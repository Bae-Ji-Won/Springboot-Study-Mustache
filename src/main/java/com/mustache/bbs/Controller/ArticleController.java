package com.mustache.bbs.Controller;


import com.mustache.bbs.Domain.ArticleDto;
import com.mustache.bbs.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // 사용자에게 입력받은 데이터를 articledto를 통해 DTO에 저장하고 난후 toEntity를 통해 entity클래스에
    // 저장하고난 후 DB에 저장 한다. 그리고 나서 redirect로  /articles/%d url 이동
    @PostMapping("")
    public String addAriticle(ArticleDto articledto){
        log.info(articledto.toString());
        Article saveArticle = articleRepository.save(articledto.toEntity());
        log.info("generatedId:{}", saveArticle.getId());
        return String.format("redirect:/articles/%d", saveArticle.getId());
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

    // id를 통한 데이터 세부 출력 페이지
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

    // edit(수정) 페이지 이동
    // 기존의 데이터를 출력하기 위해 id를 통해 데이터를 모두 호출함
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Optional<Article> optionArticle = articleRepository.findById(id);

        if(!optionArticle.isEmpty()){
            model.addAttribute("article",optionArticle.get());
            log.info(String.valueOf(optionArticle));
            return "edit";
        }else{
            model.addAttribute("errormessage",String.format("%d가 없습니다",id));
            return "error";
        }
    }

    // 수정 url
    // 프론트에서 수정한 값을 Form을 통해 articleDto에 저장하고 그값을 toEntity, JPA를 통해 DB에 저장한다.
    // 그 후 모델에 담아 수정된 상세 페이지에 데이터를 보내 수정된 데이터를 출력시킨다.
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleDto articleDto,Model model){
        Article article = articleRepository.save(articleDto.toEntity());        // DB에 수정한 데이터 저장함
        model.addAttribute("article",article);                      // model에 article 데이터 저장
        return String.format("redirect:/articles/%d",article.getId());          // 수정한 상세 페이지 이동
    }

    // 삭제 url
    @GetMapping("/{id}/delete")
    public String Delete(@PathVariable Long id,Model model){
        articleRepository.deleteById(id);
        return "redirect:/articles";
    }

}