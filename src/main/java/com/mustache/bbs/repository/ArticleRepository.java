package com.mustache.bbs.repository;

import com.mustache.bbs.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO의 추상화 버전
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
