package com.mustache.bbs.repository;

import com.mustache.bbs.Domain.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
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
        System.out.println("데이터 값 : "+ hp);
        assertEquals(1,hp.getId());
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층",hp.getFulladdress());
        assertEquals("효치과의원",hp.getHospitalname());
    }

    @Test
    @DisplayName("BusinessTypeName이 보건소, 보건진료소, 보건지소 중 1개이상을 포함한 데이터가 잘 나오는지 확인")
    void findByBusinessTypeNameIn(){
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건진료소");
        inClues.add("보건지소");

        List<Hospital> hospitals = hospitalRepository.findByBusinesstypenameIn(inClues);
        for(var hospital:hospitals)
            System.out.println(hospital.getHospitalname());
    }


    @Test
    @DisplayName("In, And, Like모두 사용한 데이터 찾기")
    void findByBusinesstypenameInAndRoadNameAddressLike(){
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건진료소");
        inClues.add("보건지소");
        String str = "%광진구%";

        List<Hospital> hospitals = hospitalRepository.findByBusinesstypenameInAndRoadNameAddressLike(inClues,str);
        for(var hospital:hospitals)
            System.out.println(hospital.getHospitalname());
    }

    @Test
    @DisplayName("Like 대신 Containing 사용하여 데이터 찾기")
    void Containing(){
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContaining("송파구");
        for(Hospital hospital : hospitals)
            System.out.printf("%s, %s\n",hospital.getHospitalname(),hospital.getRoadNameAddress());
    }

    @Test
    void startsWith(){
        List<Hospital> hospitals = hospitalRepository.findByHospitalnameStartsWith("경희");
        for(Hospital hospital : hospitals)
            System.out.printf("%s, %s\n",hospital.getHospitalname(),hospital.getRoadNameAddress());
    }

    @Test
    void endWith(){
        List<Hospital> hospitals = hospitalRepository.findByHospitalnameEndingWith("병원");
        for(Hospital hospital : hospitals)
            System.out.printf("%s, %s\n",hospital.getHospitalname(),hospital.getRoadNameAddress());
    }
    
    @Test
    @DisplayName("2개의 값 사이의 데이터 호출")
    void findByPatientRoomCountAndPatientRomCount(){
        List<Hospital> hospitals = hospitalRepository.findByPatientroomcountBetweenOrderByPatientroomcountDesc(10,20);
        for(Hospital hospital: hospitals)
            System.out.printf("%s, %d\n",hospital.getHospitalname(),hospital.getPatientroomcount());
    }
}