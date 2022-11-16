package com.mustache.bbs.Domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")

// JPA Entity만을 사용하여 DB 테이블 생성하기(@Colum으로 설정값들도 넣어줌)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, name = "stock")
    private Integer stock;

    @Transient
    private String test;


    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
