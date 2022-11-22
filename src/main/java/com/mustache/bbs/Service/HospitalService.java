package com.mustache.bbs.Service;

import com.mustache.bbs.Domain.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public Page<Hospital> getHospitalList(Pageable pageable){
        return hospitalRepository.findAll(pageable);
    }

    // 검색 기능
    public Page<Hospital> searchname(Pageable pageable, String keyword){
        return hospitalRepository.findByRoadNameAddressContaining(pageable, keyword);
    }
}
