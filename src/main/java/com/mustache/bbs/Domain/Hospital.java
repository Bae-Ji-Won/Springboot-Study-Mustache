package com.mustache.bbs.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "nation_wide_hospitals")
public class Hospital {
    @Id
    private Integer id;

    @Column(name = "hospital_name") // DB 변수와 entity 변수명이 다를경우 Column을 통해 직접 연결할 수 있다.
    private String hospitalname;

    @Column(name = "full_address")
    private String fulladdress;     // cammel 형식으로 작성시 자동으로 DB에 _ 형식으로 들어감 fullAddress => full_address

//    public static Hospital of(Integer id) {
//        return Hospital.builder().id(id).build();
//    }

}
