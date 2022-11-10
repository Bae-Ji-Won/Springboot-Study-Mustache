package com.mustache.bbs.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity // JPA에서 객체로 다루겠다는 선언
@NoArgsConstructor  // 생성자에 모든 변수를 사용하지 않을때 사용
@Getter
public class Article {
    @Id     // PK값이 무엇인지 구별하기 위해 꼭 붙여야함
    @GeneratedValue     // ID를 직접 생성하지 않고 자동으로 생성하도록 한 경우 붙힘
    private Long id;

    private String title;
    private String contents;

    public Article(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
