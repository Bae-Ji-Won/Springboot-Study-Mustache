package com.mustache.bbs.Controller;


import com.mustache.bbs.Domain.Dto.ArticleAddRequest;
import com.mustache.bbs.Domain.Dto.ArticleAddResponse;
import com.mustache.bbs.Service.ArticleRestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleRestContorller {

    private final ArticleRestService articleRestService;

    public ArticleRestContorller(ArticleRestService articleRestService) {
        this.articleRestService = articleRestService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleAddResponse> getfind(@PathVariable Long id){
        return ResponseEntity.ok().body(articleRestService.getfind(id));
    }

    @PostMapping
    public ResponseEntity<ArticleAddResponse> addArticle(@RequestBody ArticleAddRequest dto) {      // @RequestBody를 통해 json 형식으로 받음
        ArticleAddResponse response = articleRestService.add(dto);
        return ResponseEntity.ok().body(response);
    }
}
