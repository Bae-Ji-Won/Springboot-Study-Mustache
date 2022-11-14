package com.mustache.bbs.repository;

import com.mustache.bbs.Domain.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {

    @Autowired
    HospitalRepository hospitalRepository;
    
    @Test
    @DisplayName("DB연결 됬는지 데이터 하나 찾기")
    void finddata(){
        Optional<Hospital> hospital = hospitalRepository.findById(1);
        Hospital hp = hospital.get();
        assertEquals(1,hp.getId());
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층",hp.getFulladdress());
        assertEquals("효치과의원",hp.getHospitalname());
    }
}