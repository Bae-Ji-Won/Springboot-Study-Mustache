package com.mustache.bbs.repository;

import com.mustache.bbs.Domain.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital,Integer> {

    // JPQL
    List<Hospital> findByBusinesstypenameIn(List<String> businessTypes);    // Businesstypename을 통해 데이터 찾기

    // In, And, Like를 통해 데이터 찾기
    List<Hospital> findByBusinesstypenameInAndRoadNameAddressLike(List<String> businessTypes, String roadnameaddress);

    // Like 대신 Containing을 사용해서 데이터 비교후 찾기(Like는 %를 붙여야하지만 Containing은 %를 안씀)
    List<Hospital> findByRoadNameAddressContaining(String keyword); // keyword를 포함 (%keyword% 와 같음)
    List<Hospital> findByHospitalnameStartsWith(String keyword); // keyword로 시작 (%keyword 와 같음)
    List<Hospital> findByHospitalnameEndingWith(String keyword); // keyword로 끝남 (keyword% 와 같음)

    List<Hospital> findByPatientroomcountBetweenOrderByPatientroomcountDesc(int a, int b);  // a~b 사이의 데이터만 출력

    Page<Hospital> findByRoadNameAddressContaining(Pageable pageable, String keyword);

    // 검색기능을 위한 쿼리문
//    List<Hospital> findByHospitalnameIn(List<String> hospitalname);     // Hospitalname을 통해 데이터 찾기
//
//    List<Hospital> findByFulladdressIn(List<String> fulladdress);    // fulladdress을 통해 데이터 찾기
//
//    List<Hospital> findByHospitalnameInAndFulladdressIn(List<String> hospitalname,List<String> fulladdress);
}
